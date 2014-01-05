(in-ns 'soot.game)

(defn update-cards
  "Applies f to each card in state"
  [s f] (update-in s [:cards] (partial map f)))

(defn index-cards
  "Adds indices to cards, required for some operations.  use this after
  mulliganing"
  [s] (update-in s [:cards] (fn [cs] (map (fn [c i] (assoc c :index i))))))

(defn update-hero
  "Applies f to the current hero"
  [s f] (update-in s [:heroes (:actor s)] f))

(defn current-target
  "Returns the state of the current target"
  [s] (let [[type n] (:target s)] (cond
    (= type :hero) ((:heroes s) n)
    (= type :card) ((:cards s) n))))

(defn update-target
  "Applies f to the current target"
  [s f] (let [[type n] (:target s)] (cond
    (= type :hero) (update-in s [:heroes n] f)
    (= type :card) (update-in s [:cards]
      (partial map (fn [i c] (if (= i n) (f c) c)) (range))))))

(defn set-target
  "Sets state on the current target"
  [s vs] ((apply comp
    (for [[k v] (seq vs)] (update-target (fn [c] (assoc-in c [k] v))))) s))

(defn trigger-target
  "Triggers an event on the current target"
  [s event] ((event (current-target s)) s))

(defn damage-target
  "Gives n damage to the current target, triggering damage-based secrets"
  [s n] (let [target (current-target s)
              damaged-armor (- (:armor target) n)
              new-health (+ (:health target) (min 0 damaged-armor))
              new-armor (max 0 damaged-armor)] (cond
    (:immune target) s
    (:divine-shield target) (set-target s {:divine-shield false})
    true (-> s
      (assoc-in [:damage-taking] n)
      (trigger-target :before-damaged)
      (set-target {:health new-health :armor new-armor})
      (assoc-in [:damage-taken] n)
      (trigger-target :after-damaged)))))

(defn all-things
  [s] (flatten [(vals (:heroes s)) (:cards s)]))

(def filter-map
  "keywords to boolean predicates for use with filter-all"
{
  :my (fn [s] #(= (:controller %) (:actor s)))
  :opponent (fn [s] #(not= (:controller %) (:actor s)))
  :self (fn [s] #(= (:eid %) (:targeter s)))
  ; states
  :undrawn (fn [s] #(= (:state %) :undrawn))
  :drawn (fn [s] #(= (:state %) :drawn))
  :mulliganing (fn [s] #(= (:state %) :mulliganing))
  :played (fn [s] #(= (:state %) :played))
  :discarded (fn [s] #(= (:state %) :discarded))
  ; types
  :character (fn [s] #(or (:hero %) (:minion %)))
  ; etc
  :damaged (fn [s] #(< (:health %) (:max-health %)))
  ; mapped pairs
  :not (fn [pred] #(not (pred %)))
})

(defn filter-pred
  [s filt] (let [pred (filter-map filt)]
    (if pred (pred s) (if (map? filt)
      ; Thank god for nullpointerexceptions or I'll have no idea if this is okay
      ((filter-map (first (keys filt)))
        (filter-pred s (filter-map (first (vals filt)))))
      filt))))

(defn add-default-filters
  [filters] (cond->> filters
    (not-any? #{:undrawn :drawn :mulliganing :played :discarded} filters)
    (cons :played)
    (not-any? #{:weapon :character :hero :minion :spell :hero-power})
    (cons :character)))

(defn filter-all
  [s filters] (let [
    fs (add-default-filters (flatten [filters]))
    ] (filter (fn
      [c] (every? #(% c) (map
        filter-pred (repeat s) fs))))) (all-things s))

(defn target-info [t] [(if (:hero t) :hero :card) (:index t)])

(defn push-target [s t] (-> s
  (update-in [:target-stack] (fn [stack] (cons (:target s) stack)))
  (assoc-in [:target] (target-info t))))

(defn pop-target [s] (-> s
  (assoc-in [:target] (first (:target-stack s)))
  (assoc-in [:target-stack] (rest (:target-stack s)))))

(defn target
  "Branches the game state, applying f to the state after the target is chosen;
  filters is a seq of functions or keywords.  :my and :opponent are special
  keywords that vary based on the current actor in state."
  [s filters f] (let [choices (filter-all s filters)]
    (choose s
      (for [choice choices] (fn [s] (-> s
        (push-target choice)
        f
        pop-target)))
      (map :name choices))))

(defn tick-fatigue
  "Does a tick of fatigue on the current player"
  [state] (target state [:my :hero] (fn [s] (-> s
    (update-target (fn [hero] (update-in hero [:fatigue #(+ 1 (or % 0))])))
    (#(damage-target % (get-in % [:heroes (:actor %) :fatigue])))))))

(defn draw-cards
  "Draws n cards for the current player"
  [s n] (if (> n 0) (let [
    undrawn (:eid (first (filter-all s [:my :undrawn])))
    drawn (if undrawn
      (update-cards s #(if (some #{[:eid undrawn]} %)
        (assoc-in % [:state] :drawn) %))
      (tick-fatigue s))] (draw-cards drawn (dec n))) s))
