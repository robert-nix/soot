; game.clj - let's get to a debuggable point; set up a valid game and play it
; randomly.
(ns soot.game
  (:require [clojure.set :as set]
            [clojure.math.combinatorics :as combo]
            [clojure.pprint :refer [pprint]]))

(defn without
  [coll n]
  (concat (take n coll) (nthrest coll (inc n))))

; mockup of the ai primitive
(defn choose
  "Branches between the supplied functions on state"
  [s fns labels]
  (if (first fns)
    (let [choice 0 ; (rand-int (count fns))
          ; _ (pprint s)
          ; _ (pprint fns)
          ; _ (pprint labels)
          chose ((nth fns choice) s)]
      (if chose
        (do
          ; (println "\nChoosing:")
          ; (println labels)
          ; (println "Chose" choice (nth labels choice))
          chose)
        (recur s (without fns choice) (without labels choice))))
    nil))

(load "game_helpers")
(load "cards")

(def cards-by-id
  (loop [v cards by-id {}] (if (nil? v)
    by-id
    (let [head (first v)]
      (recur
        (next v)
        (conj by-id {(:id head) head}))))))

(defn card
  "Gets a card definition by numerical id or attempts to get a card definition
  by name, throwing an exception if not exactly one card is found"
  [id] (if (number? id)
    (cards-by-id id)
    (let [result (filter #(= (:name %) id) cards)]
      (if (= 1 (count result))
        (first result)
        (throw (Exception. (str
          "Could not find exactly one card with name " id)))))))

;;;

; phases: setup, mulliganing, turns, finished
(defn create-game
  "Initializes a game up to before the mulliganing phase"
  [first-hero first-deck second-hero second-deck] (-> {
    ; whoever owns the current actor, determining :my/:opponent
    :actor 0
    ; hero, hero power, mana, and refs to weapon, minions
    :heroes (apply hash-map (interleave [0 1]
      (map create-hero [first-hero second-hero] [0 1])))
    ; everything else - mix together the players to keep the structure flat
    :cards (map (fn [card eid] (assoc-in card [:eid] eid)) (shuffle (mapcat
      (fn [v] (let [[pid deck] v] (map create-card deck (repeat pid))))
      [[0 first-deck] [1 second-deck]])) (range))
  }
  (#(assoc % :next-eid (count (:cards %))))
  (draw-cards 3)
  swap-actor
  (draw-cards 4)
  swap-actor))

(defn mulligan
  "Do mulliganing for the current actor"
  [s] (let [
    subsets (combo/subsets (filter-all s [:my :drawn :card]))
    eidsets (map #(set (map vector (repeat :eid) (map :eid %))) subsets)
    chosen (choose s
      ; transitions
      (for [eidset eidsets]
        (fn [s] (update-cards s (fn [c]
          (if (some eidset c)
            (assoc c :state :mulliganing)
            c)))))
      ; names
      (map #(map :name %) subsets))]
    ; with chosen, draw count mulliganing, make mulliganing undrawn, shuffle
    (-> chosen
      (draw-cards (count (filter-all chosen [:my :mulliganing :card])))
      (update-cards #(if (some #{[:state :mulliganing]} %)
        (assoc-in % [:state] :undrawn) %))
      (update-in [:cards] shuffle)
      (index-cards))))

(defn play-turn
  "Runs a full turn of play for the current actor"
  [s] (-> s
    (trigger-all :start-of-turn)
    (update-hero (fn [h]
      (assoc h :mana-crystal
        (min 10 (+ (or (:mana-crystal h) 0) 1)))))
    (update-hero (fn [h]
      (assoc h :mana
        (max (- (:mana-crystal h) (or (:overload h) 0)) 0))))
    (draw-cards 1)
    (choose-loop)
    ((fn [s]
      (if (first (filter-all s [:hero :discarded]))
        (-> s
          (assoc :game-over true)
          (assoc :winner (:owner (first (filter-all s [:hero :played])))))
        (-> s
          (trigger-all :end-of-turn)
          (reset-turn-stats)))))))
