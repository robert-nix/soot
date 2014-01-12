(in-ns 'soot.game)

(defn all-things
  [s] (flatten [(vals (:heroes s)) (:cards s)]))

(defn thing-by-eid [s eid] (first (filter #(= (:eid %) eid) (all-things s))))

(defn update-cards
  "Applies f to each card in state"
  [s f] (update-in s [:cards] (partial map f)))

(defn update-last-summoned
  [s f] (update-cards s (fn [c] (if (= (:eid c) (:last-summoned s))
    (f c) c))))

(defn update-targeter
  [s f] (update-cards s (fn [c] (if (= (:eid c) (:targeter s))
    (f c) c))))

(defn index-cards
  "Adds indices to cards, required for some operations.  use this after
  shuffling"
  [s] (update-in s [:cards] (fn [cs] (map (fn [c i] (assoc c :index i))))))

(defn update-hero
  "Applies f to the current hero"
  [s f] (update-in s [:heroes (:actor s)] f))

(defn current-hero [s] (get-in s [:heroes (:actor s)]))

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
  "Triggers an event on the current target.  If the event returns nil, trigger
  acts as identity"
  [s event] (or ((or (event (current-target s)) identity) s) s))

(defn trigger-all
  "Triggers an event on every entity"
  [s event] ((apply comp (map #(or ((or (event %) identity) %) %)
    (all-things s))) s))

(defn damage-target
  "Gives n damage to the current target, triggering damage-based secrets"
  [s n] (let [target (current-target s)
              damaged-armor (- (:armor target) n)
              minimum-health (or (:minimum-health target) 0)
              new-health (max minimum-health
                (+ (:health target) (min 0 damaged-armor)))
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

(defn target-all
  "Doesn't branch, but performs the composition of f for all targets to resolve
  the game state"
  ; kinda copy-paste but I don't care as much
  [s filters f] (let [choices (filter-all s filters)]
    ((apply comp (for [choice choices] (fn [s] (-> s
        (push-target choice)
        f
        pop-target)))) s)))

(defn target-random
  "Doesn't branch, but performs f on a random or a number of random targets"
  [s filters f] (let [
    [n rest-filters] (if (number? (first filters))
      [(first filters) (rest filters)] [1 filters])
    choices (filter-all s rest-filters)
    n-choices (count choices)
    chosen (shuffle (concat (repeat n true) (repeat (- n-choices n) false)))]
    (if (< n-choices n) nil
      ((apply comp (for [[choice chose] [choices chosen]] (if chose
        (fn [s] (-> s (push-target choice) f pop-target))
        identity))) s))))

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

(defn swap-actor
  [s] (update-in s [:actor] {0 1 1 0}))

(defn swap-player
  [s] (update-in s [:player] {0 1 1 0}))

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
    (update-last-summoned #(assoc-in % [:state]
      (if (>= (cards-in-hand s) 10) :discarded :drawn)))))

(defn give-opponent-card
  [s cid] (-> s
    swap-actor
    (give-card cid)
    swap-actor))

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
        (update-last-summoned #(-> %
          (assoc-in [:state] :played)
          (assoc-in [:left] (:targeter s))
          (assoc-in [:right] (:right (targeter s)))))
        ; update the targeter's right
        (#(update-cards % (fn [c] (if (= (:eid c) (:targeter s))
          (assoc-in c [:right] (:last-summoned %)) c))))
        ; update the targeter's right's left
        (#(update-cards % (fn [c] (if (= (:eid c) (:right (targeter s)))
          (assoc-in c [:left] (:last-summoned %)) c))))
        (trigger-all :after-summoned)))))

(defn summon-opponent-minion
  [s minions] (-> s
    swap-actor
    (summon-minion minions)
    swap-actor))

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
  ; todo: do correct :max-health updating with :health modifiers
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

(defn buff-target [s attack health]
  (give-target s {:attack attack :health health}))

(defn buff-weapon [s attack health]
  (target s [:my :weapon] (buff-target attack health)))

(defn buff-self [s attack health]
  (target s [:self] (buff-target attack health)))

(defn choose-one
  "Chooses between given spell ids/names.  Does NOT create the spells, simply
  applies their card-def's :spell function."
  [s & choices] (choose s
    (map card choices)
    (map #(:name (card %)) choices)))

(defn remove-target-positioning
  "Removes the target's positioning info and updates the target's neighbors'
  positioning info"
  [s] (let [target (current-target s)] (-> s
    (update-target (fn [t] (-> t
      (assoc-in [:left] nil)
      (assoc-in [:right] nil))))
    (update-cards (fn [c] (cond-> c
      (= (:eid c) (:left target)) (update-in c [:right] (:right target))
      (= (:eid c) (:right target)) (update-in c [:left] (:left target))))))))

(defn destroy-target
  "Destroys (discards) the target"
  ; note: for minions damaged to below 0 health, destruction is triggered after
  ; exiting the current card's context; i.e., after a card is used, all cards
  ; are checked for destruction.  this prevents weirdness when aoe cards and
  ; aura minions combine.
  [s] (-> s
    (trigger-all :before-destroyed)
    (set-target {:state :discarded})
    (trigger-target :deathrattle)
    (remove-target-positioning)
    (trigger-all :after-destroyed)))

(defn destroy-self
  [s] (target [:self] destroy-target))

(defn equip-weapon
  "Creates and summons a weapon for the current hero.  Destroys any existing
  weapon first"
  ; todo: this feels wrong
  [s cid] (-> s
    (target [:my :weapon] destroy-target)
    (summon-card cid)
    (update-last-summoned #(assoc-in % [:state] :played))))

(defn destroy-opponent-weapon
  [s] (target s [:opponent :weapon] destroy-target))

(defn opponent-weapon-durability
  [s] (:health (first (filter-all s [:opponent :weapon]))))

(defn create-hero
  [cid pid] (->
    (create-card cid pid)
    (#(update-cards % (fn [c] (if (= (:eid c) (:last-summoned %))
      (-> c
        (assoc-in [:state] :played))
      c))))))

(defn set-hero
  [s cid] (let [pid (:actor s)]
    (update-hero #(create-hero cid pid))))

(defn count-minions
  [s filters] (count (filter-all s (conj filters :minion))))

(defn mind-control-target
  [s] (-> s (update-target (fn [c] (assoc-in c [:owner] (- 1 (:actor s)))))))

(defn polymorph-target
  [s cid] (-> s
    (summon-card cid)
    ; summon to the right of the targeter
    (update-last-summoned #(-> %
      (assoc-in [:state] :played)
      (assoc-in [:left] (:left (current-target s)))
      (assoc-in [:right] (:right (current-target s)))))
    ; update the target's left's right
    (#(update-cards % (fn [c] (if (= (:eid c) (:left (current-target s)))
      (assoc-in c [:right] (:last-summoned %)) c))))
    ; update the target's right's left
    (#(update-cards % (fn [c] (if (= (:eid c) (:right (current-target s)))
      (assoc-in c [:left] (:last-summoned %)) c))))
    (destroy-target)))

(defn combo
  [s normal-f combo-f] (if (> (:cards-played-this-turn s) 0)
    (combo-f s) (normal-f s)))

(defn is-dead
  ; used in cases where the card hasn't been checked for death yet, so we check
  ; current health as well
  [c] (or (= (:state c) :discarded) (<= (:health c) 0)))

(defn destroy-minions
  [s] (target-all [:minion] destroy-target))

(defn draw-target
  [s] (update-target s #(update-in % [:state] :drawn)))

(defn copy-target
  [s] (let [target (current-target s)]
    (update-targeter s (fn [t] (-> target
      (assoc-in [:owner] (:owner t))
      (assoc-in [:eid] (:eid t))
      (assoc-in [:left] (:left t))
      (assoc-in [:right] (:right t)))))))

(defn hero-armor [s] (:armor (current-hero s)))

(defn hero-health [s] (:health (current-hero s)))

(defn return-target [s] (-> s
  (update-target (fn [t] (-> (create-card (:id t) (:owner t))
    (assoc-in [:eid] (:eid t))
    (assoc-in [:state] :drawn))))
  (remove-target-positioning)))
