(in-ns 'soot.game)

(defn all-things
  [s] (flatten [(vals (:heroes s)) (:cards s)]))

(defn thing-by-eid [s eid] (first (filter #(= (:eid %) eid) (all-things s))))

(defn update-cards
  "Applies f to each card in state"
  [s f] (update-in s [:cards] (partial map f)))

(defn index-cards
  "Adds indices to cards, required for some operations.  use this after
  shuffling"
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
  [s event] ((or (event (current-target s)) identity) s))

(defn trigger-all
  "Triggers an event on every entity"
  [s event] ((apply comp (map #(or (event %) identity) (all-things s))) s))

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
  :last-summoned (fn [s] #(= (:eid %) (:last-summoned s)))
  ; adjacent to targeter
  :adjacent (fn [s] #(some #{[:left (:targeter s)] [:right (:targeter s)]} %))
  :adjacent-to-target (fn [s] #(some #{
    [:left (:eid (current-target s))]
    [:right (:eid (current-target s))]} %))
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
  (update-in [:target-stack] rest)))

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

(defn cards-in-hand [s] (count (filter-all s [:my :drawn])))

(defn draw-cards
  "Draws n cards for the current player"
  [s n] (if (> n 0) (let [
    undrawn (:eid (first (filter-all s [:my :undrawn])))
    card-state (if (>= (cards-in-hand s) 10) :discarded :drawn)
    drawn (if undrawn
      (-> s
        (update-cards #(if (some #{[:eid undrawn]} %)
          (assoc-in % [:state] card-state) %)))
      (tick-fatigue s))] (draw-cards drawn (dec n))) s))



(def actor-map
  "Functions to transform card-def keys to events and stuff"
{

})

; needed to resolve summons
(declare card)

(defn create-card
  "Creates an undrawn card given an id/name and controller id"
  [cid pid] (let [
    card-def (card cid)
    actor (some #{:minion :weapon :secret :hero} (keys card-def))
    ability (some #{:hero-power :spell} (keys card-def))
    ] (cond-> card-def
      true (->
        (assoc-in [:state] :undrawn)
        (assoc-in [:owner] pid))
      actor (->
        ; Again, NPEs help keep shit real
        (apply comp (map #(apply actor-map %) (seq (actor card-def)))))
      ability (->
        (assoc-in [:use] (ability card-def))))))

(defn summon-card
  [s cid] (-> s
    (update-in [:cards]
      (fn [cs] (conj cs (-> (create-card cid (:actor s))
        (assoc-in [:index] (count cs))
        (assoc-in [:eid] (:next-eid s))
        (assoc-in [:summoned] true)
        (assoc-in [:state] :undrawn)))))
    (assoc-in [:last-summoned] (:next-eid s))
    (update-in [:next-eid] inc)))

(defn give-card
  [s cid] (-> s
    (summon-card s cid)
    (#(update-cards % (fn [c] (if (= (:eid c) (:last-summoned %))
      (assoc-in c [:state] (if (>= (cards-in-hand s) 10) :discarded :drawn))
      c))))))

(defn targeter [s] (thing-by-eid (get-in s [:targeter])))

(defn summon-minion
  "Creates and summons one or more minions for the current actor.  Does not
  trigger battlecries."
  [s minions] (if (seq? minions)
    ((apply comp (map (fn [m] (fn [s] (summon-minion s m))) minions)) s)
    ; use identity for over-summoning; it's not an invalid state.
    (if (>= (count (filter-all s [:my :minion])) 7) s
      (-> s
        (trigger-all :before-summoned)
        (summon-card minions)
        ; summon to the right of the targeter
        (#(update-cards % (fn [c] (if (= (:eid c) (:last-summoned %))
          (-> c
            (assoc-in [:state] :played)
            (assoc-in [:left] (:targeter %))
            (assoc-in [:right] (:right (targeter %))))
          c))))
        ; update the targeter's right
        (#(update-cards % (fn [c] (if (= (:eid c) (:targeter s))
          (assoc-in c [:right] (:last-summoned %)) c))))
        (trigger-all :after-summoned)))))

; (defn && [& bools] (every? identity bools))
(defn || [& bools] (some identity (cons false bools)))

(def give-target-map
  "Determines how to add stats with give-target"
  ; also causes NPEs! (fn [& values] ...)
{
  :immune ||
  :divine-shield ||
  :taunt ||
  :freeze ||
  :charge ||
  :windfury ||
  :stealth ||
  :stealth-this-turn ||
  :stealth-next-turn ||
  :spell-damage +
  :attack +
  :attack-this-turn +
  :use-cost +
  :mana +
  :mana-crystal +
  :health +
  :max-health +
  :armor +
  :deathrattle comp
  :before-minion-attacks comp
  :start-of-my-turn comp
  :end-of-turn comp
})

(defn give-target
  "Adds to the current target's stats"
  [s stats] (if (map? stats)
    (reduce #(%2 %1) s (map (fn
      [[stat value]] (fn [s]
        (update-target s (fn [t]
          ; support functions such as doubling attack/health
          (update-in t [stat] (if (fn? value) value (fn [v]
            ; prevent NPEing when the target doesn't have a value yet
            (#(% value (or v (%))) (give-target-map stat)))))))))
      stats))
    (give-target s {stats true})))
