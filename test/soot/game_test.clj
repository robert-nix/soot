(ns soot.game-test
  (:require [clojure.test :refer :all]
            [soot.game :refer :all]
            [clojure.pprint :refer [pprint]]))

(def druid-deck [
  "Innervate" "Innervate"
  "Claw" "Claw"
  633 633 ; Wrath
  "Bloodmage Thalnos"
  "Novice Engineer" "Novice Engineer"
  "Wild Pyromancer" "Wild Pyromancer"
  "Earthen Ring Farseer" "Earthen Ring Farseer"
  "Harvest Golem" "Harvest Golem"
  "Tinkmaster Overspark"
  "Swipe" "Swipe"
  "Defender of Argus"
  "Keeper of the Grove" "Keeper of the Grove"
  464 ; Starfall
  587 587 ; Druid of the Claw
  "Sylvanas Windrunner"
  "Cairne Bloodhoof"
  "Ancient of Lore" "Ancient of Lore"
  "Baron Geddon"
  "Cenarius"
])

(deftest random-game-test
  (testing "FIXME, I fail."
    (pprint (->
      (create-game
        "Malfurion Stormrage"
        druid-deck
        "Malfurion Stormrage"
        druid-deck)
      mulligan
      swap-actor
      mulligan
      (give-card "The Coin")
      swap-actor
      (filter-all [:my :drawn :card])))
    (is (= 0 1))))
