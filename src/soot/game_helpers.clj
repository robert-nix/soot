(in-ns 'soot.game)

(defn all-things [s] (flatten [(vals (:heroes s)) (:cards s)]))

(defn thing-by-eid [s eid] (first (filter #(= (:eid %) eid) (all-things s))))

(defn targeter [s] (thing-by-eid s (:targeter s)))

(defn observer [s] (thing-by-eid s (:observer s)))

(defn last-summoned [s] (thing-by-eid (:last-summoned s)))

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
  "Adds indices to cards; use this after shuffling"
  [s] (update-in s [:cards] (fn
    [cs] (map (fn [c i] (assoc c :index i)) cs (range)))))

(defn update-hero
  "Applies f to the current hero"
  [s f] (update-in s [:heroes (:actor s)] f))

(defn update-all
  "Applies f to cards and heroes"
  [s f] (-> s (update-hero f) (update-cards f)))

(defn current-hero [s] (get-in s [:heroes (:actor s)]))

(defn current-target
  "Returns the state of the current target"
  [s] (let [[type n] (:target s)] (cond
    (= type :hero) ((:heroes s) n)
    (= type :card) (nth (:cards s) n))))

(defn update-target
  "Applies f to the current target"
  [s f] (let [[type n] (:target s)] (cond
    (= type :hero) (update-in s [:heroes n] f)
    (= type :card) (update-in s [:cards]
      (partial map (fn [i c] (if (= i n) (f c) c)) (range))))))

(defn set-target
  "Sets state on the current target"
  ([s vs] ((apply comp
    (for [[k v] (seq vs)] #(update-target % (fn [c] (assoc c k v))))) s))
  ([vs] (fn [s] (set-target s vs))))

; observer stack (used to tell an event notifee who they are)

(defn push-observer [s o] (-> s
  (update-in [:observer-stack] (fn [stack] (cons (:observer s) stack)))
  (assoc :observer o)))

(defn pop-observer [s] (-> s
  (assoc :observer (first (:observer-stack s)))
  (update-in [:observer-stack] rest)))

(def filter-map
  "keywords to boolean predicates for use with filter-all"
{
  :my (fn [s] #(= (:owner %) (:actor s)))
  :opponent (fn [s] #(not= (:owner %) (:actor s)))
  :self (fn [s] #(= (:eid %) (:targeter s)))
  ; states
  :undrawn (fn [s] #(= (:state %) :undrawn))
  :drawn (fn [s] #(= (:state %) :drawn))
  :mulliganing (fn [s] #(= (:state %) :mulliganing))
  :played (fn [s] #(= (:state %) :played))
  :discarded (fn [s] #(= (:state %) :discarded))
  ; types
  :character (fn [s] #(or (:hero %) (:minion %)))
  :card (fn [s] #(or (:weapon %) (:minion %) (:spell %) (:secret %)))
  :all (fn [s] (fn [c] true))
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
        (filter-pred s (or (filter-map (first (vals filt))) (first (vals filt)))))
      filt))))

(defn add-default-filters
  [filters] (cond->> filters
    (not-any? #{:self :undrawn :drawn :mulliganing :played :discarded} filters)
    (cons :played)
    (not-any? #{:self :card :weapon :character :hero :minion :spell :hero-power
      :secret :all} filters)
    (cons :character)))

(defn add-direct-filters
  "Adds filters for direct targeting, implementing stealth and spell immunity;
  requires state to discern whether we are targeting via a spell or a minion."
  [s filters] (cond->> filters
    (not-any? #{:self :my} filters) (cons {:not :stealth})
    (:spell (targeter s)) (cons {:not :spell-immunity})))

(defn filter-all
  [s filters] (let [
    fs (add-default-filters (flatten [filters]))
    ] (do (filter (fn
      [c] (every? #(% c) (map
        filter-pred (repeat s) fs))) (all-things s)))))

(defn trigger-target
  "Triggers an event on the current target.  If the event returns nil, trigger
  acts as identity"
  [s event] (let [f (or (event (current-target s)) identity)]
    (-> s
      (push-observer (:eid (current-target s)))
      (#(or (f %) %))
      pop-observer)))

(defn trigger-all
  "Triggers an event on every entity currently in play"
  [s event] (reduce #(%2 %1) s (map (fn
    [c] (let [f (or (event c) identity)]
      (fn [state] (-> state
        (push-observer (:eid c))
        (#(or (f %) %))
        pop-observer)))) (filter-all s [:all]))))

(defn damage-target
  "Gives n damage to the current target, triggering damage-based secrets"
  ([s n]
    (let [target (current-target s)
          spell? (:spell (targeter s))
          spell-damage (apply + (map
            :spell-damage
            (filter-all s [:my :spell-damage])))
          damage (if spell? (+ spell-damage n) n)
          damaged-armor (- (or (:armor target) 0) damage)
          minimum-health (or (:minimum-health target) 0)
          new-health (max minimum-health
            (+ (:health target) (min 0 damaged-armor)))
          new-armor (max 0 damaged-armor)]
    (cond
      (:immune target) s
      (:divine-shield target) (set-target s {:divine-shield false})
      true (-> s
        (assoc :damage-taking damage)
        (trigger-all :before-damaged)
        (set-target {:health new-health :armor new-armor})
        (assoc :damage-taken damage)
        (trigger-all :after-damaged)))))
  ([n] (fn [s] (damage-target s n))))

; target stack stuff

(defn target-info [t] [(if (:hero t) :hero :card) (:index t)])

(defn push-target [s t] (-> s
  (update-in [:target-stack] (fn [stack] (cons (:target s) stack)))
  (assoc :target (target-info t))))

(defn pop-target [s] (-> s
  (assoc :target (first (:target-stack s)))
  (update-in [:target-stack] rest)))

; targeter stack (playing, destroyer, healer, attacker, damager)

(defn push-targeter [s t] (-> s
  (update-in [:targeter-stack] (fn [stack] (cons (:targeter s) stack)))
  (assoc :targeter t)))

(defn pop-targeter [s] (-> s
  (assoc :targeter (first (:targeter-stack s)))
  (update-in [:targeter-stack] rest)))

(defn redirect-attack-target
  "Modified the target stack so that the next popped target is the current
  target.  This should cause any before-damaged events to change the target of
  the current attack to that target."
  [s] (-> s
    (update-in [:target-stack] #(cons (:target s) (rest %)))))

(defn target-entity
  [s t f] (-> s
    (push-target t)
    f
    pop-target))

(defn target
  "Branches the game state, applying f to the state after the target is chosen;
  filters is a seq of functions or keywords.  :my and :opponent are special
  keywords that vary based on the current actor in state."
  ([s filters f] (let [choices (filter-all s (add-direct-filters s filters))]
    (choose s
      (for [choice choices] (fn [s] (target-entity s choice f)))
      (map :name choices))))
  ([filters f] (fn [s] (target s filters f))))

(defn target-all
  "Doesn't branch, but performs the composition of f for all targets to resolve
  the game state"
  ; kinda copy-paste but I don't care as much
  ([s filters f] (let [choices (filter-all s filters)]
    ((apply comp (for [choice choices] (fn [s] (-> s
        (push-target choice)
        f
        pop-target)))) s)))
  ([filters f] (fn [s] (target-all s filters f))))

(defn target-random
  "Doesn't branch, but performs f on a random or a number of random targets."
  ([s filters f alternate] (let [
    [n rest-filters] (if (number? (first filters))
      [(first filters) (rest filters)] [1 filters])
    choices (filter-all s rest-filters)
    n-choices (count choices)
    chosen (shuffle (concat (repeat n true) (repeat (- n-choices n) false)))]
      ((apply comp (for [
          [choice chose]
          (partition 2 (interleave (concat choices (repeat nil)) chosen))]
        (if choice
          (if chose
            (fn [s] (if s (-> s (push-target choice) f pop-target) nil))
            identity)
          (fn [s] (if s (alternate s) nil))))) s)))
  ([s filters f] (target-random s filters f (fn [s] nil))))

(defn swap-actor
  [s] (update-in s [:actor] {0 1 1 0}))

(defn tick-fatigue
  "Does a tick of fatigue on the current player"
  [state] (target state [:my :hero] (fn [s] (-> s
    (update-target (fn [hero] (update-in hero [:fatigue #(+ 1 (or % 0))])))
    (#(damage-target % (get-in % [:heroes (:actor %) :fatigue])))))))

(defn cards-in-hand [s] (count (filter-all s [:my :drawn])))

(defn draw-cards
  "Draws n cards for the current player"
  ([s n] (if (> n 0) (let [
    undrawn (:eid (first (filter-all s [:my :undrawn :card])))
    card-state (if (>= (cards-in-hand s) 10) :discarded :drawn)
    drawn (if undrawn
      (-> s
        (update-cards #(if (some #{[:eid undrawn]} %)
          (assoc % :state card-state) %)))
      (tick-fatigue s))] (draw-cards drawn (dec n))) s))
  ([n] (fn [s] (draw-cards s n))))

(defn draw-opponent-cards
  [s amount] (-> s
    swap-actor
    (draw-cards amount)
    swap-actor))

; needed to resolve summons
(declare card)

(def property-map
{
  :charge 1
  :windfury 1
  :immune true
  :divine-shield true
  :taunt true
  :freeze true
  :stealth true
  :spell-immunity true
})

(def actor-map
  "Functions to transform card-def keys to events and stuff.
  (fn [value] (fn [card] ... card))"
(-> {
  ; :attack (fn [v] (fn [c] (assoc c :attack v)))
  :hero-power (fn [v] (fn [c] (assoc c :power (card v))))
  :when-my-spell-cast (fn [v] (fn [c] (assoc c
    :after-played (fn [s] (if (and
        (= (:owner (targeter s)) (:owner (observer s))) ; my
        (:spell s)) ; spell-cast
      (v s) s)))))
  :end-of-my-turn (fn [v] (fn [c] (assoc c
    :end-of-turn (fn [s] (if (and
        (= (:owner (targeter s)) (:owner (observer s)))) ; my
      (v s) s)))))
  :health (fn [v] (fn [c] (-> c (assoc :health v) (assoc :max-health v))))
  :properties (fn [v] (fn [c]
    ((apply comp
      (map (fn [prop]
        (fn [card] (assoc card prop (property-map prop)))) v)) c)))
  :type (fn [v] (fn [c] (-> c (assoc v true) (assoc :type v))))
}
((apply comp (map (fn [k]
  (fn [m] (assoc m k (fn [v] (fn [c] (assoc c k v))))))
  ; these keywords simply place their value directly on the card
  [:attack :spell-damage :deathrattle :battlecry])))))

(defn create-card
  "Creates an undrawn card given an id/name and controller id"
  [cid pid] (let [
    card-def (card cid)
    actor (some #{:minion :weapon :secret :hero} (keys card-def))
    ability (some #{:hero-power :spell} (keys card-def))
    ] (cond-> card-def
      true (->
        (assoc :state :undrawn)
        (assoc :owner pid))
      actor ((apply comp
        ; Again, NPEs help keep shit real
        (map (fn
          [[k v]] (let [f (actor-map k)]
            (if f
              (f v)
              (println "Unknown actor prop (cid actor k):" cid actor k))))
          (seq (actor card-def)))))
      ability (->
        (assoc :use (ability card-def))))))

(defn summon-card
  [s cid] (-> s
    (update-in [:cards]
      (fn [cs] (vec (concat cs [(-> (create-card cid (:actor s))
        (assoc :index (count cs))
        (assoc :eid (:next-eid s))
        (assoc :summoned true)
        (assoc :state :undrawn))]))))
    (assoc :last-summoned (:next-eid s))
    (update-in [:next-eid] inc)))

(defn give-card
  ([s cid] (-> s
    (summon-card cid)
    (update-last-summoned #(assoc % :state
      (if (>= (cards-in-hand s) 10) :discarded :drawn)))))
  ([cid] (fn [s] (give-card s cid))))

(defn give-opponent-card
  ([s cid] (-> s
    swap-actor
    (give-card cid)
    swap-actor))
  ([cid] (fn [s] (give-opponent-card s cid))))

(defn summon-minion
  "Creates and summons one or more minions for the current actor.  Does not
  trigger battlecries."
  ([s minions] (if (seq? minions)
    ((apply comp (map (fn [m] (fn [s] (summon-minion s m))) minions)) s)
    ; use identity for over-summoning; it's not an invalid state.
    (if (>= (count (filter-all s [:my :minion])) 7) s
      (-> s
        (trigger-all :before-summoned)
        (summon-card minions)
        ; summon to the right of the targeter
        (update-last-summoned #(-> %
          (assoc :summoning-sickness true)
          (assoc :state :played)
          (assoc :left (:targeter s))
          (assoc :right (:right (targeter s)))))
        ; update the targeter's right
        (#(update-cards % (fn [c] (if (= (:eid c) (:targeter s))
          (assoc c :right (:last-summoned %)) c))))
        ; update the targeter's right's left
        (#(update-cards % (fn [c] (if (= (:eid c) (:right (targeter s)))
          (assoc c :left (:last-summoned %)) c))))
        (trigger-all :after-summoned)))))
  ([minions] (fn [s] (summon-minion s minions))))

(defn summon-opponent-minion
  ([s minions] (-> s
    swap-actor
    (summon-minion minions)
    swap-actor))
  ([minions] (fn [s] (summon-opponent-minion s minions))))

(defn remove-target-positioning
  "Removes the target's positioning info and updates the target's neighbors'
  positioning info"
  [s] (let [target (current-target s)] (-> s
    (update-target (fn [t] (-> t
      (assoc :left nil)
      (assoc :right nil))))
    (update-cards (fn [c] (cond-> c
      (= (:eid c) (:left target)) (assoc c :right (:right target))
      (= (:eid c) (:right target)) (assoc c :left (:left target))))))))

(defn return-target
  ([s] (-> s
    (update-target (fn [t] (-> (create-card (:id t) (:owner t))
      (assoc :eid (:eid t))
      (assoc :state :drawn))))
    (remove-target-positioning)))
  ([] (fn [s] (return-target s))))

(defn swap-with-target ; Alarm-o-Bot
  [s] (let [target-eid (:eid (current-target s))]
    (-> s
      (trigger-all :before-summoned)
      (target [:self] return-target)
      (assoc :last-summoned target-eid)
      (update-target (fn [t] (-> t
        (assoc :summoning-sickness true)
        (assoc :state :played)
        (assoc :left (:left (targeter s)))
        (assoc :right (:right (targeter s))))))
      (update-cards (fn [c] (cond-> c
        (= (:eid c) (:left (targeter s))) (assoc c :right target-eid)
        (= (:eid c) (:right (targeter s))) (assoc c :left target-eid))))
      (trigger-all :after-summoned))))

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
  ; gets to be a number because it's the easiest way to deal with +charge auras
  :charge +
  :windfury + ; for raging worgen's enrage
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
  ([s stats] (if (map? stats)
    (reduce #(%2 %1) s (map (fn
      [[stat value]] (fn [s]
        (update-target s (fn [t]
          ; support functions such as doubling attack/health
          (update-in t [stat] (if (fn? value) value (fn [v]
            ; prevent NPEing when the target doesn't have a value yet
            (#(% value (or v (%))) (give-target-map stat)))))))))
      stats))
    (give-target s {stats true})))
  ([stats] (fn [s] (give-target s stats))))

(defn buff-target
  ([s attack health] (give-target s {:attack attack :health health}))
  ([attack health] (fn [s] (buff-target s attack health))))

(defn buff-weapon
  ([s attack health] (target s [:my :weapon] (buff-target attack health)))
  ([attack health] (fn [s] (buff-weapon s attack health))))

(defn buff-self
  ([s attack health] (target s [:self] (buff-target attack health)))
  ([attack health] (fn [s] (buff-self s attack health))))

(defn choose-one
  "Chooses between given spell ids/names.  Does NOT create the spells, simply
  applies their card-def's :spell function."
  ([s choices] (choose s
    (map :spell (map card choices))
    (map #(:name (card %)) choices)))
  ([choices] (fn [s] (choose-one s choices))))

(defn destroy-target
  "Destroys (discards) the target.  Triggers deathrattles."
  ; note: for minions damaged to below 0 health, destruction is triggered after
  ; exiting the current card's context; i.e., after a card is used, all cards
  ; are checked for destruction.  this prevents weirdness when aoe cards and
  ; aura minions combine.
  ([s] (-> s
    (trigger-all :before-destroyed)
    (set-target {:state :discarded})
    (trigger-target :deathrattle)
    (remove-target-positioning)
    (trigger-all :after-destroyed)))
  ([] (fn [s] (destroy-target s))))

(defn destroy-self
  [s] (target [:self] destroy-target))

(defn equip-weapon
  "Creates and summons a weapon for the current hero.  Destroys any existing
  weapon first"
  ; todo: this feels wrong
  ([s cid] (-> s
    (target [:my :weapon] destroy-target)
    (summon-card cid)
    (update-last-summoned #(assoc % :state :played))))
  ([cid] (fn [s] (equip-weapon s cid))))

(defn destroy-opponent-weapon
  [s] (target s [:opponent :weapon] destroy-target))

(defn opponent-weapon-durability
  [s] (:health (first (filter-all s [:opponent :weapon]))))

(defn create-hero
  [cid pid] (->
    (create-card cid pid)
    (assoc :state :played)
    (assoc :index pid)
    (assoc :eid (- pid 2))))

(defn set-hero
  [s cid] (let [pid (:actor s)]
    (update-hero #(create-hero cid pid))))

(defn count-minions
  [s filters] (count (filter-all s (conj filters :minion))))

(defn mind-control-target
  ([s] (-> s (update-target (fn [c] (assoc c :owner (- 1 (:actor s)))))))
  ([] (fn [s] (mind-control-target s))))

(defn polymorph-target
  ([s cid] (-> s
    (summon-card cid)
    ; summon to the right of the targeter
    (update-last-summoned #(-> %
      (assoc :state :played)
      (assoc :summoning-sickness true)
      (assoc :left (:left (current-target s)))
      (assoc :right (:right (current-target s)))))
    ; update the target's left's right
    (#(update-cards % (fn [c] (if (= (:eid c) (:left (current-target s)))
      (assoc c :right (:last-summoned %)) c))))
    ; update the target's right's left
    (#(update-cards % (fn [c] (if (= (:eid c) (:right (current-target s)))
      (assoc c :left (:last-summoned %)) c))))
    (destroy-target)))
  ([cid] (fn [s] (polymorph-target s cid))))

(defn combo
  ([s normal-f combo-f] (if (> (:cards-played-this-turn s) 0)
    (combo-f s) (normal-f s)))
  ([normal-f combo-f] (fn [s] (combo s normal-f combo-f))))

(defn is-dead
  ; used in cases where the card hasn't been checked for death yet, so we check
  ; current health as well
  [c] (or (= (:state c) :discarded) (<= (:health c) 0)))

(defn destroy-minions
  ([s] (target-all [:minion] destroy-target))
  ([] (fn [s] (destroy-minions s))))

(defn draw-target
  [s] (update-target s #(update-in % [:state] :drawn)))

(defn copy-target
  [s] (let [target (current-target s)]
    (update-targeter s (fn [t] (-> target
      (assoc :owner (:owner t))
      (assoc :eid (:eid t))
      (assoc :left (:left t))
      (assoc :right (:right t)))))))

(defn hero-armor [s] (:armor (current-hero s)))

(defn hero-health [s] (:health (current-hero s)))

(defn hero-max-health [s] (:max-health (current-hero s)))

(defn hero-power [s] (:power (current-hero s)))

(defn hero-attack [s] (:attack-this-turn (current-hero s)))

; probably need to move this up before actor-map
(defn set-hero-power [s cid]
  (target s [:my :hero] #(update-target %
    (fn [hero] (assoc hero :power (card cid))))))

(defn is-friendly [s character] (= (:owner character) (:actor s)))

(defn restore-health
  "Restores amount health to the current target.  Has a special case for
  Auchenai Soulpriest."
  ([s amount]
  (let [t (current-target s)
        new-health (max (+ (:health t) amount) (:max-health t))
        restored (- new-health (:health t))
        soulpriest (:auchenai-soulpriest-aura (current-hero s))]
    (cond
      ; note: this damage is increased by spell damage when it's a healing spell
      soulpriest (-> s damage-target s)
      (> restored 0) (-> s
        (trigger-all :before-healed)
        (update-target #(assoc % :health new-health))
        (trigger-all :after-healed)))))
  ([amount] (fn [s] (restore-health s amount))))

(defn gain-aura
  "Adds the current target's aura effects to other minions"
  [s]
  (let [target (current-target s)
        aura (:aura target)
        self? (= :self (:filter aura))
        filters (cons {:not :self} (:filter aura))
        stats (dissoc aura :filter)]
    (if self?
      (give-target s stats)
      (-> s (target-all filters (fn [t]
          (reduce #(%2 %1) t (map (fn
            [[stat value]] (fn [c]
              ; similar to give-target, but only use + for comp
              (let [update-fn (fn [v] (+ value (or v (+))))]
                (-> c
                  (update-in [stat] update-fn)
                  ; and also store these given stats in :aura-gains
                  (update-in [:aura-gains stat] update-fn))))) stats))))))))

(defn fade-aura
  "Removes the current target's aura effects from other minions"
  [s]
  ; copy paste -- all we wanna do is negate update-fn
  (let [target (current-target s)
        aura (:aura target)
        self? (= :self (:filter aura))
        filters (cons {:not :self} (:filter aura))
        stats (dissoc aura :filter)]
    (if self?
      ; fades on self are only used for enrage fading... there's a lot of
      ; cheating going on with auras, something to think about.
      (give-target s (map (fn [[k v]] [k (- v)]) stats))
      (-> s (target-all filters (fn [t]
          (reduce #(%2 %1) t (map (fn
            [[stat value]] (fn [c]
              (let [update-fn (fn [v] (- (or v (+)) value))]
                (-> c
                  (update-in [stat] update-fn)
                  (update-in [:aura-gains stat] update-fn))))) stats))))))))

(defn silence-target
  "Silences the target minion, removing all card text, including properties,
  events, and outgoing auras"
  [s]
  (let [t (current-target s)
        card-def (create-card (:id t) (:owner t))
        card-attack (:attack card-def)
        card-health (:health card-def)
        new-health (min card-health (:health t))]
    (-> s
      ; remove our auras from others
      fade-aura
      ; give post-silence pre-aura stats
      (update-target (fn [c] (-> c
        (assoc :health new-health)
        (assoc :attack card-attack)
        (dissoc :taunt :freeze :enrage :charge :windfury :divine-shield
          :stealth :spell-damage :deathrattle :aura))))
      ; reapply auras gained from others
      (give-target (:aura-gains t)))))

(defn use-minion
  "Attacks with a minion"
  [eid] (fn [state]
    (let [minion (thing-by-eid state eid)
          times-attacking (or (:times-attacking minion) 0)
          times-can-attack (if (:windfury minion) 2 1)
          can-attack (and
            (or
              (> (or (:charge minion) 0) 0)
              (not (:summoning-sickness minion)))
            (not (:cant-attack minion))
            (not (:freeze minion))
            (> (:attack minion) 0)
            (< times-attacking times-can-attack))]
      (if can-attack
        (-> state
          (push-targeter eid)
          (target [:opponent] (fn [s] (-> s
            (trigger-all :before-attacked)
            (damage-target (:attack minion))
            (update-targeter #(assoc % :times-attacking (inc times-attacking)))
            (push-targeter (:eid (current-target s)))
            (target-entity minion (fn [st] (-> st
              (damage-target (:attack (targeter st))))))
            (pop-targeter)
            (trigger-all :after-attacked))))
          pop-targeter)
        nil))))

(defn play-minion
  "Plays a minion (targeter) to the right of the current target"
  [s] (let [targeter-eid (:targeter s)
            tar (current-target s)
            target-eid (:eid tar)]
    (-> s
      (trigger-all :before-summoned)
      (assoc :last-summoned targeter-eid)
      ; summon to the right of the target
      (update-last-summoned #(-> %
        (assoc :state :played)
        (assoc :summoning-sickness true)
        (assoc :left target-eid)
        (assoc :right (:right tar))))
      (update-cards (fn [c] (cond-> c
        ; update the target's right
        (= (:eid c) target-eid) (assoc c :right targeter-eid)
        ; update the target's right's left
        (= (:eid c) (:right tar)) (assoc c :left targeter-eid))))
      (target-entity (targeter s) #(trigger-target % :battlecry))
      (trigger-all :after-summoned))))

(defn use-card
  "Uses a card (spell, minion, weapon, or secret)"
  [eid] (fn [state]
    (let [thing (thing-by-eid state eid)
          cost (if (fn? (:cost thing)) ((:cost thing) state) (:cost thing))
          unusable (> cost (:mana (current-hero state)))
          cant-play-minions (>= (count (filter-all state [:my :minion])) 7)
          invalid (fn [_] nil)
          used (or unusable (cond-> state
            true (->
              (push-targeter eid)
              (trigger-all :before-played))
            (:minion thing) (->
              (target [:my] play-minion))
            (:weapon thing) (->
              (target [:my :weapon] destroy-target)
              (assoc :last-summoned eid)
              (update-last-summoned #(-> %
                (assoc :state :played)))
              (target [:self] #(trigger-target % :battlecry)))
            (:secret thing) (->
              (assoc :last-summoned eid)
              (update-last-summoned #(-> %
                (assoc :state :played))))
            (:spell thing) (->
              (update-targeter #(assoc % :state :discarded))
              ((:use thing)))))]
      (if used
        (if unusable nil
          (if (and (:minion thing) cant-play-minions) nil
            (-> used
              (update-hero (fn [h] (update-in h [:mana] #(- % cost))))
              (trigger-all :after-played)
              (update-in [:cards-played-this-turn] #(+ (or % 0) 1))
              pop-targeter)))))))

(defn use-hero-power
  [s]
  (let [hero (current-hero s)
        times-power-used (or (:times-power-used hero) 0)
        cost (:cost (:power hero))
        unusable (or (> cost (:mana hero)) (>= times-power-used 1))]
    (if unusable
      nil
      (-> s
        (push-targeter (:eid hero))
        ((:hero-power (:power hero)))
        (update-hero (fn [h]
          (update-in h [:mana] #(- % cost))))
        (update-hero (fn [h]
          (assoc h :times-power-used (inc times-power-used))))
        pop-targeter))))

(defn attack-with-hero
  [state]
    (let [hero (current-hero state)
          times-attacking (or (:times-attacking hero) 0)
          times-can-attack (if
            (:windfury (first (filter-all state [:my :weapon]))) 2 1)
          can-attack (and
            (not (:freeze hero))
            (> (or (:attack hero) 0) 0)
            (< times-attacking times-can-attack))]
      (if can-attack
        (-> state
          (push-targeter (:eid hero))
          (target [:opponent] (fn [s] (-> s
            (trigger-all :before-attacked)
            (damage-target (:attack hero))
            (update-hero (fn [h] (assoc h :times-attacking (inc times-attacking))))
            (push-targeter (:eid (current-target s)))
            (target-entity hero
              (fn [st] (let [rec-damage (or (:attack (targeter st)) 0)]
                (if (> rec-damage 0)
                  (damage-target rec-damage)
                  st))))
            (pop-targeter)
            (trigger-all :after-attacked))))
          pop-targeter)
        nil)))

(defn end-turn
  [s] (assoc s :turn-ended true))

(defn choose-loop
  "Enumerates the choices in a turn of play, chooses one, and updates any state
  that needs to be updated after each action is taken.  This repeats until the
  choice is made to end the turn."
  [s]
  (let [my-cards (filter-all s [:my :drawn :card])
        my-minions (filter-all s [:my :minion])
        used-cards (map use-card (map :eid my-cards))
        card-names (map :name my-cards)
        used-minions (map use-minion (map :eid my-minions))
        minion-names (map :name my-minions)
        choices (concat used-cards used-minions
          [use-hero-power attack-with-hero end-turn])
        labels (concat card-names minion-names
          ["Hero Power" "Attack with Hero" "End Turn"])
        action-taken (-> s
          ; if this returns nil something has gone wrong
          (choose choices labels)
          ((fn [st] (if (:actor st)
            st
            (do
              (println "CHOOSE RETURNED NIL")
              (pprint choices)
              (pprint labels)
              (pprint s)
              (pprint st) nil))))
          ; destroy dead weapons
          (target-all [:my :weapon] (fn [s]
            (if (<= (:health (current-target s)) 0)
              (destroy-target s)
              s)))
          ; recalculate hero attack
          (#(update-hero % (fn [h]
            (assoc h :attack (+
              (or (:attack-this-turn h) 0)
              (or (:attack (first (filter-all % [:my :weapon]))) 0))))))
          ; destroy dead minions and heroes
          (target-all [] (fn [s]
            (if (<= (or (:health (current-target s)) 1) 0)
              (destroy-target s)
              s)))
          ; apply end-of-turn if a hero is dead
          (#(update-in % [:turn-ended] (fn [v] (or v
            (first (filter-all % [:hero :discarded])))))))]
    (if (:turn-ended action-taken) action-taken (recur action-taken))))

(defn reset-turn-stats
  "Resets or turns over state values specific to this turn, e.g.
  attack-this-turn, overload."
  [s] (-> s
    (update-all
      (fn [c]
        (cond-> c
          (:attack-this-turn c)
          (update-in [:attack] #(- % (or (:attack-this-turn c) 0)))
          true
          (dissoc
            :immune
            :times-attacking
            :summoning-sickness
            :times-power-used
            :attack-this-turn
            :minimum-health-this-turn
            :stealth-this-turn
            :overload)
          (:overload-next-turn c)
          (assoc :overload (:overload-next-turn c))
          (:stealth-next-turn c)
          (assoc :stealth-this-turn (:stealth-next-turn c))
          )))
    (dissoc
      :turn-ended
      :cards-played-this-turn
      :spells-cost-this-turn
      :spells-cost-next-turn)
    (cond->
      (:spells-cost-next-turn s)
      (assoc :spells-cost-this-turn (:spells-cost-next-turn s)))))
