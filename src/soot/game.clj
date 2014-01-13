; game.clj - let's get to a debuggable point; set up a valid game and play it
; randomly.
(ns soot.game
  (:require [clojure.set :as set]
            [clojure.math.combinatorics :as combo]))

; mockup of the ai primitive
(defn choose
  "Branches between the supplied functions on state"
  [s fns labels]
  (if (< (count fns) 1)
    (println "What the fuck" (nth labels 0))
    (do
      (println "Choosing:")
      (println labels)
      ((nth fns 0) s))))

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
    ; the current player index
    :player 0
    ; whoever owns the current actor, determining :my/:opponent in most cases
    :actor 0
    ; hero, hero power, mana, and refs to weapon, minions
    :heroes (apply hash-map (interleave [0 1]
      (map create-hero [first-hero second-hero] [0 1])))
    ; everything else - mix together the players to keep the structure flat
    :cards (map (fn [card eid] (assoc-in card [:eid] eid)) (shuffle (mapcat
      (fn [v] (let [[pid deck] v] (map create-card deck (repeat pid))))
      [[0 first-deck] [1 second-deck]])) (range))
  }
  (draw-cards 3)
  swap-player
  (draw-cards 4)
  swap-player))

(defn mulligan
  "Do mulliganing for the current player"
  [s] (let [
    subsets (combo/subsets (filter-all s [:my :drawn]))
    eidsets (map #(set (map vector (repeat :eid) (map :eid %))) subsets)
    chosen (choose s
      ; transitions
      (for [eidset eidsets]
        (update-cards (fn [c]
          (if (some eidset c)
            (assoc-in c [:state] :mulliganing)
            c))))
      ; names
      (map #(map :name %) subsets))]
    ; with chosen, draw count mulliganing, make mulliganing undrawn, shuffle
    (-> chosen
      ; shouldn't need :my, just to make it read better
      (draw-cards (count (filter-all s [:my :mulliganing])))
      (update-cards #(if (some #{[:state :mulliganing]} %)
        (assoc-in % [:state] :undrawn) %))
      (update-in [:cards] shuffle)
      index-cards)
    ))
