; game.clj - let's get to a debuggable point; set up a valid game and play it
; randomly.
(ns soot.game
  (:require [clojure.set :as set]))

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
  by name, throwing an exception if not exactly one card is found."
  [id] (if (number? id)
    (cards-by-id id)
    (let [result (filter #(= (:name %) id))]
      (if (= 1 (count result))
        (first result)
        (throw (Exception. (str
          "Could not find exactly one card with name " id)))))))

; phases: setup, mulliganing, turns, finished
(defn create-game [first-hero first-deck second-hero second-deck]
  )
