(in-ns 'soot.game)

; as of 01/02/2014 via Hearthpwn's card db
(def cards [
; Misc
{
  :id 552
  :name "Chicken"
  :quality :free
  :set :debug
  :cost 0
  :minion {
    :attack 1
    :health 1
    :type :beast
  }
}
{
  :id 520
  :name "Excess Mana"
  :quality :free
  :set :basic
  :class :druid
  :cost 0
  :spell (draw-cards 1)
}
{
  :id 349
  :name "Flames of Azzinoth"
  :quality :free
  :set :missions
  :cost 2
  :hero-power (summon-minion [455 455])
}
{
  :id 714
  :name "I Am Murloc"
  :quality :free
  :set :reward
  :spell #((summon-minion (repeat (rand-nth [3 4 5]) 715)) %) ; 715 = 1/1 Murloc
}
{
  :id 223
  :name "Illidan Stormrage"
  :quality :free
  :set :missions
  :class :hunter
  :cost 0
  :hero {
    :max-health 30
    :hero-power "Flames of Azzinoth"
  }
}
{
  :id 655
  :name "Lorewalker Cho"
  :quality :free
  :set :missions
  :cost 0
  :hero {
    :max-health 25
    :hero-power "Transcendence"
  }
}
{
  :id 715
  :name "Murloc"
  :quality :free
  :set :reward
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :beast
  }
}
{
  :id 687
  :name "NOOOOOOOOOOOO"
  :quality :free
  :set :basic
  :cost 2
  :spell #(println "Yes?")
}
{
  :id 717
  :name "Power of the Horde"
  :quality :free
  :set :reward
  :spell #((summon-minion (rand-nth ["Frostwolf Grunt" "Sen'jin Shieldmasta"
    "Cairne Bloodhoof" "Tauren Warrior" "Thrallmar Farseer"
    "Silvermoon Guardian"])) %)
}
{
  :id 716
  :name "Rogues Do It..."
  :quality :free
  :set :reward
  :spell (target [] (-> % (damage-target 4) (draw-cards 1)))
}
; The Coin
{
  :id 141
  :name "The Coin"
  :quality :free
  :set :basic
  :cost 0
  :spell (target [:my :hero] (give-target {:mana 1}))
}
; Legendaries
{
  :id 335
  :name "Al'Akir the Windlord"
  :quality :legendary
  :set :expert
  :class :shaman
  :cost 8
  :minion {
    :attack 3
    :health 5
    :properties [:windfury :charge :divine-shield :taunt]
  }
}
{
  :id 303
  :name "Alexstrasza"
  :quality :legendary
  :set :expert
  :cost 9
  :minion {
    :attack 8
    :health 8
    :type :dragon
    :battlecry (target [:hero] (set-target {:health 15}))
  }
}
{
  :id 220
  :name "Archmage Antonidas"
  :quality :legendary
  :set :expert
  :class :mage
  :cost 7
  :minion {
    :attack 5
    :health 7
    :when-my-spell-cast (give-card "Fireball")
  }
}
{
  :id 53
  :name "Ashbringer"
  :quality :legendary
  :set :expert
  :class :paladin
  :cost 5
  :weapon {
    :attack 5
    :durability 5
  }
}
{
  :id 359
  :name "Baine Bloodhoof"
  :quality :legendary
  :set :expert
  :cost 4
  :minion {
    :attack 4
    :health 5
  }
}
{
  :id 539
  :name "Baron Geddon"
  :quality :legendary
  :set :expert
  :cost 7
  :minion {
    :attack 7
    :health 5
    :end-of-my-turn (damage-all 2)
  }
}
{
  :id 525
  :name "Bloodmage Thalnos"
  :quality :legendary
  :set :expert
  :cost 2
  :minion {
    :attack 1
    :health 1
    :spell-damage 1
    :deathrattle (draw-cards 1)
  }
}
{
  :id 498
  :name "Cairne Bloodhoof"
  :quality :legendary
  :set :expert
  :cost 6
  :minion {
    :attack 4
    :health 5
    :deathrattle (summon-minion "Baine Bloodhoof")
  }
}
{
  :id 267
  :name "Captain Greenskin"
  :quality :legendary
  :set :expert
  :cost 5
  :minion {
    :attack 5
    :health 4
    :type :pirate
    :battlecry (buff-weapon 1 1)
  }
}
{
  :id 605
  :name "Cenarius"
  :quality :legendary
  :set :expert
  :class :druid
  :cost 9
  :minion {
    :attack 5
    :health 8
    :battlecry (choose "Demigod's Favor" "Shan'do's Lesson")
      ; (target-all [:my :minion] (buff-target 2 2))
      ; (summon-minion [181 181])) ; 181 = Taunt Treant
  }
}
{
  :id 474
  :name "Deathwing"
  :quality :legendary
  :set :expert
  :cost 10
  :minion {
    :attack 12
    :health 12
    :type :dragon
    :battlecry (comp
      (target-all [:minion {:not :self}] (destroy-target))
      (target-all [:drawn] (destroy-target)))
  }
}
{
  :id 3
  :name "Edwin VanCleef"
  :quality :legendary
  :set :expert
  :class :rogue
  :cost 3
  :minion {
    :attack 2
    :health 2
    ; todo: make this less shitty
    :combo #((apply buff-self (repeat 2 (* 2 (cards-played-this-turn %)))) %)
  }
}
{
  :id 682
  :name "Elite Tauren Chieftan"
  :quality :legendary
  :set :reward
  :cost 5
  :minion {
    :attack 5
    :health 5
    :battlecry (#(comp (give-card (%)) (give-opponent-card (%)))
      #(rand-nth ["I Am Murloc" "Power of the Horde" "Rogues Do It..."]))
  }
}
{
  :id 541
  :name "Finkle Einhorn"
  :quality :legendary
  :set :expert
  :cost 2
  :minion {
    :attack 3
    :health 3
  }
}
{
  :id 251
  :name "Gelbin Mekkatorque"
  :quality :legendary
  :set :reward
  :cost 6
  :minion {
    :attack 6
    :health 6
    :battlecry #((summon-minion (rand-nth
      ["Emboldener 3000" "Homing Chicken" "Poultryizer" "Repair Bot"])) %)
  }
}
{
  :id 643
  :name "Grommash Hellscream"
  :quality :legendary
  :set :expert
  :class :warrior
  :cost 8
  :minion {
    :attack 4
    :health 9
    :properties [:charge]
    :enrage (buff-self 6 0)
  }
}
{
  :id 18
  :name "Gruul"
  :quality :legendary
  :set :expert
  :cost 8
  :minion {
    :attack 7
    :health 7
    :end-of-turn (buff-self 1 1)
  }
}
{
  :id 602
  :name "Harrison Jones"
  :quality :legendary
  :set :expert
  :cost 5
  :minion {
    :attack 5
    :health 4
    :battlecry #(-> %
      (draw-cards (opponent-weapon-durability %))
      (destroy-opponent-weapon))
  }
}
{
  :id 39
  :name "Hogger"
  :quality :legendary
  :set :expert
  :cost 6
  :minion {
    :attack 4
    :health 4
    :end-of-my-turn (summon-minion 565) ; 565 = Taunt Gnoll
  }
}
{
  :id 203
  :name "Illidan Stormrage"
  :quality :legendary
  :set :expert
  :cost 6
  :minion {
    :attack 7
    :health 5
    :type :demon
    :when-my-card-played (summon-minion 685) ; 685 = Flame of Azzinoth
  }
}
{
  :id 194
  :name "King Krush"
  :quality :legendary
  :set :expert
  :class :hunter
  :cost 9
  :minion {
    :attack 8
    :health 8
    :type :beast
    :properties [:charge]
  }
}
{
  :id 373
  :name "King Mukla"
  :quality :legendary
  :set :expert
  :cost 3
  :minion {
    :attack 5
    :health 5
    :type :beast
    :battlecry (give-opponent-card (repeat 2 "Bananas"))
  }
}
{
  :id 674
  :name "Leeroy Jenkins"
  :quality :legendary
  :set :expert
  :cost 4
  :minion {
    :attack 6
    :health 2
    :properties [:charge]
    :battlecry (summon-opponent-minion (repeat 2 "Whelp"))
  }
}
{
  :id 482
  :name "Lord Jaraxxus"
  :quality :legendary
  :set :expert
  :class :warlock
  :cost 9
  :minion {
    :attack 3
    :health 15
    :type :demon
    :battlecry #(-> %
      (set-hero 406) ; Lord Jaraxxus hero
      ; So that any Secrets/etc. that modify minion HP take effect
      ; 'my-health' since the state has always one acting character
      ; unsure if positive health effects take place? doubtful
      (set-hero-health (my-health %))
      (equip-weapon "Blood Fury")
      (destroy-self))
  }
}
{
  :id 406
  :name "Lord Jaraxxus"
  :quality :legendary
  :set :expert
  :class :warlock
  :hero {
    :health 15
    :type :demon
    :hero-power "INFERNO!"
  }
}
{
  :id 456
  :name "Lorewalker Cho"
  :quality :legendary
  :set :expert
  :cost 2
  :minion {
    :attack 0
    :health 4
    :when-spell-cast #((give-opponent-card (current-spell %)) %)
  }
}
{
  :id 241
  :name "Malygos"
  :quality :legendary
  :set :expert
  :cost 9
  :minion {
    :attack 4
    :health 12
    :type :dragon
    :spell-damage 5
  }
}
{
  :id 339
  :name "Millhouse Manastorm"
  :quality :legendary
  :set :expert
  :cost 2
  :minion {
    :attack 4
    :health 4
    :battlecry (next-turn (spells-cost 0)) ; next turn is always a 'half-turn'.
  }
}
{
  :id 19
  :name "Nat Pagle"
  :quality :legendary
  :set :expert
  :cost 2
  :minion {
    :attack 0
    :health 4
    :end-of-my-turn #((draw-cards (rand-nth [0 1])) %)
  }
}
{
  :id 285
  :name "Nozdormu"
  :quality :legendary
  :set :expert
  :cost 9
  :minion {
    :attack 8
    :health 8
    :type :dragon
    :start-of-turn (set-time-limit 15/90) ; How the fuck am I gonna do this?
  }
}
{
  :id 217
  :name "Old Murk-Eye"
  :quality :legendary
  :set :reward
  :cost 4
  :minion {
    :attack #(+ 2 (count-minions % :murloc))
    :health 4
    :type :murloc
    :properties [:charge]
  }
}
{
  :id 432
  :name "Onyxia"
  :quality :legendary
  :set :expert
  :cost 9
  :minion {
    :attack 8
    :health 8
    :type :dragon
    ; hard to tell if this is a bug but in the current beta build, ony's summons
    ; are now all to the right (as is normal with summoned minions) instead of
    ; alternating right/left
    :battlecry (summon-minion (repeat 6 527)) ; 527 = 1/1 Whelp
  }
}
{
  :id 228
  :name "Prophet Velen"
  :quality :legendary
  :set :expert
  :class :priest
  :cost 7
  :minion {
    :attack 7
    :health 7
    :when-my-spell-cast (modify-damage-and-healing (partial * 2))
    :when-my-hero-power-used (modify-damage-and-healing (partial * 2))
  }
}
{
  :id 503
  :name "Ragnaros the Firelord"
  :quality :legendary
  :set :expert
  :cost 8
  :minion {
    :attack 8
    :health 8
    :properties [:cant-attack]
    :end-of-my-turn (target-random [:opponent] (damage-target 8))
  }
}
{
  :id 33
  :name "Sylvanas Windrunner"
  :quality :legendary
  :set :expert
  :cost 5
  :minion {
    :attack 5
    :health 5
    :deathrattle (target-random [:opponent :minion] (mind-control-target))
  }
}
{
  :id 179
  :name "The Beast"
  :quality :legendary
  :set :expert
  :cost 6
  :minion {
    :attack 9
    :health 7
    :type :beast
    :deathrattle (summon-opponent-minion "Finkle Einhorn")
  }
}
{
  :id 396
  :name "The Black Knight"
  :quality :legendary
  :set :expert
  :cost 6
  :minion {
    :attack 4
    :health 5
    :battlecry (target :taunt (destroy-target))
  }
}
{
  :id 245
  :name "Tinkmaster Overspark"
  :quality :legendary
  :set :expert
  :cost 3
  :minion {
    :attack 2
    :health 2
    :battlecry (target [:minion]
      #(-> % (polymorph-target (rand-nth ["Devilsaur" "Squirrel"]))))
  }
}
{
  :id 391
  :name "Tirion Fordring"
  :quality :legendary
  :set :expert
  :class :paladin
  :cost 8
  :minion {
    :attack 6
    :health 6
    :properties [:divine-shield :taunt]
    :deathrattle (equip-weapon "Ashbringer")
  }
}
{
  :id 495
  :name "Ysera"
  :quality :legendary
  :set :expert
  :cost 9
  :minion {
    :attack 4
    :health 12
    :end-of-my-turn #((give-card (rand-nth ["Dream"
      "Emerald Drake" "Laughing Sister" "Nightmare" "Ysera Awakens"])) %)
  }
}
; Epics
{
  :id 180
  :name "Adrenaline Rush"
  :quality :epic
  :set :expert
  :class :rogue
  :cost 1
  :spell (combo (draw-cards 1) (draw-cards 2))
}
{
  :id 34
  :name "Ancient of Lore"
  :quality :epic
  :set :expert
  :class :druid
  :cost 7
  :minion {
    :attack 5
    :health 5
    :battlecry (choose "Ancient Teachings" "Ancient Secrets")
  }
}
{
  :id 242
  :name "Ancient of War"
  :quality :epic
  :set :expert
  :class :druid
  :cost 7
  :minion {
    :attack 5
    :health 5
    :battlecry (choose "Rooted" "Uproot")
      ; (target :self #(-> % (give-target :taunt) (buff-target 0 5)))
      ; (buff-self 5 0))
  }
}
{
  :id 142
  :name "Avenging Wrath"
  :quality :epic
  :set :expert
  :class :paladin
  :cost 6
  :spell (apply comp (repeat 8 (target-random [:opponent] (damage-target 1))))
}
{
  :id 670
  :name "Bane of Doom"
  :quality :epic
  :set :expert
  :class :warlock
  :cost 5
  :spell (target [] #(let [s (-> % (damage-target 2))]
    (if (is-dead (current-target s))
      (-> s (summon-minion (rand-nth ["Blood Imp" "Voidwalker" "Flame Imp"
      "Dread Infernal" "Succubus" "Felguard"])))
      s)))
}
{
  :id 304
  :name "Bestial Wrath"
  :quality :epic
  :set :expert
  :class :hunter
  :cost 1
  :spell (target :beast #(-> %
    (buff-target 2 0)
    (give-target :immune)))
}
{
  :id 73
  :name "Big Game Hunter"
  :quality :epic
  :set :expert
  :cost 3
  :minion {
    :attack 4
    :health 2
    :battlecry (target [:minion (> (:attack %) 6)]
      (destroy-target))
  }
}
{
  :id 75
  :name "Blood Knight"
  :quality :epic
  :set :expert
  :cost 3
  :minion {
    :attack 3
    :health 3
    :battlecry #(-> %
      (apply buff-self (repeat 2 (* 3 (count-minions % :divine-shield))))
      (target-all :minion (set-target {:divine-shield false})))
  }
}
{
  :id 297
  :name "Brawl"
  :quality :epic
  :set :expert
  :class :warrior
  :cost 5
  :spell #((apply comp (repeat (+ (count-minions %) -1)
    (target-random :minion (destroy-target)))) %)
}
{
  :id 147
  :name "Cabal Shadow Priest"
  :quality :epic
  :set :expert
  :class :priest
  :cost 6
  :minion {
    :attack 4
    :health 5
    :battlecry (target [:opponent :minion #(< (:attack %) 3)]
      (mind-control-target))
  }
}
{
  :id 559
  :name "Captain's Parrot"
  :quality :epic
  :set :reward
  :cost 2
  :minion {
    :attack 1
    :health 1
    :type :beast
    :battlecry (draw-random :pirate)
  }
}
{
  :id 172
  :name "Doomhammer"
  :quality :epic
  :set :expert
  :class :shaman
  :cost 5
  :overload 2
  :weapon {
    :attack 2
    :durability 8
    :properties [:windfury]
  }
}
{
  :id 467
  :name "Doomsayer"
  :quality :epic
  :set :expert
  :cost 2
  :minion {
    :attack 0
    :health 7
    :start-of-my-turn (destroy-minions)
  }
}
{
  :id 124
  :name "Earth Elemental"
  :quality :epic
  :set :expert
  :class :shaman
  :cost 5
  :overload 3
  :minion {
    :attack 7
    :health 8
    :properties [:taunt]
  }
}
{
  :id 450
  :name "Faceless Manipulator"
  :quality :epic
  :set :expert
  :cost 5
  :minion {
    :attack 3
    :health 3
    ; i.e. replace my minion slot's values with that of the target
    :battlecry (target :minion (copy-target))
  }
}
{
  :id 107
  :name "Far Sight"
  :quality :epic
  :set :expert
  :class :shaman
  :cost 3
  :spell (target-random [:my :undrawn] #(-> %
    (give-target {:cost -3})
    (draw-target)))
}
{
  :id 237
  :name "Force of Nature"
  :quality :epic
  :set :expert
  :class :druid
  :cost 6
  :spell (summon-minion (repeat 3 337))
}
{
  :id 278
  :name "Gladiator's Longbow"
  :quality :epic
  :set :expert
  :class :hunter
  :cost 7
  :weapon {
    :attack 5
    :durability 2
    :properties [:immune] ; todo, remember to interpret this tag for weapons
  }
}
{
  :id 96
  :name "Gorehowl"
  :quality :epic
  :set :expert
  :class :warrior
  :cost 7
  :weapon {
    :attack 7
    :durability 1
    :when-minion-attacked (buff-self -1 1) ; todo, remember to interpret this too
  }
}
{
  :id 660
  :name "Hungry Crab"
  :quality :epic
  :set :expert
  :cost 1
  :minion {
    :attack 1
    :health 2
    :type :beast
    :battlecry (target :murloc #(-> % (destroy-target) (buff-self 2 2)))
  }
}
{
  :id 28
  :name "Ice Block"
  :quality :epic
  :set :expert
  :class :mage
  :cost 3
  :secret {
    :when-my-hero-damaged #(if (< (hero-health %) 1)
      (-> %
        (heal-hero (damage-taken %))
        (give-hero :immune)))
  }
}
{
  :id 562
  :name "Kidnapper"
  :quality :epic
  :set :expert
  :class :rogue
  :cost 6
  :minion {
    :attack 5
    :health 5
    :combo (target :minion (return-target))
  }
}
{
  :id 506
  :name "Lay on Hands"
  :quality :epic
  :set :expert
  :class :paladin
  :cost 8
  :spell #(-> (heal-hero 8) (draw-cards 3))
}
{
  :id 301
  :name "Mindgames"
  :quality :epic
  :set :expert
  :class :priest
  :cost 4
  :spell (target-random [1 :opponent :undrawn :minion]
    #(-> % (summon-minion (current-target %)))
    (summon-minion "Shadow of Nothing"))
}
{
  :id 94
  :name "Molten Giant"
  :quality :epic
  :set :expert
  :cost #(+ 20 (- (hero-health %) (hero-max-health %)))
  :minion {
    :attack 8
    :health 8
  }
}
{
  :id 264
  :name "Mountain Giant"
  :quality :epic
  :set :expert
  :cost #(- 12 (cards-in-hand %))
  :minion {
    :attack 8
    :health 8
  }
}
{
  :id 222
  :name "Murloc Warleader"
  :quality :epic
  :set :expert
  :cost 3
  :minion {
    :attack 3
    :health 3
    :type :murloc
    :aura {:filter :murloc :attack #(+ 2 %) :health inc}
  }
}
{
  :id 14
  :name "Patient Assassin"
  :quality :epic
  :set :expert
  :class :rogue
  :cost 2
  :minion {
    :attack 1
    :health 1
    :properties [:stealth :poison]
  }
}
{
  :id 402
  :name "Pit Lord"
  :quality :epic
  :set :expert
  :class :warlock
  :cost 4
  :minion {
    :attack 5
    :health 6
    :type :demon
    :battlecry (damage-hero 5)
  }
}
{
  :id 102
  :name "Placeholder Card"
  :quality :epic
  :set :none
  :class :mage
  :cost 9
  :minion {
    :attack 6
    :health 8
    :battlecry #(println "I am Placeholder Card!")
  }
}
{
  :id 364
  :name "Preparation"
  :quality :epic
  :set :expert
  :class :rogue
  :cost 0
  ; how do I plan for this to work?  these get their own modifiers for each
  ; time-dependent function (once-this-turn next-turn aura ...) on the state
  ; object and are managed/handled appropriately?  seems needlessly complex...
  :spell (once-this-turn (spells-cost #(- % 3)))
}
{
  :id 496
  :name "Pyroblast"
  :quality :epic
  :set :expert
  :class :mage
  :cost 8
  :spell (target [] (damage-target 10))
}
{
  :id 614
  :name "Sea Giant"
  :quality :epic
  :set :expert
  :cost (- 10 (count-minions))
  :minion {
    :attack 8
    :health 8
  }
}
{
  :id 582
  :name "Shadow of Nothing"
  :quality :epic
  :set :expert
  :class :priest
  :cost 0
  :minion {
    :attack 0
    :health 1
  }
}
{
  :id 421
  :name "Shadowform"
  :quality :epic
  :set :expert
  :class :priest
  :cost 3
  :spell #(if (= (:name (hero-power %)) "Mind Spike")
    ((set-hero-power "Mind Shatter") %)
    ((set-hero-power "Mind Spike") %))
}
{
  :id 50
  :name "Shield Slam"
  :quality :epic
  :set :expert
  :class :warrior
  :cost 1
  :spell (target :minion #((damage-target (hero-armor %)) %))
}
{
  :id 210
  :name "Snake Trap"
  :quality :epic
  :set :expert
  :class :hunter
  :cost 2
  :secret {
    ; 512 = 1/1 Snake
    :when-my-minion-attacked (comp (end-secret) (summon-minion (repeat 3 512)))
  }
}
{
  :id 324
  :name "Southsea Captain"
  :quality :epic
  :set :expert
  :cost 3
  :minion {
    :attack 3
    :health 3
    :type :pirate
    :aura {:filter [:my :pirate] :attack #(+ 1 %) :health #(+ 1 %)}
  }
}
{
  :id 309
  :name "Spellbender"
  :quality :epic
  :set :expert
  :class :mage
  :cost 3
  :secret {
    ; before
    :when-opponent-spell-cast #(if
      (and (:minion (current-target %)) (is-friendly (current-target %)))
      (-> %
        (summon-minion 645) ; 645 = 1/3 Spellbender
        (end-secret)))
  }
}
{
  :id 645
  :name "Spellbender"
  :quality :epic
  :set :expert
  :class :mage
  :cost 0
  :minion {
    :attack 1
    :health 3
  }
}
{
  :id 567
  :name "Sword of Justice"
  :quality :epic
  :set :expert
  :class :paladin
  :cost 3
  :weapon {
    :attack 1
    :durability 3
    :when-my-minion-summoned (comp (buff-self 1 1) (buff-weapon 0 -1))
  }
}
{
  :id 398
  :name "Twisting Nether"
  :quality :epic
  :set :expert
  :class :warlock
  :cost 8
  :spell (destroy-minions)
}
; Rares
{
  :id 597
  :name "Abomination"
  :quality :rare
  :set :expert
  :cost 5
  :minion {
    :attack 4
    :health 4
    :properties [:taunt]
    :deathrattle (damage-all 2)
  }
}
{ ; todo: be certain swap-with-target works essentially like brewmaster + summon
  :id 425
  :name "Alarm-o-Bot"
  :quality :rare
  :set :expert
  :cost 3
  :minion {
    :attack 0
    :health 3
    :start-of-my-turn #(if (> (count (filter-all % [:my :drawn :minion])) 0)
      ((target-random [:my :drawn :minion]
        (swap-with-target)) %))
  }
}
{
  :id 23
  :name "Aldor Peacekeeper"
  :quality :rare
  :set :expert
  :class :paladin
  :cost 3
  :minion {
    :attack 3
    :health 3
    :battlecry (target [:opponent :minion]
      (buff-target #(1) 0))
  }
}
{
  :id 526
  :name "Ancestral Spirit"
  :quality :rare
  :set :expert
  :class :shaman
  :cost 2
  :spell (target [:minion]
    (give-target {:deathrattle #((summon-minion (self %)) %)}))
}
{
  :id 176
  :name "Ancient Mage"
  :quality :rare
  :set :expert
  :cost 4
  :minion {
    :attack 2
    :health 5
    :battlecry (target-all :adjacent
      (give-target {:spell-damage 1}))
  }
}
{
  :id 153
  :name "Ancient Watcher"
  :quality :rare
  :set :expert
  :cost 2
  :minion {
    :attack 4
    :health 5
    :properties [:cant-attack]
  }
}
{
  :id 57
  :name "Angry Chicken"
  :quality :rare
  :set :expert
  :cost 1
  :minon {
    :attack 1
    :health 1
    :type :beast
    :enrage (buff-self 5 0)
  }
}
{
  :id 97
  :name "Arcane Golem"
  :quality :rare
  :set :expert
  :cost 3
  :minion {
    :attack 4
    :health 2
    :properties [:charge]
    :battlecry (target [:opponent :hero]
      (give-target {:mana-crystal 1}))
  }
}
{
  :id 463
  :name "Argent Commander"
  :quality :rare
  :set :expert
  :cost 6
  :minion {
    :attack 4
    :health 2
    :properties [:charge :divine-shield]
  }
}
{
  :id 644
  :name "Armorsmith"
  :quality :rare
  :set :expert
  :class :warrior
  :cost 2
  :minion {
    :attack 1
    :health 4
    :when-my-minion-damaged (target [:my :hero]
      (give-target {:armor 1}))
  }
}
{
  :id 656
  :name "Auchenai Soulpriest"
  :quality :rare
  :set :expert
  :class :priest
  :cost 4
  :minion {
    :attack 3
    :health 5
    ; handle this in restore-health, apply aura to everything as a signal
    :aura :auchenai-soulpriest-aura
  }
}
{
  :id 280
  :name "Azure Drake"
  :quality :rare
  :set :expert
  :cost 5
  :minion {
    :attack 4
    :health 4
    :type :dragon
    :spell-damage 1
    :battlecry (draw-cards 1)
  }
}
{
  :id 266
  :name "Bite"
  :quality :rare
  :set :expert
  :class :druid
  :cost 4
  :spell (target [:my :hero] (give-target {:armor 4 :attack-this-turn 4}))
}
{
  :id 244
  :name "Blade Flurry"
  :quality :rare
  :set :expert
  :class :rogue
  :cost 2
  :spell #(-> %
    (destroy-my-weapon)
    (damage-all (my-weapon-durability %)))
}
{
  :id 7
  :name "Blessed Champion"
  :quality :rare
  :set :expert
  :class :paladin
  :cost 5
  :spell (target [:minion]
    (give-target {:attack (partial * 2)}))
}
{
  :id 276
  :name "Blizzard"
  :quality :rare
  :set :expert
  :class :mage
  :cost 6
  :spell (target-all [:opponent :minion]
    #(-> %
      (damage-target 2)
      (give-target :freeze)))
}
{
  :id 453
  :name "Bloodsail Corsair"
  :quality :rare
  :set :expert
  :cost 1
  :minion {
    :attack 1
    :health 2
    :type :pirate
    :battlecry (target [:opponent :weapon] (give-target {:durability -1}))
  }
}
{
  :id 88
  :name "Coldlight Oracle"
  :quality :rare
  :set :expert
  :cost 3
  :minion {
    :attack 2
    :health 2
    :type :murloc
    :battlecry #(-> % (draw-cards 2) (draw-opponent-cards 2))
  }
}
{
  :id 424
  :name "Coldlight Seer"
  :quality :rare
  :set :expert
  :cost 3
  :minion {
    :attack 2
    :health 3
    :type :murloc
    :battlecry (target-all :murloc (buff-target 2 0))
  }
}
{
  :id 166
  :name "Commanding Shout"
  :quality :rare
  :set :expert
  :class :warrior
  :cost 2
  :spell #(-> %
    (target-all [:my :minion] (give-target {:minimum-health 1}))
    (draw-cards 1))
}
{
  :id 531
  :name "Counterspell"
  :quality :rare
  :set :expert
  :class :mage
  :cost 3
  :secret {
    :when-opponent-spell-cast (counter-spell)
  }
}
{
  :id 612
  :name "Crazed Alchemist"
  :quality :rare
  :set :expert
  :cost 2
  :minion {
    :attack 2
    :health 2
    :battlecry (target [:minion] #(let [
      attack (:attack (current-target %))
      health (:health (current-target %))
      ] (-> %
      ; so... silence interaction.  I think this is right.  Silence doesn't
      ; appear to rely very much on history.
      (set-target {:health attack :max-health attack})
      (set-target {:attack health}))))
  }
}
{
  :id 542
  :name "Defender of Argus"
  :quality :rare
  :set :expert
  :cost 4
  :minion {
    :attack 3
    :health 3
    :battlecry (target-all :adjacent
      (give-target {:health 1 :attack 1 :taunt true}))
  }
}
{
  :id 212
  :name "Demolisher"
  :quality :rare
  :set :expert
  :cost 3
  :minion {
    :attack 1
    :health 4
    :start-of-my-turn (target-random [:opponent]
      (damage-target 2))
  }
}
{
  :id 581
  :name "Divine Favor"
  :quality :rare
  :set :expert
  :class :paladin
  :cost 3
  :spell #((draw-cards (-
    (count (filter-all % [:opponent :drawn]))
    (count (filter-all % [:my :drawn])))) %)
}
{
  :id 507
  :name "Doomguard"
  :quality :rare
  :set :expert
  :class :warlock
  :cost 5
  :minion {
    :attack 5
    :health 7
    :type :demon
    :properties [:charge]
    :battlecry (target-random [2 :my :drawn] (destroy-target))
  }
}
{
  :id 363
  :name "Eaglehorn Bow"
  :quality :rare
  :set :expert
  :class :hunter
  :cost 3
  :weapon {
    :attack 3
    :durability 2
    :when-secret-revealed (buff-self 0 1)
  }
}
{
  :id 625
  :name "Emperor Cobra"
  :quality :rare
  :set :expert
  :cost 3
  :minion {
    :attack 2
    :health 3
    :type :beast
    :properties [:poison]
  }
}
{
  :id 383
  :name "Equality"
  :quality :rare
  :set :expert
  :class :paladin
  :cost 2
  :spell (target-all [:minion] (set-target {:health 1}))
}
{
  :id 125
  :name "Ethereal Arcanist"
  :quality :rare
  :set :expert
  :class :mage
  :cost 4
  :minion {
    :attack 3
    :health 3
    :end-of-my-turn #(if
      (> (count (filter-all % [:my :secret :played])) 0)
      ((buff-self 2 2) %))
  }
}
{
  :id 114
  :name "Explosive Shot"
  :quality :rare
  :set :expert
  :class :hunter
  :cost 5
  :spell (comp
    (target-all :adjacent-to-target (damage-target 2))
    (target [:minion] (damage-target 5)))
}
{
  :id 236
  :name "Felguard"
  :quality :rare
  :set :expert
  :class :warlock
  :cost 3
  :minion {
    :attack 3
    :health 5
    :type :demon
    :battlecry (target [:my :hero] (give-target {:mana-crystal -1}))
  }
}
{
  :id 214
  :name "Feral Spirit"
  :quality :rare
  :set :expert
  :class :shaman
  :cost 3
  :overload 2
  :spell (summon-minion (repeat 2 "Spirit Wolf"))
}
{
  :id 630
  :name "Flare"
  :quality :rare
  :set :expert
  :class :hunter
  :cost 1
  :spell #(-> %
    (target-all :minion (set-target {:stealth false}))
    ; note: this destroys counterspell before it triggers
    (target-all [:opponent :secret] (destroy-target))
    (draw-cards 1))
}
{
  :id 69
  :name "Frothing Berserker"
  :quality :rare
  :set :expert
  :class :warrior
  :cost 3
  :minion {
    :attack 2
    :health 4
    :when-minion-damaged (buff-self 1 0)
  }
}
{
  :id 131
  :name "Gadgetzan Auctioneer"
  :quality :rare
  :set :expert
  :cost 5
  :minion {
    :attack 4
    :health 4
    :when-my-spell-cast (draw-cards 1)
  }
}
{
  :id 135
  :name "Headcrack"
  :quality :rare
  :set :expert
  :class :rogue
  :cost 3
  :spell (target [:opponent :hero] (damage-target 2))
  :combo (once-my-next-turn (give-card "Headcrack"))
}
{
  :id 457
  :name "Holy Fire"
  :quality :rare
  :set :expert
  :class :priest
  :cost 6
  :spell #(-> %
    (target [] (damage-target 5))
    (target [:my :hero] (restore-health 5)))
}
{
  :id 355
  :name "Holy Wrath"
  :quality :rare
  :set :expert
  :class :paladin
  :cost 5
  ; let this be known as the first time I've restored to let
  :spell (target [] #(let [s ((draw-cards 1) %)]
    (-> s (damage-target (:cost (last-card-drawn s))))))
}
{
  :id 689
  :name "Hyena"
  :quality :rare
  :set :expert
  :class :hunter
  :cost 2
  :minion {
    :attack 2
    :health 2
    :type :beast
  }
}
{
  :id 321
  :name "Imp"
  :quality :rare
  :set :expert
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :demon
  }
}
{
  :id 178
  :name "Imp Master"
  :quality :rare
  :set :expert
  :cost 3
  :minion {
    :attack 1
    :health 5
    :end-of-my-turn #(-> %
      (target [:self] (damage-target 1))
      (summon-minion 321)) ; 321 = 1/1 Imp
  }
}
{
  :id 209
  :name "Injured Blademaster"
  :quality :rare
  :set :expert
  :cost 3
  :minion {
    :attack 4
    :health 7
    :battlecry (target [:self] (damage-target 4))
  }
}
{
  :id 459
  :name "Keeper of the Grove"
  :quality :rare
  :set :expert
  :class :druid
  :cost 4
  :minion {
    :attack 2
    :health 4
    :battlecry (choose "Moonfire" "Dispel")
      ; (target [] (damage-target 2))
      ; (target [:minion] (silence-target)))
  }
}
{
  :id 411
  :name "Kirin Tor Mage"
  :quality :rare
  :set :expert
  :class :mage
  :cost 3
  :minion {
    :attack 4
    :health 3
    :battlecry (once-this-turn (secrets-cost 0))
  }
}
{
  :id 422
  :name "Knife Juggler"
  :quality :rare
  :set :expert
  :cost 2
  :minion {
    :attack 3
    :health 2
    :when-my-minion-summoned (target-random [:opponent] (damage-target 1))
  }
}
{
  :id 679
  :name "Lava Burst"
  :quality :rare
  :set :expert
  :class :shaman
  :cost 3
  :overload 2
  :spell (target [] (damage-target 5))
}
{
  :id 676
  :name "Lightning Storm"
  :quality :rare
  :set :expert
  :class :shaman
  :cost 3
  :overload 2
  :spell (target-all [:opponent :minion]
    #((damage-target (rand-nth [2 3])) %))
}
{
  :id 436
  :name "Lightwarden"
  :quality :rare
  :set :expert
  :cost 1
  :minion {
    :attack 1
    :health 2
    :when-character-healed (buff-self 2 0)
  }
}
{
  :id 117
  :name "Lightwell"
  :quality :rare
  :set :expert
  :class :priest
  :cost 2
  :minion {
    :attack 0
    :health 5
    :start-of-my-turn (target-random [:my :damaged]
      (restore-health 3))
  }
}
{
  :id 67
  :name "Mana Addict"
  :quality :rare
  :set :expert
  :cost 2
  :minion {
    :attack 1
    :health 3
    :when-my-spell-cast (target [:self] (give-target {:attack-this-turn 2}))
  }
}
{
  :id 613
  :name "Mana Tide Totem"
  :quality :rare
  :set :expert
  :class :shaman
  :cost 3
  :minion {
    :attack 0
    :health 3
    :type :totem
    :end-of-my-turn (draw-cards 1)
  }
}
{
  :id 197
  :name "Mana Wraith"
  :quality :rare
  :set :expert
  :cost 2
  :minion {
    :attack 2
    :health 2
    :aura {:filter :hero :minions-cost inc}
  }
}
{
  :id 249
  :name "Mass Dispel"
  :quality :rare
  :set :expert
  :class :priest
  :cost 4
  :spell (target-all [:opponent :minion] (silence-target))
}
{
  :id 127
  :name "Master of Disguise"
  :quality :rare
  :set :expert
  :class :rogue
  :cost 4
  :minion {
    :attack 4
    :health 4
    :battlecry (target [:my :minion] (give-target :stealth))
  }
}
{
  :id 584
  :name "Master Swordsmith"
  :quality :rare
  :set :expert
  :cost 2
  :minion {
    :attack 1
    :health 3
    :end-of-my-turn (target-random [:my :minion {:not :self}]
      (buff-target 1 0))
  }
}
{
  :id 368
  :name "Mind Control Tech"
  :quality :rare
  :set :expert
  :cost 3
  :minion {
    :attack 3
    :health 3
    :battlecry #(if (>= (count (filter-all [:opponent :minion])) 4)
      (target-random [:opponent :minion] (mind-control-target)))
  }
}
{
  :id 447
  :name "Misdirection"
  :quality :rare
  :set :expert
  :class :hunter
  :cost 2
  :secret {
    :when-my-hero-attacked (target-random [{:not :attacker}]
      (redirect-attack-target))
  }
}
{
  :id 345
  :name "Mortal Strike"
  :quality :rare
  :set :expert
  :class :warrior
  :cost 4
  :spell #(if (<= (hero-health %) 12)
    (target [] (damage-target 6))
    (target [] (damage-target 4)))
}
{
  :id 420
  :name "Murloc Tidecaller"
  :quality :rare
  :set :expert
  :cost 1
  :minion {
    :attack 1
    :health 2
    :type :murloc
    :when-minion-summoned #(if
      (= (:type (last-minion-summoned %)) :murloc)
      ((buff-self 1 0) %))
  }
}
{
  :id 120
  :name "Nourish"
  :quality :rare
  :set :expert
  :class :druid
  :cost 5
  :spell (choose 485 58)
    ; (target [:my :hero] (give-target {:mana-crystal 2}))
    ; (draw-cards 3))
}
{
  :id 82
  :name "Perdition's Blade"
  :quality :rare
  :set :expert
  :class :rogue
  :cost 3
  :weapon {
    :attack 2
    :durability 2
    :battlecry (combo
      (target [] (damage-target 1))
      (target [] (damage-target 2)))
  }
}
{
  :id 54
  :name "Pint-Sized Summoner"
  :quality :rare
  :set :expert
  :cost 2
  :minion {
    :attack 2
    :health 2
    :aura {:filter [:my :hero] :first-minion-costs dec}
  }
}
{
  :id 157
  :name "Questing Adventurer"
  :quality :rare
  :set :expert
  :cost 3
  :minion {
    :attack 2
    :health 2
    :when-my-card-played (buff-self 1 1)
  }
}
{
  :id 518
  :name "Ravenholdt Assassin"
  :quality :rare
  :set :expert
  :cost 7
  :minion {
    :attack 7
    :health 5
    :properties [:stealth]
  }
}
{
  :id 148
  :name "Savagery"
  :quality :rare
  :set :expert
  :class :druid
  :cost 1
  :spell (target []
    #((damage-target (hero-attack %)) %))
}
{
  :id 8
  :name "Savannah Highmane"
  :quality :rare
  :set :expert
  :class :hunter
  :cost 6
  :minion {
    :attack 6
    :health 5
    :type :beast
    :deathrattle (summon-minion (repeat 2 "Hyena"))
  }
}
{
  :id 483
  :name "Secretkeeper"
  :quality :rare
  :set :expert
  :cost 1
  :minion {
    :attack 1
    :health 2
    :when-secret-played (buff-self 1 1)
  }
}
{
  :id 442
  :name "Shadow Madness"
  :quality :rare
  :set :expert
  :class :priest
  :cost 4
  :spell (target [:opponent :minion #(<= (:attack %) 3)]
    (mind-control-target))
}
{
  :id 673
  :name "Shadowflame"
  :quality :rare
  :set :expert
  :class :warlock
  :cost 4
  :spell (target [:my :minion] #(-> %
    (target-all [:opponent :minion]
      (damage-target (:attack (current-target %))))
    (destroy-target)))
}
{
  :id 286
  :name "SI:7 Agent"
  :quality :rare
  :set :expert
  :class :rogue
  :cost 3
  :minion {
    :attack 3
    :health 3
    :combo (target [] (damage-target 2))
  }
}
{
  :id 573
  :name "Siphon Soul"
  :quality :rare
  :set :expert
  :class :warlock
  :cost 6
  :spell (target [:minion] #(-> %
    (destroy-target)
    (target [:my :hero] (restore-health 3))))
}
{
  :id 451
  :name "Spirit Wolf"
  :quality :rare
  :set :expert
  :class :shaman
  :cost 2
  :minion {
    :attack 2
    :health 3
    :properties [:taunt]
  }
}
{
  :id 389
  :name "Stampeding Kodo"
  :quality :rare
  :set :expert
  :cost 5
  :minion {
    :attack 3
    :health 5
    :type :beast
    :battlecry (target-random [:opponent :minion #(<= (:attack %) 2)]
      (destroy-target))
  }
}
{
  :id 464
  :name "Starfall"
  :quality :rare
  :set :expert
  :class :druid
  :cost 5
  :spell (choose 195 653)
    ; (target [:minion] (damage-target 5))
    ; (target-all [:opponent :minion] (damage-target 2)))
}
{
  :id 372
  :name "Sunfury Protector"
  :quality :rare
  :set :expert
  :cost 2
  :minion {
    :attack 2
    :health 3
    :battlecry (target-all [:adjacent] (give-target :taunt))
  }
}
{
  :id 221
  :name "Sunwalker"
  :quality :rare
  :set :expert
  :cost 6
  :minion {
    :attack 4
    :health 5
    :properties [:taunt :divine-shield]
  }
}
{
  :id 360
  :name "Twilight Drake"
  :quality :rare
  :set :expert
  :cost 4
  :minion {
    :attack 4
    :health 1
    :type :dragon
    :battlecry #(buff-self 0 (count (filter-all [:my :drawn])))
  }
}
{
  :id 638
  :name "Upgrade!"
  :quality :rare
  :set :expert
  :class :warrior
  :cost 1
  :spell (target [:my :hero] #(if (:weapon (current-target %))
    ((buff-weapon 1 1) %)
    ((equip-weapon "Heavy Axe") %)))
}
{
  :id 160
  :name "Vaporize"
  :quality :rare
  :set :expert
  :class :mage
  :cost 3
  :secret {
    :when-my-hero-attacked (target [:attacker] #(if (:minion (current-target %))
      (destroy-target)))
  }
}
{
  :id 523
  :name "Violet Teacher"
  :quality :rare
  :set :expert
  :cost 4
  :minion {
    :attack 3
    :health 5
    :when-my-spell-cast (summon-minion "Violet Apprentice")
  }
}
{
  :id 119
  :name "Void Terror"
  :quality :rare
  :set :expert
  :class :warlock
  :cost 3
  :minion {
    :attack 3
    :health 3
    :type :demon
    :battlecry (target-all [:adjacent] #(let [
      attack (:attack (current-target %))
      health (:health (current-target %))
      ] (-> % (buff-self attack health) (destroy-target))))
  }
}
{
  :id 25
  :name "Wild Pyromancer"
  :quality :rare
  :set :expert
  :cost 2
  :minion {
    :attack 3
    :health 2
    ; after
    :when-my-spell-cast (target-all [:minion] (damage-target 1))
  }
}
{
  :id 123
  :name "Young Priestess"
  :quality :rare
  :set :expert
  :cost 1
  :minion {
    :attack 2
    :health 1
    :end-of-my-turn (target-random [:my :minion {:not :self}]
      (buff-target 0 1))
  }
}
; Commons
{
  :id 577
  :name "Abusive Sergeant"
  :quality :common
  :set :expert
  :cost 1
  :minion {
    :attack 2
    :health 1
    :battlecry (target [:my :minion]
      (give-target {:attack-this-turn 2}))
  }
}
{
  :id 428
  :name "Acolyte of Pain"
  :quality :common
  :set :expert
  :cost 3
  :minion {
    :attack 1
    :health 3
    :when-self-damaged (draw-cards 1)
  }
}
{
  :id 641
  :name "Amani Berserker"
  :quality :common
  :set :expert
  :cost 2
  :minion {
    :attack 2
    :health 3
    :enrage (buff-self 3 0)
  }
}
{
  :id 572
  :name "Ancient Brewmaster"
  :quality :common
  :set :expert
  :cost 4
  :minion {
    :attack 5
    :health 4
    :battlecry (target [:my :minion]
      (return-target))
  }
}
{
  :id 243
  :name "Ancient Secrets"
  :quality :free
  :set :expert

  :class :druid
  :cost 0
  :spell (target [] (restore-health 5))
}
{
  :id 517
  :name "Ancient Teachings"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (draw-cards 2)
}
{
  :id 504
  :name "Arathi Weaponsmith"
  :quality :common
  :set :expert
  :class :warrior
  :cost 4
  :minion {
    :attack 3
    :health 3
    :battlecry (equip-weapon "Battle Axe")
  }
}
{
  :id 191
  :name "Argent Protector"
  :quality :common
  :set :expert
  :class :paladin
  :cost 2
  :minion {
    :attack 2
    :health 2
    :battlecry (target [:my :minion] ; todo: figure out {:not :self} stuff
      (give-target :divine-shield))
  }
}
{
  :id 473
  :name "Argent Squire"
  :quality :common
  :set :expert
  :cost 1
  :minion {
    :attack 1
    :health 1
    :properties [:divine-shield]
  }
}
{
  :id 231
  :name "Bananas"
  :quality :free
  :set :expert
  :cost 1
  :spell (target [:minion] (buff-target 1 1))
}
{
  :id 403
  :name "Battle Axe"
  :quality :free
  :set :expert
  :class :warrior
  :cost 1
  :weapon {
    :attack 2
    :durability 2
  }
}
{
  :id 664
  :name "Battle Rage"
  :quality :common
  :set :expert
  :class :warrior
  :cost 2
  :spell (target-all [:my :damaged] (draw-cards 1))
}
{
  :id 662
  :name "Bear Form"
  :quality :common
  :set :expert
  :class :druid
  :cost 0
  ; question: does choose one force the target?
  ; todo: :self / poly
  :spell (target [:minion] (give-target {:health 2 :taunt true}))
}
{
  :id 198
  :name "Betrayal"
  :quality :common
  :set :expert
  :class :rogue
  :cost 2
  :spell (target [:opponent :minion]
    ; todo: account for poison
    #(let [damage (:attack (current-target %))]
      ((target-all [:adjacent-to-target] (damage-target damage)) %)))
}
{
  :id 100
  :name "Blessing of Wisdom"
  :quality :common
  :set :expert
  :class :paladin
  :cost 1
  :spell (target [:minion] (give-target {
    ; todo: make sure this draws the caster cards... i.e. draw-cards generates
    ; in the context of the targeter, not the target.
    :when-minion-attacks (draw-cards 1)}))
}
{
  :id 669
  :name "Blood Fury"
  :quality :free
  :set :expert
  :class :warlock
  :cost 3
  :weapon { ; Jaraxxus
    :attack 3
    :durability 8
  }
}
{
  :id 196
  :name "Blood Imp"
  :quality :common
  :set :expert
  :class :warlock
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :demon
    :properties :stealth
    :aura {:filter [:my :minion] :health inc}
  }
}
{
  :id 637
  :name "Bloodsail Raider"
  :quality :common
  :set :expert
  :cost 2
  :minion {
    :attack 2
    :health 3
    :type :pirate
    :battlecry #(let [attack (or 0 (:attack (:weapon (my-hero %))))]
      (-> % (buff-self attack 0)))
  }
}
{
  :id 287
  :name "Cat Form"
  :quality :common
  :set :expert
  :class :druid
  :cost 0
  :spell (target [:minion] (give-target :charge)) ; todo: :self / poly
}
{
  :id 38
  :name "Circle of Healing"
  :quality :common
  :set :expert
  :class :priest
  :cost 0
  :spell (target-all [:minion] (restore-health 4))
}
{
  :id 92
  :name "Cold Blood"
  :quality :common
  :set :expert
  :class :rogue
  :cost 1
  :spell (target [:minion] (combo (buff-target 2 0) (buff-target 4 0)))
}
{
  :id 284
  :name "Conceal"
  :quality :common
  :set :expert
  :class :rogue
  :cost 1
  :spell (target-all [:my :minion] (give-target
    {:stealth-this-turn true :stealth-next-turn true}))
}
{
  :id 26
  :name "Cone of Cold"
  :quality :common
  :set :expert
  :class :mage
  :cost 4
  :spell (target [:minion] #(let [
    chill (fn [s] (-> s (damage-target 1) (give-target :freeze)))
    ] (-> % chill (target-all [:adjacent-to-target] chill))))
}
{
  :id 328
  :name "Cruel Taskmaster"
  :quality :common
  :set :expert
  :class :warrior
  :cost 2
  :minion {
    :attack 2
    :health 2
    :battlecry (target [:minion]
      #(-> % (damage-target 2) (buff-target 2 0)))
  }
}
{
  :id 140
  :name "Cult Master"
  :quality :common
  :set :expert
  :cost 4
  :minion {
    :attack 4
    :health 2
    :when-my-minion-destroyed (draw-cards 1)
  }
}
{
  :id 200
  :name "Damaged Golem"
  :quality :common
  :set :expert
  :cost 1
  :minion {
    :attack 2
    :health 1
  }
}
{
  :id 128
  :name "Dark Iron Dwarf"
  :quality :common
  :set :expert
  :cost 4
  :minion {
    :attack 4
    :health 4
    :battlecry (target [:minion] (buff-target 2 0))
  }
}
{
  :id 239
  :name "Deadly Shot"
  :quality :common
  :set :expert
  :class :hunter
  :cost 3
  :spell (target-random [:opponent :minion] (destroy-target))
}
{
  :id 318
  :name "Defender"
  :quality :common
  :set :expert
  :class :paladin
  :cost 1
  :minion {
    :attack 2
    :health 1
  }
}
{
  :id 9
  :name "Defias Bandit"
  :quality :free
  :set :expert
  :class :rogue
  :cost 1
  :minion {
    :attack 2
    :health 1
  }
}
{
  :id 417
  :name "Defias Ringleader"
  :quality :common
  :set :expert
  :class :rogue
  :cost 2
  :minion {
    :attack 2
    :health 2
    :combo (summon-minion "Defias Bandit")
  }
}
{
  :id 358
  :name "Demigod's Favor"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target-all [:my :minion {:not :self}] (buff-target 2 2))
}
{
  :id 452
  :name "Demonfire"
  :quality :common
  :set :expert
  :class :warlock
  :cost 2
  :spell (target [:minion] #(let [target (current-target %)]
    (if (and (is-friendly target) (:demon target))
      (buff-target 2 2)
      (damage-target 2))))
}
{
  :id 354
  :name "Devilsaur"
  :quality :common
  :set :expert
  :cost 5
  :minion {
    :attack 5
    :health 5
    :type :beast
  }
}
{
  :id 305
  :name "Dire Wolf Alpha"
  :quality :common
  :set :expert
  :cost 2
  :minion {
    :attack 2
    :health 2
    :type :beast
    :aura {:filter :adjacent :attack inc}
  }
}
{
  :id 524
  :name "Dispel"
  :quality :free
  :set :expert
  :class :druid
  :spell (target [:minion] (silence-target))
}
{
  :id 261
  :name "Dread Corsair"
  :quality :common
  :set :expert
  :cost #(- 4 (or 0 (:attack (:weapon (my-hero %)))))
  :minion {
    :attack 3
    :health 3
    :type :pirate
    :properties [:taunt]
  }
}
{
  :id 561
  :name "Dream"
  :quality :free
  :set :expert
  :cost 0
  :spell (target [:minion] (return-target))
}
{
  :id 587
  :name "Druid of the Claw"
  :quality :common
  :set :expert
  :class :druid
  :cost 5
  :minion {
    :attack 4
    :health 4
    :battlecry (choose "Cat Form" "Bear Form")
  }
}
; next two are polymorph results of Cat Form / Bear Form.  not sure how the
; spells should be implemented... interaction with spell copies e.g. lorewalker
; cho versus interaction with minion copies e.g. faceless manipulator.  could be
; both
{
  :id 408
  :name "Druid of the Claw"
  :quality :common
  :set :expert
  :class :druid
  :cost 5
  :minion {
    :attack 4
    :health 4
    :properties [:charge]
  }
}
{
  :id 45
  :name "Druid of the Claw"
  :quality :common
  :set :expert
  :class :druid
  :cost 5
  :minion {
    :attack 4
    :health 6
    :properties [:taunt]
  }
}
{
  :id 129
  :name "Dust Devil"
  :quality :common
  :set :expert
  :class :shaman
  :cost 1
  :overload 2
  :minion {
    :attack 3
    :health 1
    :properties [:windfury]
  }
}
{
  :id 77
  :name "Earth Shock"
  :quality :common
  :set :expert
  :class :shaman
  :cost 1
  :spell (target [:minion] #(-> % (silence-target) (damage-target 1)))
}
{
  :id 557
  :name "Earthen Ring Farseer"
  :quality :common
  :set :expert
  :cost 3
  :minion {
    :attack 3
    :health 3
    :battlecry (target [] (restore-health 3))
  }
}
{
  :id 534
  :name "Emerald Drake"
  :quality :free
  :set :expert
  :cost 4
  :minion {
    :attack 7
    :health 6
    :type :dragon
  }
}
{
  :id 382
  :name "Eviscerate"
  :quality :common
  :set :expert
  :class :rogue
  :cost 2
  :spell (target [] (combo (damage-target 2) (damage-target 4)))
}
{
  :id 344
  :name "Explosive Trap"
  :quality :common
  :set :expert
  :class :hunter
  :cost 2
  :secret {
    :when-my-hero-attacked (target-all [:opponent] (damage-target 2))
  }
}
{
  :id 206
  :name "Eye for an Eye"
  :quality :common
  :set :expert
  :class :paladin
  :cost 1
  :secret {
    :when-my-hero-damaged (target [:opponent :hero]
      #((damage-target (damage-taken %)) %))
  }
}
{
  :id 213
  :name "Faerie Dragon"
  :quality :common
  :set :expert
  :cost 2
  :minion {
    :attack 3
    :health 2
    :type :dragon
    :properties [:spell-immunity] ; todo: figure out a name for this
  }
}
{
  :id 476
  :name "Fen Creeper"
  :quality :common
  :set :expert
  :cost 5
  :minion {
    :attack 3
    :health 6
    :properties [:taunt]
  }
}
{
  :id 85
  :name "Flame Imp"
  :quality :common
  :set :expert
  :class :warlock
  :cost 1
  :minion {
    :attack 3
    :health 2
    :type :demon
    :battlecry (target [:my :hero] (damage-target 3))
  }
}
{
  :id 685
  :name "Flame of Azzinoth"
  :quality :free
  :set :expert
  :cost 1
  :minion {
    :attack 2
    :health 1
  }
}
{
  :id 610
  :name "Flesheating Ghoul"
  :quality :common
  :set :expert
  :cost 3
  :minion {
    :attack 2
    :health 3
    :when-minion-destroyed (buff-self 1 0)
  }
}
{
  :id 530
  :name "Forked Lightning"
  :quality :common
  :set :expert
  :class :shaman
  :cost 1
  :overload 2
  :spell (target-random [:opponent :minion] (damage-target 2))
}
{
  :id 99
  :name "Freezing Trap"
  :quality :common
  :set :expert
  :class :hunter
  :cost 2
  :secret {
    ; todo: make sure this supplies attacker as the target
    :when-opponent-minion-attacks #(-> %
      (return-target)
      (give-target {:cost 2}))
  }
}
{
  :id 598
  :name "Frost Elemental"
  :quality :common
  :set :expert
  :cost 6
  :minion {
    :attack 5
    :health 5
    :battlecry (target [:minion] (give-target :freeze))
  }
}
{
  :id 565
  :name "Gnoll"
  :quality :free
  :set :expert
  :cost 2
  :minion {
    :attack 2
    :health 2
    :properties [:taunt]
  }
}
{
  :id 386
  :name "Harvest Golem"
  :quality :common
  :set :expert
  :cost 3
  :minion {
    :attack 2
    :health 3
    :deathrattle (summon-minion "Damaged Golem")
  }
}
{
  :id 583
  :name "Heavy Axe"
  :quality :free
  :set :expert
  :class :warrior
  :cost 1
  :weapon {
    :attack 1
    :durability 3
  }
}
{
  :id 672
  :name "Ice Barrier"
  :quality :common
  :set :expert
  :class :mage
  :cost 3
  :secret {
    ; todo: trigger this before the damage is calculated (rewrite ice block?)
    :when-my-hero-attacked (target [:my :hero] (give-target {:armor 8}))
  }
}
{
  :id 188
  :name "Ice Lance"
  :quality :common
  :set :expert
  :class :mage
  :cost 1
  :spell (target [] #(if (:freeze (current-target %))
    (damage-target 4)
    (give-target :freeze)))
}
{
  :id 121
  :name "Infernal"
  :quality :common
  :set :expert
  :class :warlock
  :cost 6
  :minion {
    :attack 6
    :health 6
    :type :demon
  }
}
{
  :id 83
  :name "INFERNO!"
  :quality :free
  :set :expert
  :class :warlock
  :cost 2
  :hero-power (summon-minion "Infernal")
}
{
  :id 207
  :name "Inner Fire"
  :quality :common
  :set :expert
  :class :priest
  :cost 1
  :spell (target [:minion] #(let [health (:health (current-target))]
    (-> % (set-target {:attack health}))))
}
{
  :id 366
  :name "Inner Rage"
  :quality :common
  :set :expert
  :class :warrior
  :cost 0
  :spell (target [:minion] #(-> % (damage-target 1) (buff-target 2 0)))
}
{
  :id 500
  :name "Ironbeak Owl"
  :quality :common
  :set :expert
  :cost 2
  :minion {
    :attack 2
    :health 1
    :type :beast
    :battlecry (target [:minion] (silence-target))
  }
}
{
  :id 392
  :name "Jungle Panther"
  :quality :common
  :set :expert
  :cost 3
  :minion {
    :attack 4
    :health 2
    :type :beast
    :properties [:stealth]
  }
}
{
  :id 116
  :name "Laughing Sister"
  :quality :free
  :set :expert
  :cost 3
  :minion {
    :attack 3
    :health 5
    :properties [:spell-immunity]
  }
}
{
  :id 204
  :name "Leader of the Pack"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target-all [:my :minion] (buff-target 1 1))
}
{
  :id 513
  :name "Leper Gnome"
  :quality :common
  :set :expert
  :cost 1
  :minion {
    :attack 2
    :health 1
    :deathrattle (target [:opponent :hero] (damage-target 2))
  }
}
{
  :id 10
  :name "Lightning Bolt"
  :quality :common
  :set :expert
  :class :shaman
  :cost 1
  :overload 1
  :spell (target [] (damage-target 3))
}
{
  :id 192
  :name "Lightspawn"
  :quality :common
  :set :expert
  :class :priest
  :cost 4
  :minion {
    :attack 0
    :health 5
    ; just a sketch; needs to be an aura (silencible), but functions are applied
    ; to the values of their keyword, so using keywords instead
    :aura {:filter :self :attack :health}
  }
}
{
  :id 395
  :name "Loot Hoarder"
  :quality :common
  :set :expert
  :cost 2
  :minion {
    :attack 2
    :health 1
    :deathrattle (draw-cards 1)
  }
}
{
  :id 80
  :name "Mad Bomber"
  :quality :common
  :set :expert
  :cost 2
  :minion {
    :attack 3
    :health 2
    :battlecry (apply comp (repeat 3 (target-random [{:not :self}]
      (damage-target 1))))
  }
}
{
  :id 263
  :name "Mana Wyrm"
  :quality :common
  :set :expert
  :class :mage
  :cost 1
  :minion {
    :attack 1
    :health 3
    :when-my-spell-cast (buff-self 1 0)
  }
}
; note: for this to interact with lorewalker cho twice would be dumb
{
  :id 149
  :name "Mark of Nature"
  :quality :common
  :set :expert
  :class :druid
  :cost 3
  :spell (choose 430 133)
}
{
  :id 430
  :name "Mark of Nature"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target [:minion] (buff-target 4 0))
}
{
  :id 133
  :name "Mark of Nature"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target [:minion] (give-target {:health 4 :taunt true}))
}
{
  :id 229
  :name "Mind Shatter"
  :quality :free
  :set :expert
  :class :priest
  :cost 2
  :hero-power (target [] (damage-target 3))
}
{
  :id 70
  :name "Mind Spike"
  :quality :free
  :set :expert
  :class :priest
  :cost 2
  :hero-power (target [] (damage-target 2))
}
{
  :id 569
  :name "Mirror Entity"
  :quality :common
  :set :expert
  :class :mage
  :cost 3
  :secret {
    :when-opponent-minion-summoned #(-> % (summon-minion (current-target %)))
  }
}
{
  :id 346
  :name "Mogu'shan Warden"
  :quality :common
  :set :expert
  :cost 4
  :minion {
    :attack 1
    :health 7
    :properties [:taunt]
  }
}
{
  :id 111
  :name "Moonfire"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target [] (damage-target 2))
}
{
  :id 154
  :name "Naturalize"
  :quality :common
  :set :expert
  :class :druid
  :cost 1
  :spell (target [:minion] #(-> % (destroy-target) (draw-opponent-cards 2)))
}
{
  :id 334
  :name "Nightmare"
  :quality :free
  :set :expert
  :cost 0
  :spell (target [:minion] (give-target {
    :health 5 :attack 5
    :start-of-my-turn (destroy-self)}))
}
{
  :id 158
  :name "Noble Sacrifice"
  :quality :common
  :set :expert
  :class :paladin
  :cost 1
  :secret {
    :when-opponent-minion-attacks #(-> %
      (summon-minion "Defender")
      (target [:last-minion-summoned] (redirect-attack-target)))
  }
}
{
  :id 58
  :name "Nourish"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target [:my :hero] (give-target {:mana-crystal 2}))
}
{
  :id 485
  :name "Nourish"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (draw-cards 3)
}
{
  :id 190
  :name "Panther"
  :quality :common
  :set :expert
  :class :druid
  :cost 2
  :minion {
    :attack 3
    :health 2
    :type :beast
  }
}
{
  :id 165
  :name "Power of the Wild"
  :quality :common
  :set :expert
  :class :druid
  :cost 2
  :spell (choose "Summon a Panther" "Leader of the Pack")
}
{
  :id 170
  :name "Power Overwhelming"
  :quality :common
  :set :expert
  :class :warlock
  :cost 1
  :spell (target [:my :minion] (give-target {
    :health 4 :attack 4
    :end-of-turn (destroy-self)}))
}
{
  :id 138
  :name "Priestess of Elune"
  :quality :common
  :set :expert
  :cost 6
  :minion {
    :attack 5
    :health 4
    :battlecry (target [:my :hero] (restore-health 4))
  }
}
{
  :id 95
  :name "Raging Worgen"
  :quality :common
  :set :expert
  :cost 3
  :minion {
    :attack 3
    :health 3
    :enrage (target [:self] (give-target {:windfury true :attack 1}))
  }
}
{
  :id 454
  :name "Rampage"
  :quality :common
  :set :expert
  :class :warrior
  :cost 2
  :spell (target [:minion :damaged] (buff-target 3 3))
}
{
  :id 657
  :name "Redemption"
  :quality :common
  :set :expert
  :class :paladin
  :cost 1
  :secret {
    :when-my-minion-destroyed #(-> %
      (summon-minion (current-target %))
      ; todo: ensure the resurrectee isn't healable
      (set-target {:health 1 :max-health 1}))
  }
}
{
  :id 642
  :name "Repentance"
  :quality :common
  :set :expert
  :class :paladin
  :cost 1
  :secret {
    ; todo: as above
    :when-opponent-minion-summoned (set-target {:health 1 :max-health 1})
  }
}
{
  :id 375
  :name "Rooted"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target [:self] (give-target {:health 5 :taunt true}))
}
{
  :id 475
  :name "Scarlet Crusader"
  :quality :common
  :set :expert
  :cost 3
  :minion {
    :attack 3
    :health 1
    :properties [:divine-shield]
  }
}
{
  :id 279
  :name "Scavenging Hyena"
  :quality :common
  :set :expert
  :class :hunter
  :cost 2
  :minion {
    :attack 2
    :health 2
    :type :beast
    :when-my-minion-destroyed #(if (:beast (current-target %))
      (buff-self 2 1))
  }
}
{
  :id 327
  :name "Sense Demons"
  :quality :common
  :set :expert
  :class :warlock
  :cost 3
  :spell (target-random [2 :undrawn :demon]
    (draw-target) (give-card "Worthless Imp"))
}
{
  :id 550
  :name "Shadowstep"
  :quality :common
  :set :expert
  :class :rogue
  :cost 0
  :spell (target [:my :minion] #(-> %
    (return-target)
    (give-target {:cost -2})))
}
{
  :id 159
  :name "Shan'do's Lesson"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (summon-minion [181 181]) ; 181 = 2/2 Treant w/ Taunt
}
{
  :id 24
  :name "Shieldbearer"
  :quality :common
  :set :expert
  :cost 1
  :minion {
    :attack 0
    :health 4
    :properties [:taunt]
  }
}
{
  :id 544
  :name "Silence"
  :quality :common
  :set :expert
  :class :priest
  :cost 0
  :spell (target [:minion] (silence-target))
}
{
  :id 648
  :name "Silver Hand Knight"
  :quality :common
  :set :expert
  :cost 5
  :minion {
    :attack 4
    :health 4
    :battlecry (summon-minion "Squire")
  }
}
{
  :id 634
  :name "Silvermoon Guardian"
  :quality :common
  :set :expert
  :cost 4
  :minion {
    :attack 3
    :health 3
    :properties [:divine-shield]
  }
}
{
  :id 255
  :name "Skeleton"
  :quality :free
  :set :expert
  :cost 3
  :minion {
    :attack 3
    :health 3
  }
}
{
  :id 215
  :name "Slam"
  :quality :common
  :set :expert
  :class :warrior
  :cost 2
  :spell (target [:minion] #(-> %
    (damage-target 2)
    (if (not (is-dead (current-target %)))
      (draw-cards 1))))
}
{
  :id 512
  :name "Snake"
  :quality :common
  :set :expert
  :class :hunter
  :cost 0
  :minion {
    :attack 1
    :health 1
    :type :beast
  }
}
{
  :id 553
  :name "Snipe"
  :quality :common
  :set :expert
  :class :hunter
  :cost 2
  :secret {
    :when-opponent-minion-summoned (damage-target 4)
  }
}
{
  :id 4
  :name "Sorcerer's Apprentice"
  :quality :common
  :set :expert
  :class :mage
  :cost 2
  :minion {
    :attack 3
    :health 2
    :aura (spells-cost dec)
  }
}
{
  :id 311
  :name "Soul of the Forest"
  :quality :common
  :set :expert
  :class :druid
  :cost 4
  :spell (target-all [:my :minion]
    (give-target {
      :deathrattle (summon-minion 272)})) ; 272 = 2/2 Treant
}
{
  :id 103
  :name "Southsea Deckhand"
  :quality :common
  :set :expert
  :cost 1
  :minion {
    :attack 2
    :health 1
    :type :pirate
    ; todo: as above
    :aura {:filter :self :charge :weapon-equipped}
  }
}
{
  :id 42
  :name "Spellbreaker"
  :quality :common
  :set :expert
  :cost 4
  :minion {
    :attack 4
    :health 3
    :battlecry (target [:minion] (silence-target))
  }
}
{
  :id 627
  :name "Spiteful Smith"
  :quality :common
  :set :expert
  :class :warrior
  :cost 5
  :minion {
    :attack 4
    :health 6
    :enrage (target [:my :hero] (give-target {:weapon-bonus-attack 2}))
  }
}
{
  :id 21
  :name "Squire"
  :quality :common
  :set :expert
  :cost 1
  :minion {
    :attack 2
    :health 2
  }
}
{
  :id 469
  :name "Squirrel"
  :quality :common
  :set :expert
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :beast
  }
}
{
  :id 653
  :name "Starfall"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target-all [:opponent :minion] (damage-target 2))
}
{
  :id 195
  :name "Starfall"
  :quality :common
  :class :druid
  :cost 0
  :spell (target [:minion] (damage-target 5))
}
{
  :id 152
  :name "Stormforged Axe"
  :quality :common
  :set :expert
  :class :shaman
  :cost 2
  :overload 1
  :weapon {
    :attack 2
    :durability 3
  }
}
{
  :id 338
  :name "Stranglethorn Tiger"
  :quality :common
  :set :expert
  :cost 5
  :minion {
    :attack 5
    :health 5
    :type :beast
    :properties [:stealth]
  }
}
{
  :id 219
  :name "Summon a Panther"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (summon-minion "Panther")
}
{
  :id 566
  :name "Summoning Portal"
  :quality :common
  :set :expert
  :class :warlock
  :cost 4
  :minion {
    :attack 0
    :health 4
    :aura {:filter [:drawn :minion] :minimum-cost 1 :cost (partial + -2)}
  }
}
{
  :id 477
  :name "Tauren Warrior"
  :quality :common
  :set :expert
  :cost 3
  :minion {
    :attack 2
    :health 3
    :properties [:taunt]
    :enrage (buff-self 3 0)
  }
}
{
  :id 232
  :name "Temple Enforcer"
  :quality :common
  :set :expert
  :class :priest
  :cost 6
  :minion {
    :attack 6
    :health 6
    :battlecry (target [:my :minion] (buff-target 0 3))
  }
}
{
  :id 62
  :name "Thoughtsteal"
  :quality :common
  :set :expert
  :class :priest
  :cost 3
  :spell (target-random [2 :opponent :undrawn] #(-> %
    (give-card (:id (current-target %)))))
}
{
  :id 265
  :name "Thrallmar Farseer"
  :quality :common
  :set :expert
  :cost 3
  :minion {
    :attack 2
    :health 3
    :properties [:windfury]
  }
}
{
  :id 272
  :name "Treant"
  :quality :free
  :set :expert
  :class :druid
  :cost 1
  :minion {
    :attack 2
    :health 2
  }
}
{
  :id 337
  :name "Treant"
  :quality :common
  :set :expert
  :class :druid
  :cost 1
  :minion {
    :attack 2
    :health 2
    :properties [:charge]
    :end-of-turn (destroy-self)
  }
}
{
  :id 181
  :name "Treant"
  :quality :free
  :set :expert
  :class :druid
  :cost 1
  :minion {
    :attack 2
    :health 2
    :properties [:taunt]
  }
}
{
  :id 51
  :name "Unbound Elemental"
  :quality :common
  :set :expert
  :class :shaman
  :cost 3
  :minion {
    :attack 2
    :health 4
    :when-my-card-played #(if (> (or 0 (:overload (current-target %))) 0)
      (-> % (buff-self 1 1)))
  }
}
{
  :id 317
  :name "Unleash the Hounds"
  :quality :common
  :set :expert
  :class :hunter
  :cost 4
  :spell (target-all [:opponent :minion] (summon-minion "Hound"))
}
{
  :id 262
  :name "Uproot"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target [:self] (buff-target 5 0))
}
{
  :id 509
  :name "Venture Co. Mercenary"
  :quality :common
  :set :expert
  :cost 5
  :minion {
    :attack 7
    :health 6
    :aura {:filter [:my :hero] :minions-cost #(+ % 3)}
  }
}
{
  :id 63
  :name "Violet Apprentice"
  :quality :free
  :set :expert
  :cost 0
  :minion {
    :attack 1
    :health 1
  }
}
{
  :id 527
  :name "Whelp"
  :quality :free
  :set :expert
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :dragon
  }
}
{
  :id 675
  :name "Windfury Harpy"
  :quality :common
  :set :expert
  :cost 6
  :minion {
    :attack 4
    :health 5
    :properties [:windfury]
  }
}
{
  :id 273
  :name "Wisp"
  :quality :common
  :set :expert
  :cost 0
  :minion {
    :attack 1
    :health 1
  }
}
{
  :id 112
  :name "Worgen Infiltrator"
  :quality :common
  :set :expert
  :cost 1
  :minion {
    :attack 2
    :health 1
    :properties [:stealth]
  }
}
{
  :id 230
  :name "Worthless Imp"
  :quality :common
  :set :expert
  :class :warlock
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :demon
  }
}
{
  :id 633
  :name "Wrath"
  :quality :common
  :set :expert
  :class :druid
  :cost 2
  :spell (choose 234 501)
}
{
  :id 234
  :name "Wrath"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target [:minion] (damage-target 3))
}
{
  :id 501
  :name "Wrath"
  :quality :free
  :set :expert
  :class :druid
  :cost 0
  :spell (target [:minion] #(-> % (damage-target 1) (draw-cards 1)))
}
{
  :id 629
  :name "Young Dragonhawk"
  :quality :common
  :set :expert
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :beast
    :properties [:windfury]
  }
}
{
  :id 247
  :name "Youthful Brewmaster"
  :quality :common
  :set :expert
  :cost 2
  :minion {
    :attack 3
    :health 2
    :battlecry (target [:my :minion] (return-target))
  }
}
{
  :id 235
  :name "Ysera Awakens"
  :quality :free
  :set :expert
  :cost 2
  :spell (target-all [#(not= (:name %) "Ysera")]
    (damage-target 5))
}
{
  :id 74
  :name "Acidic Swamp Ooze"
  :quality :common
  :set :basic
  :cost 2
  :minion {
    :attack 3
    :health 2
    :battlecry (target [:opponent :weapon] (destroy-target))
  }
}
{
  :id 578
  :name "Animal Companion"
  :quality :common
  :set :basic
  :class :hunter
  :cost 3
  :spell #(-> % (summon-minion (rand-nth ["Misha" "Leokk" "Huffer"])))
}
{
  :id 182
  :name "Arcanite Reaper"
  :quality :common
  :set :basic
  :class :warrior
  :cost 5
  :weapon {
    :attack 5
    :durability 2
  }
}
{
  :id 545
  :name "Archmage"
  :quality :common
  :set :basic
  :cost 6
  :minion {
    :attack 4
    :health 7
    :spell-damage 1
  }
}
{
  :id 433
  :name "Assassin's Blade"
  :quality :common
  :set :basic
  :class :rogue
  :cost 5
  :weapon {
    :attack 3
    :durability 4
  }
}
{
  :id 156
  :name "Bananas"
  :quality :common
  :set :missions
  :cost 1
  :spell (target [:my :minion] (buff-target 1 1))
}
{
  :id 376
  :name "Barrel"
  :quality :common
  :set :missions
  :cost 0
  :minion {
    :attack 0
    :health 2
    ; :deathrattle This card isn't used any more? Part of King Mukla tutorial.
  }
}
{
  :id 606
  :name "Barrel Toss"
  :quality :common
  :set :missions
  :cost 1
  :spell (target [] (damage-target 2))
}
{
  :id 29
  :name "Blessing of Kings"
  :quality :common
  :set :basic
  :class :paladin
  :cost 4
  :spell (target [:minion] (buff-target 4 4))
}
{
  :id 256
  :name "Bloodlust"
  :quality :common
  :set :basic
  :class :shaman
  :cost 5
  :spell (target-all [:my :minion] (give-target {:attack-this-turn 3}))
}
{
  :id 289
  :name "Bluegill Warrior"
  :quality :common
  :set :basic
  :cost 2
  :minion {
    :attack 2
    :health 1
    :type :murloc
    :properties [:charge]
  }
}
{
  :id 65
  :name "Boar"
  :quality :common
  :set :basic
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :beast
  }
}
{
  :id 27
  :name "Booty Bay Bodyguard"
  :quality :common
  :set :basic
  :cost 5
  :minion {
    :attack 5
    :health 4
    :properties [:taunt]
  }
}
{
  :id 397
  :name "Brewmaster"
  ; todo: add collectible false to all uncollectibles (or collectible true?)
  :quality :common
  :set :missions
  :cost 4
  :minion {
    :attack 4
    :health 4
  }
}
{
  :id 31
  :name "Chillwind Yeti"
  :quality :common
  :set :basic
  :cost 4
  :minion {
    :attack 4
    :health 5
  }
}
{
  :id 81
  :name "Cleave"
  :quality :common
  :set :basic
  :class :warrior
  :cost 2
  ; todo: determine how to apply spell damage effects
  :spell (target-random [2 :opponent :minion] (damage-target 2))
}
{
  :id 260
  :name "Consecration"
  :quality :common
  :set :basic
  :class :paladin
  :cost 4
  :spell (target-all [:opponent] (damage-target 2))
}
{
  :id 173
  :name "Core Hound"
  :quality :common
  :set :basic
  :cost 7
  :minion {
    :attack 9
    :health 5
    :type :beast
  }
}
{
  :id 252
  :name "Corruption"
  :quality :common
  :set :basic
  :class :warlock
  :cost 1
  :spell (target [:opponent :minion] (give-target
    {:start-of-my-turn (destroy-self)}))
}
{
  :id 377
  :name "Crazed Hunter"
  :quality :common
  :set :missions
  :cost 1
  :minion {
    :attack 1
    :health 1
  }
}
{
  :id 393
  :name "Crazy Monkey"
  :quality :common
  :set :missions
  :cost 1
  :minion {
    :attack 1
    :health 2
    :battlecry (give-opponent-card "Bananas")
  }
}
{
  :id 388
  :name "Dalaran Mage"
  :quality :common
  :set :basic
  :cost 3
  :minion {
    :attack 1
    :health 4
    :spell-damage 1
  }
}
{
  :id 84
  :name "Darkscale Healer"
  :quality :common
  :set :basic
  :cost 4
  :minion {
    :attack 4
    :health 5
    :battlecry (target-all [:my] (restore-health 2))
  }
}
{
  :id 554
  :name "Divine Spirit"
  :quality :common
  :set :basic
  :class :priest
  :cost 2
  :spell (target [:minion] #(let [
    target (current-target %)
    health (:health target)
    max-health (:max-health target)
    doubled-health (* 2 health)
    ; we can't be healed above our new health, but we can be healed to our
    ; previous maximum health.
    new-max-health (max max-health doubled-health)
    ] (-> % (set-target {
      :max-health new-max-health
      :health doubled-health}))))
}
{
  :id 472
  :name "Dragonling Mechanic"
  :quality :common
  :set :basic
  :cost 4
  :minion {
    :attack 2
    :health 4
    :battlecry (summon-minion "Mechanical Dragonling")
  }
}
{
  :id 36
  :name "Dread Infernal"
  :quality :common
  :set :basic
  :class :warlock
  :cost 6
  :minion {
    :attack 6
    :health 6
    :type :demon
    :battlecry (target-all [{:not :self}]
      (damage-target 1))
  }
}
{
  :id 599
  :name "Dual Warglaives"
  :quality :common
  :set :missions
  :cost 6
  :weapon {
    :attack 4
    :durability 2
  }
}
{
  :id 356
  :name "Elven Archer"
  :quality :common
  :set :basic
  :cost 1
  :minion {
    :attack 1
    :health 1
    :battlecry (target [] (damage-target 1))
  }
}
{
  :id 240
  :name "Emboldener 3000"
  :quality :common
  :set :reward
  :cost 1
  :minion {
    :attack 0
    :health 4
    :end-of-my-turn (target-random [:minion] (buff-target 1 1))
  }
}
{
  :id 378
  :name "Fan of Knives"
  :quality :common
  :set :basic
  :class :rogue
  :cost 3
  :spell #(-> %
    (target-all [:opponent :minion] (damage-target 1))
    (draw-cards 1))
}
{
  :id 636
  :name "Fire Elemental"
  :quality :common
  :set :basic
  :cost 6
  :minion {
    :attack 6
    :health 5
    :battlecry (target [] (damage-target 3))
  }
}
{
  :id 622
  :name "Flame Burst"
  :quality :common
  :set :missions
  :cost 3
  :spell (apply comp (repeat 5 (target-random [:opponent] (damage-target 1))))
}
{
  :id 455
  :name "Flame of Azzinoth"
  :quality :common
  :set :missions
  :cost 1
  :minion {
    :attack 2
    :health 1
  }
}
{
  :id 44
  :name "Flamestrike"
  :quality :common
  :set :basic
  :class :mage
  :cost 7
  :spell (target-all [:opponent :minion] (damage-target 4))
}
{
  :id 390
  :name "Flametongue Totem"
  :quality :common
  :set :basic
  :class :shaman
  :cost 2
  :minion {
    :attack 0
    :health 3
    :type :totem
    :aura {:filter :adjacent :attack (partial + 2)}
  }
}
{
  :id 115
  :name "Frog"
  :quality :common
  :set :basic
  :cost 0
  :minion {
    :attack 0
    :health 1
    :type :beast
    :properties [:taunt]
  }
}
{
  :id 49
  :name "Frost Nova"
  :quality :common
  :set :basic
  :class :mage
  :cost 3
  :spell (target-all [:opponent :minion] (give-target :freeze))
}
{
  :id 177
  :name "Frostbolt"
  :quality :common
  :set :basic
  :class :mage
  :cost 2
  :spell (target [] #(-> % (damage-target 3) (give-target :freeze)))
}
{
  :id 663
  :name "Frostwolf Grunt"
  :quality :common
  :set :basic
  :cost 2
  :minion {
    :attack 2
    :health 2
    :properties [:taunt]
  }
}
{
  :id 604
  :name "Frostwolf Warlord"
  :quality :common
  :set :basic
  :cost 5
  :minion {
    :attack 4
    :health 4
    :battlecry #(let [n (count (filter-all % [:my :minion {:not :self}]))]
      (-> % (buff-self n n)))
  }
}
{
  :id 381
  :name "Gnoll"
  :quality :common
  :set :missions
  :cost 1
  :minion {
    :attack 1
    :health 1
  }
}
{
  :id 246
  :name "Gnomish Inventor"
  :quality :common
  :set :basic
  :cost 4
  :minion {
    :attack 2
    :health 4
    :battlecry (draw-cards 1)
  }
}
{
  :id 564
  :name "Goldshire Footman"
  :quality :common
  :set :basic
  :cost 1
  :minion {
    :attack 1
    :health 2
    :properties [:taunt]
  }
}
{
  :id 510
  :name "Grimscale Oracle"
  :quality :common
  :set :basic
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :murloc
    :aura {:filter :murloc :attack inc}
  }
}
{
  :id 283
  :name "Guardian of Kings"
  :quality :common
  :set :basic
  :class :paladin
  :cost 7
  :minion {
    :attack 5
    :health 6
    :battlecry (target [:my :hero] (restore-health 6))
  }
}
{
  :id 624
  :name "Gurubashi Berserker"
  :quality :common
  :set :basic
  :cost 5
  :minion {
    :attack 2
    :health 7
    :when-minion-damaged (buff-self 3 0)
  }
}
{
  :id 470
  :name "Hemet Nesingwary"
  :quality :common
  :set :missions
  :hero {
    :hero-power "Shotgun Blast"
    :max-health 20
  }
}
{
  :id 387
  :name "Hidden Gnome"
  :quality :common
  :set :missions
  :cost 2
  :minion { ; Was hiding in a barrel! (King Mukla)
    :attack 1
    :health 3
  }
}
{
  :id 490
  :name "Hogger"
  :quality :common
  :set :missions
  :hero {
    :max-health 10
  }
}
{
  :id 443
  :name "Hogger SMASH!"
  :quality :common
  :set :missions
  :cost 3
  :spell (target [] (damage-target 4))
}
{
  :id 671
  :name "Holy Nova"
  :quality :common
  :set :basic
  :class :priest
  :cost 5
  :spell #(-> % (target-all [:opponent] (damage-target 2))
    (target-all [:my] (restore-health 2)))
}
{
  :id 423
  :name "Homing Chicken"
  :quality :common
  :set :reward
  :cost 1
  :minion {
    :attack 0
    :health 1
    :start-of-my-turn #(-> % (destroy-self) (draw-cards 3))
  }
}
{
  :id 369
  :name "Huffer"
  :quality :common
  :set :basic
  :class :hunter
  :cost 3
  :minion {
    :attack 4
    :health 2
    :type :beast
    :properties :charge
  }
}
{
  :id 189
  :name "Humility"
  :quality :common
  :set :basic
  :class :paladin
  :cost 1
  :spell (target [:minion] (set-target {:attack 1}))
}
{
  :id 22
  :name "Hunter's Mark"
  :quality :common
  :set :basic
  :class :hunter
  :cost 0
  :spell (target [:minion] (set-target {:health 1 :max-health 1}))
}
{
  :id 238
  :name "Ironbark Protector"
  :quality :common
  :set :basic
  :class :druid
  :cost 8
  :minion {
    :attack 8
    :health 8
    :properties [:taunt]
  }
}
{
  :id 41
  :name "Ironforge Rifleman"
  :quality :common
  :set :basic
  :cost 3
  :minion {
    :attack 2
    :health 2
    :battlecry (target [] (damage-target 1))
  }
}
{
  :id 519
  :name "Ironfur Grizzly"
  :quality :common
  :set :basic
  :cost 3
  :minion {
    :attack 3
    :health 3
    :type :beast
    :properties [:taunt]
  }
}
{
  :id 139
  :name "Jaina Proudmoore"
  :quality :common
  :set :missions
  :class :mage
  :cost 0
  :hero {
    :max-health 27
  }
}
{
  :id 488
  :name "Kill Command"
  :quality :common
  :set :basic
  :class :hunter
  :cost 3
  :spell (target [] #(if (> (count (filter-all % [:my :beast])) 0)
    (-> % (damage-target 5))
    (-> % (damage-target 3))))
}
{
  :id 444
  :name "King Mukla"
  :quality :common
  :set :missions
  :cost 0
  :hero {
    :max-health 26
  }
}
{
  :id 479
  :name "Kobold Geomancer"
  :quality :common
  :set :basic
  :cost 2
  :minion {
    :attack 2
    :health 2
    :spell-damage 1
  }
}
{
  :id 130
  :name "Kor'kron Elite"
  :quality :common
  :set :basic
  :class :warrior
  :cost 4
  :minion {
    :attack 4
    :health 3
    :properties [:charge]
  }
}
{
  :id 441
  :name "Legacy of the Emperor"
  :quality :common
  :set :missions
  :cost 3
  :spell (target-all [:my :minion] (buff-target 2 2))
}
{
  :id 32
  :name "Leokk"
  :quality :common
  :set :basic
  :class :hunter
  :cost 3
  :minion {
    :attack 2
    :health 4
    :aura {:filter [:my :minion] :attack inc}
  }
}
{
  :id 414
  :name "Lord of the Arena"
  :quality :common
  :set :basic
  :cost 6
  :minion {
    :attack 6
    :health 5
    :properties [:taunt]
  }
}
{
  :id 137
  :name "Massive Gnoll"
  :quality :common
  :set :missions
  :cost 4
  :minion {
    :attack 5
    :health 2
  }
}
{
  :id 680
  :name "Mechanical Dragonling"
  :quality :common
  :set :basic
  :cost 1
  :minion {
    :attack 2
    :health 1
  }
}
{
  :id 592
  :name "Metamorphosis"
  :quality :common
  :set :missions
  :cost 6
  :spell #(println "Do something crazy...") ; Illidan Tutorial
}
{
  :id 330
  :name "Millhouse Manastorm"
  :quality :common
  :set :missions
  :class :mage
  :cost 0
  :hero {
    :max-health 20
  }
}
{
  :id 401
  :name "Mind Control"
  :quality :common
  :set :basic
  :class :priest
  :cost 10
  :spell (target [:opponent :minion] (mind-control-target))
}
{
  :id 438
  :name "Mind Vision"
  :quality :common
  :set :basic
  :class :priest
  :cost 1
  :spell (target-random [:opponent :drawn]
    #(-> % (give-card (:id (current-target %)))))
}
{
  :id 30
  :name "Mirror Image"
  :quality :common
  :set :basic
  :class :mage
  :cost 1
  :spell (summon-minion [650 650])
}
{
  :id 650
  :name "Mirror Image"
  :quality :common
  :set :basic
  :class :mage
  :cost 0
  :minion {
    :attack 0
    :health 2
    :properties [:taunt]
  }
}
{
  :id 593
  :name "Misha"
  :quality :common
  :set :basic
  :class :hunter
  :cost 3
  :minion {
    :attack 4
    :health 4
    :type :beast
    :properties [:taunt]
  }
}
{
  :id 619
  :name "Moonfire"
  :quality :common
  :set :basic
  :class :druid
  :cost 0
  :spell (target [] (damage-target 1))
}
{
  :id 43
  :name "Mortal Coil"
  :quality :common
  :set :basic
  :class :warlock
  :cost 1
  :spell (target [:minion] #(-> %
    (damage-target 1)
    (draw-cards (if (is-dead (current-target %)) 1 0))))
}
{
  :id 404
  :name "Mukla's Big Brother"
  :quality :common
  :set :missions
  :cost 6
  :minion {
    :attack 10
    :health 10
  }
}
{
  :id 486
  :name "Murloc Scout"
  :quality :common
  :set :basic
  :cost 0
  :minion {
    :attack 1
    :health 1
    :type :murloc
  }
}
{
  :id 357
  :name "Murloc Tidehunter"
  :quality :common
  :set :basic
  :cost 2
  :minion {
    :attack 2
    :health 1
    :type :murloc
    :battlecry (summon-minion "Murloc Scout")
  }
}
{
  :id 16
  :name "Naga Myrmidon"
  :quality :common
  :set :missions
  :cost 1
  :minion {
    :attack 1
    :health 1
  }
}
{
  :id 659
  :name "Ogre Magi"
  :quality :common
  :set :basic
  :cost 4
  :minion {
    :attack 4
    :health 4
    :spell-damage 1
  }
}
{
  :id 399
  :name "Pandaren Scout"
  :quality :common
  :set :missions
  :cost 1
  :minion {
    :attack 1
    :health 1
  }
}
{
  :id 405
  :name "Poultryizer"
  :quality :common
  :set :reward
  :cost 1
  :minion {
    :attack 0
    :health 3
    :start-of-my-turn (target-random [:minion {:not :self}]
      (polymorph-target "Chicken"))
  }
}
{
  :id 47
  :name "Razorfen Hunter"
  :quality :common
  :set :basic
  :cost 3
  :minion {
    :attack 2
    :health 3
    :battlecry (summon-minion "Boar")
  }
}
{
  :id 439
  :name "Repair Bot"
  :quality :common
  :set :reward
  :cost 1
  :minion {
    :attack 0
    :health 3
    :end-of-my-turn (target-random [:damaged]
      (restore-health 6))
  }
}
{
  :id 678
  :name "Riverpaw Gnoll"
  :quality :common
  :set :missions
  :cost 1
  :minion {
    :attack 2
    :health 1
  }
}
{
  :id 348
  :name "Sacrificial Pact"
  :quality :common
  :set :basic
  :class :warlock
  :cost 0
  :spell (target [:demon] #(-> %
    (destroy-target)
    (target [:my :hero] (restore-health 5))))
}
{
  :id 329
  :name "Savage Roar"
  :quality :common
  :set :basic
  :class :druid
  :cost 3
  :spell (target-all [:my] (give-target {:attack-this-turn 2}))
}
{
  :id 288
  :name "Shado-Pan Monk"
  :quality :common
  :set :missions
  :cost 2
  :minion {
    :attack 2
    :health 2
  }
}
{
  :id 547
  :name "Shadow Word: Death"
  :quality :common
  :set :basic
  :class :priest
  :cost 3
  :spell (target [:minion #(>= (:attack %) 5)] (destroy-target))
}
{
  :id 434
  :name "Shattered Sun Cleric"
  :quality :common
  :set :basic
  :cost 3
  :minion {
    :attack 3
    :health 2
    :battlecry (target [:my :minion] (buff-target 1 1))
  }
}
{
  :id 218
  :name "Sheep"
  :quality :common
  :set :basic
  :cost 0
  :minion {
    :attack 1
    :health 1
    :type :beast
  }
}
{
  :id 493
  :name "Shield Block"
  :quality :common
  :set :basic
  :class :warrior
  :cost 3
  :spell (target [:my :hero] #(-> %
    (give-target {:armor 5})
    (draw-cards 1)))
}
{
  :id 164
  :name "Shiv"
  :quality :common
  :set :basic
  :class :rogue
  :cost 2
  :spell (target [] #(-> %
    (damage-target 1)
    (draw-cards 1)))
}
{
  :id 580
  :name "Shotgun Blast"
  :quality :common
  :set :missions
  :class :hunter
  :cost 2
  :hero-power (target [] (damage-target 1))
}
{
  :id 611
  :name "Silverback Patriarch"
  :quality :common
  :set :basic
  :cost 3
  :minion {
    :attack 1
    :health 4
    :properties [:taunt]
  }
}
; {
;   :id 313
;   :name "Skeleton"
;   :quality :common
;   :set :basic
;   :cost 1
;   :minion {
;     :attack 1
;     :health 1
;   }
; }
{
  :id 529
  :name "Soulfire"
  :quality :common
  :set :basic
  :class :warlock
  :cost 0
  :spell (target [] #(-> %
    (damage-target 4)
    (target-random [:my :drawn] (destroy-target))))
}
{
  :id 90
  :name "Sprint"
  :quality :common
  :set :basic
  :class :rogue
  :cost 7
  :spell (draw-cards 4)
}
{
  :id 667
  :name "Starfire"
  :quality :common
  :set :basic
  :class :druid
  :cost 6
  :spell (target [] #(-> % (damage-target 5) (draw-cards 1)))
}
{
  :id 101
  :name "Starving Buzzard"
  :quality :common
  :set :basic
  :class :hunter
  :cost 2
  :minion {
    :attack 2
    :health 1
    :type :beast
    :when-my-minion-summoned #(if (:beast (current-target %))
      (draw-cards 1))
  }
}
{
  :id 105
  :name "Stomp"
  :quality :common
  :set :missions
  :cost 2
  :spell (target-all [:opponent] (damage-target 2))
}
{
  :id 325
  :name "Stormpike Commando"
  :quality :common
  :set :basic
  :cost 5
  :minion {
    :attack 4
    :health 2
    :battlecry (target [] (damage-target 2))
  }
}
{
  :id 310
  :name "Stormwind Champion"
  :quality :common
  :set :basic
  :cost 7
  :minion {
    :attack 6
    :health 6
    :aura {:filter [:my :minion] :attack inc :health inc}
  }
}
{
  :id 603
  :name "Stormwind Knight"
  :quality :common
  :set :basic
  :cost 4
  :minion {
    :attack 2
    :health 5
    :properties [:charge]
  }
}
{
  :id 620
  :name "Swipe"
  :quality :common
  :set :basic
  :class :druid
  :cost 4
  :spell (target [:opponent] #(-> %
    (damage-target 4)
    (target-all [:adjacent-to-target] (damage-target 1))))
}
{
  :id 367
  :name "Totemic Might"
  :quality :common
  :set :basic
  :class :shaman
  :cost 0
  :spell (target-all [:my :totem] (buff-target 0 2))
}
{
  :id 299
  :name "Transcendence"
  :quality :common
  :set :missions
  :cost 1
  :spell #(println "Kill minions or something") ; Yeah, no.
}
{
  :id 293
  :name "Truesilver Champion"
  :quality :common
  :set :basic
  :class :paladin
  :cost 4
  :weapon {
    :attack 4
    :durability 2
    :when-my-hero-attacks (target [:my :hero] (restore-health 2))
  }
}
{
  :id 162
  :name "Tundra Rhino"
  :quality :common
  :set :basic
  :class :hunter
  :cost 5
  :minion {
    :attack 2
    :health 5
    :type :beast
    :aura {:filter [:my :beast] :charge true}
  }
}
{
  :id 658
  :name "Vanish"
  :quality :common
  :set :basic
  :class :rogue
  :cost 6
  :spell (target-all [:minion] (return-target))
}
{
  :id 323
  :name "War Golem"
  :quality :common
  :set :basic
  :cost 7
  :minion {
    :attack 7
    :health 7
  }
}
{
  :id 494
  :name "Warglaive of Azzinoth"
  :quality :common
  :set :missions
  :cost 2
  :weapon {
    :attack 2
    :durability 2
  }
}
{
  :id 274
  :name "Water Elemental"
  :quality :common
  :set :basic
  :class :mage
  :cost 4
  :minion {
    :attack 3
    :health 6
    :when-minion-attacks (give-target :freeze)
  }
}
{
  :id 161
  :name "Whirlwind"
  :quality :common
  :set :basic
  :class :warrior
  :cost 1
  :spell (target-all [:minion] (damage-target 1))
}
{
  :id 586
  :name "Will of Mukla"
  :quality :common
  :set :missions
  :cost 3
  :spell (target [] (restore-health 8))
}
{
  :id 151
  :name "Windspeaker"
  :quality :common
  :set :basic
  :class :shaman
  :cost 4
  :minion {
    :attack 3
    :health 3
    :battlecry (target [:my :minion] (give-target :windfury))
  }
}
{
  :id 216
  :name "Ancestral Healing"
  :quality :free
  :set :basic
  :class :shaman
  :cost 0
  :spell (target [:minion] #(->
    (restore-health (:max-health (current-target %)))
    (give-target :taunt)))
}
{
  :id 110
  :name "Anduin Wrynn"
  :quality :free
  :set :basic
  :class :priest
  :cost 0
  :hero {
    :max-health 30
    :hero-power "Lesser Heal"
  }
}
{
  :id 56
  :name "Arcane Explosion"
  :quality :free
  :set :basic
  :class :mage
  :cost 2
  :spell (target-all [:opponent :minion] (damage-target 1))
}
{
  :id 489
  :name "Arcane Intellect"
  :quality :free
  :set :basic
  :class :mage
  :cost 3
  :spell (draw-cards 2)
}
{
  :id 589
  :name "Arcane Missiles"
  :quality :free
  :set :basic
  :class :mage
  :cost 1
  :spell (apply comp (repeat 3 (target-random [:opponent] (damage-target 1))))
}
{
  :id 167
  :name "Arcane Shot"
  :quality :free
  :set :basic
  :class :hunter
  :cost 1
  :spell (target [] (damage-target 2))
}
{
  :id 253
  :name "Armor Up!"
  :quality :free
  :set :basic
  :class :warrior
  :cost 2
  :hero-power (target [:my :hero] (give-target {:armor 2}))
}
{
  :id 568
  :name "Assassinate"
  :quality :free
  :set :basic
  :class :rogue
  :cost 5
  :spell (target [:opponent :minion] (destroy-target))
}
{
  :id 666
  :name "Avatar of the Coin"
  :collectible false
  :quality :free
  :set :basic
  :cost 0
  :minion {
    :attack 1
    :health 1
  }
}
{
  :id 471
  :name "Backstab"
  :quality :free
  :set :basic
  :class :rogue
  :cost 0
  :spell (target [{:not :damaged} :minion] (damage-target 2))
}
{
  :id 394
  :name "Blessing of Might"
  :quality :free
  :set :basic
  :class :paladin
  :cost 1
  :spell (target [:minion] (buff-target 3 0))
}
{
  :id 576
  :name "Bloodfen Raptor"
  :quality :free
  :set :basic
  :cost 2
  :minion {
    :attack 3
    :health 2
    :type :beast
  }
}
{
  :id 60
  :name "Boulderfist Ogre"
  :quality :free
  :set :basic
  :cost 6
  :minion {
    :attack 6
    :health 7
  }
}
{
  :id 646
  :name "Charge"
  :quality :free
  :set :basic
  :class :warrior
  :cost 0
  :spell (target [:my :minion] (give-target :charge))
}
{
  :id 532
  :name "Claw"
  :quality :free
  :set :basic
  :class :druid
  :cost 1
  :spell (target [:my :hero] (give-target {:armor 2 :attack-this-turn 2}))
}
{
  :id 201
  :name "Dagger Mastery"
  :quality :free
  :set :basic
  :class :rogue
  :cost 2
  :hero-power (equip-weapon "Wicked Knife")
}
{
  :id 87
  :name "Deadly Poison"
  :quality :free
  :set :basic
  :class :rogue
  :cost 1
  :spell (target [:my :weapon] (buff-target 2 0))
}
{
  :id 332
  :name "Drain Life"
  :quality :free
  :set :basic
  :class :warlock
  :cost 3
  :spell (target [] #(-> %
    (damage-target 2)
    (target [:my :hero] (restore-health 2))))
}
{
  :id 227
  :name "Execute"
  :quality :free
  :set :basic
  :class :warrior
  :cost 1
  :spell (target [:damaged :opponent :minion] (destroy-target))
}
{
  :id 632
  :name "Fiery War Axe"
  :quality :free
  :set :basic
  :class :warrior
  :cost 2
  :weapon {
    :attack 3
    :durability 2
  }
}
{
  :id 522
  :name "Fireball"
  :quality :free
  :set :basic
  :class :mage
  :cost 4
  :spell (target [] (damage-target 6))
}
{
  :id 677
  :name "Fireblast"
  :quality :free
  :set :basic
  :class :mage
  :cost 2
  :hero-power (target [] (damage-target 1))
}
{
  :id 233
  :name "Frost Shock"
  :quality :free
  :set :basic
  :class :shaman
  :cost 1
  :spell (target [:opponent] #(-> %
    (damage-target 1)
    (give-target :freeze)))
}
{
  :id 635
  :name "Garrosh Hellscream"
  :quality :free
  :set :basic
  :class :warrior
  :cost 0
  :hero {
    :max-health 30
    :hero-power "Armor Up!"
  }
}
{
  :id 618
  :name "Gul'dan"
  :quality :free
  :set :basic
  :class :warlock
  :cost 0
  :hero {
    :max-health 30
    :hero-power "Life Tap"
  }
}
{
  :id 350
  :name "Hammer of Wrath"
  :quality :free
  :set :basic
  :class :paladin
  :cost 4
  :spell (target [] #(-> % (damage-target 3) (draw-cards 1)))
}
{
  :id 499
  :name "Hand of Protection"
  :quality :free
  :set :basic
  :class :paladin
  :cost 1
  :spell (target [:minion] (give-target :divine-shield))
}
{
  :id 275
  :name "Healing Totem"
  :quality :free
  :set :basic
  :class :shaman
  :cost 1
  :minion {
    :attack 0
    :health 2
    :type :totem
    :end-of-my-turn (target-all [:my :minion] (restore-health 1))
  }
}
{
  :id 258
  :name "Healing Touch"
  :quality :free
  :set :basic
  :class :druid
  :cost 3
  :spell (target [] (restore-health 8))
}
{
  :id 122
  :name "Hellfire"
  :quality :free
  :set :basic
  :class :warlock
  :cost 4
  :spell (target-all [] (damage-target 3))
}
{
  :id 1
  :name "Heroic Strike"
  :quality :free
  :set :basic
  :class :warrior
  :cost 2
  :spell (target [:my :hero] (give-target {:attack-this-turn 4}))
}
{
  :id 270
  :name "Hex"
  :quality :free
  :set :basic
  :class :shaman
  :cost 3
  :spell (target [:minion] (polymorph-target "Frog"))
}
{
  :id 108
  :name "Holy Light"
  :quality :free
  :set :basic
  :class :paladin
  :cost 2
  :spell (target [] (restore-health 6))
}
{
  :id 409
  :name "Holy Smite"
  :quality :free
  :set :basic
  :class :priest
  :cost 1
  :spell (target [] (damage-target 2))
}
{
  :id 225
  :name "Houndmaster"
  :quality :free
  :set :basic
  :class :hunter
  :cost 4
  :minion {
    :attack 4
    :health 3
    :battlecry (target [:my :beast]
      (give-target {:health 2 :attack 2 :taunt true}))
  }
}
{
  :id 548
  :name "Innervate"
  :quality :free
  :set :basic
  :class :druid
  :cost 0
  :spell (target [:my :hero] (give-target {:mana 2}))
}
{
  :id 320
  :name "Jaina Proudmoore"
  :quality :free
  :set :basic
  :class :mage
  :cost 0
  :hero {
    :max-health 30
    :hero-power "Fireblast"
  }
}
{
  :id 126
  :name "Lesser Heal"
  :quality :free
  :set :basic
  :class :priest
  :cost 2
  :hero-power (target [] (restore-health 2))
}
{
  :id 20
  :name "Life Tap"
  :quality :free
  :set :basic
  :class :warlock
  :cost 2
  :hero-power (target [:my :hero] #(-> (damage-target 2) (draw-cards 1)))
}
{
  :id 250
  :name "Light's Justice"
  :quality :free
  :set :basic
  :class :paladin
  :cost 1
  :weapon {
    :attack 1
    :durability 4
  }
}
{
  :id 362
  :name "Magma Rager"
  :quality :free
  :set :basic
  :cost 3
  :minion {
    :attack 5
    :health 1
  }
}
{
  :id 621
  :name "Malfurion Stormrage"
  :quality :free
  :set :basic
  :class :druid
  :cost 0
  :hero {
    :max-health 30
    :hero-power "Shapeshift"
  }
}
{
  :id 480
  :name "Mark of the Wild"
  :quality :free
  :set :basic
  :class :druid
  :cost 2
  :spell (target [:minion] (give-target {:attack 2 :health 2 :taunt true}))
}
{
  :id 415
  :name "Mind Blast"
  :quality :free
  :set :basic
  :class :priest
  :cost 2
  :spell (target [:opponent :hero] (damage-target 5))
}
{
  :id 407
  :name "Multi-Shot"
  :quality :free
  :set :basic
  :class :hunter
  :cost 4
  :spell (target-random [2 :opponent :minion] (damage-target 3))
}
{
  :id 55
  :name "Murloc Raider"
  :quality :free
  :set :basic
  :cost 1
  :minion {
    :attack 2
    :health 1
    :type :murloc
  }
}
{
  :id 184
  :name "Nightblade"
  :quality :free
  :set :basic
  :cost 5
  :minion {
    :attack 4
    :health 4
    :battlecry (target [:opponent :hero] (damage-target 3))
  }
}
{
  :id 600
  :name "Northshire Cleric"
  :quality :free
  :set :basic
  :class :priest
  :cost 1
  :minion {
    :attack 1
    :health 3
    :when-minion-healed (draw-cards 1)
  }
}
{
  :id 435
  :name "Novice Engineer"
  :quality :free
  :set :basic
  :cost 2
  :minion {
    :attack 1
    :health 2
    :battlecry (draw-cards 1)
  }
}
{
  :id 15
  :name "Oasis Snapjaw"
  :quality :free
  :set :basic
  :cost 4
  :minion {
    :attack 2
    :health 7
    :type :beast
  }
}
{
  :id 595
  :name "Polymorph"
  :quality :free
  :set :basic
  :class :mage
  :cost 4
  :spell (target [:minion] (polymorph-target "Sheep"))
}
{
  :id 431
  :name "Power Word: Shield"
  :quality :free
  :set :basic
  :class :priest
  :cost 1
  :spell (target [:minion] #(-> (buff-target 0 2) (draw-cards 1)))
}
{
  :id 502
  :name "Raid Leader"
  :quality :free
  :set :basic
  :cost 3
  :minion {
    :attack 2
    :health 2
    :aura {:filter [:my :minion] :attack inc}
  }
}
{
  :id 560
  :name "Reckless Rocketeer"
  :quality :free
  :set :basic
  :cost 6
  :minion {
    :attack 5
    :health 2
    :properties [:charge]
  }
}
{
  :id 248
  :name "Reinforce"
  :quality :free
  :set :basic
  :class :paladin
  :cost 2
  :hero-power (summon-minion "Silver Hand Recruit")
}
{
  :id 484
  :name "Rexxar"
  :quality :free
  :set :basic
  :class :hunter
  :cost 0
  :hero {
    :max-health 30
    :hero-power "Steady Shot"
  }
}
{
  :id 535
  :name "River Crocolisk"
  :quality :free
  :set :basic
  :cost 2
  :minion {
    :attack 2
    :health 3
    :type :beast
  }
}
{
  :id 491
  :name "Rockbiter Weapon"
  :quality :free
  :set :basic
  :class :shaman
  :cost 1
  :spell (target [:my] (give-target {:attack-this-turn 3}))
}
{
  :id 385
  :name "Sap"
  :quality :free
  :set :basic
  :class :rogue
  :cost 2
  :spell (target [:opponent :minion] (return-target))
}
{
  :id 98
  :name "Searing Totem"
  :quality :free
  :set :basic
  :class :shaman
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :totem
  }
}
{
  :id 326
  :name "Sen'jin Shieldmasta"
  :quality :free
  :set :basic
  :cost 4
  :minion {
    :attack 3
    :health 5
    :properties [:taunt]
  }
}
{
  :id 647
  :name "Shadow Bolt"
  :quality :free
  :set :basic
  :class :warlock
  :cost 3
  :spell (target [:minion] (damage-target 4))
}
{
  :id 315
  :name "Shadow Word: Pain"
  :quality :free
  :set :basic
  :class :priest
  :cost 2
  :spell (target [:minion #(<= (:attack %) 3)] (destroy-target))
}
{
  :id 185
  :name "Shapeshift"
  :quality :free
  :set :basic
  :class :druid
  :cost 2
  :hero-power (target [:my :hero] (give-target {:armor 1 :attack-this-turn 1}))
}
{
  :id 268
  :name "Silver Hand Recruit"
  :quality :free
  :set :basic
  :class :paladin
  :cost 1
  :minion {
    :attack 1
    :health 1
  }
}
{
  :id 205
  :name "Sinister Strike"
  :quality :free
  :set :basic
  :class :rogue
  :cost 1
  :spell (target [:opponent :hero] (damage-target 3))
}
{
  :id 481
  :name "Steady Shot"
  :quality :free
  :set :basic
  :class :hunter
  :cost 2
  :hero-power (target [:opponent :hero] (damage-target 2))
}
{
  :id 298
  :name "Stoneclaw Totem"
  :quality :free
  :set :basic
  :class :shaman
  :cost 1
  :minion {
    :attack 0
    :health 2
    :type :totem
    :properties [:taunt]
  }
}
{
  :id 76
  :name "Stonetusk Boar"
  :quality :free
  :set :basic
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :beast
    :properties [:charge]
  }
}
{
  :id 208
  :name "Succubus"
  :quality :free
  :set :basic
  :class :warlock
  :cost 2
  :minion {
    :attack 4
    :health 3
    :type :demon
    :battlecry (target-random [:my :drawn] (destroy-target))
  }
}
{
  :id 319
  :name "Thrall"
  :quality :free
  :set :basic
  :class :shaman
  :cost 0
  :hero {
    :max-health 30
    :hero-power "Totemic Call"
  }
}
{
  :id 86
  :name "Timber Wolf"
  :quality :free
  :set :basic
  :class :hunter
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :beast
    :aura {:filter [:my :beast] :attack inc}
  }
}
{
  :id 316
  :name "Totemic Call"
  :quality :free
  :set :basic
  :class :shaman
  :cost 2
  :hero-power #(let [
    summonable-totems #{275 98 365 298}
    current-totems (set (map :id (filter-all % [:my :totem])))
    totems-not-summoned (set/difference summonable-totems current-totems)
    ] (if (> (count (totems-not-summoned)) 0)
      (-> % (summon-minion (rand-nth (seq totems-not-summoned))))))
  ; todo: remember nil state signals an invalid path
}
{
  :id 163
  :name "Tracking"
  :quality :free
  :set :basic
  :class :hunter
  :cost 1
  ; :first 3 here pulls first of the filter's seq 3 times
  :spell (target [:my :undrawn {:first 3}]
    #(-> %
      (draw-target) ; draw the choice
      (target-all [:my :undrawn {:first 2}]
        (destroy-target)))) ; destroy the rest
}
{
  :id 257
  :name "Uther Lightbringer"
  :quality :free
  :set :basic
  :class :paladin
  :cost 0
  :hero {
    :max-health 30
    :hero-power "Reinforce"
  }
}
{
  :id 2
  :name "Valeera Sanguinar"
  :quality :free
  :set :basic
  :class :rogue
  :cost 0
  :hero {
    :max-health 30
    :hero-power "Dagger Mastery"
  }
}
{
  :id 340
  :name "Voidwalker"
  :quality :free
  :set :basic
  :class :warlock
  :cost 1
  :minion {
    :attack 1
    :health 3
    :type :demon
    :properties [:taunt]
  }
}
{
  :id 410
  :name "Voodoo Doctor"
  :quality :free
  :set :basic
  :cost 1
  :minion {
    :attack 2
    :health 1
    :battlecry (target [] (restore-health 2))
  }
}
{
  :id 193
  :name "Warsong Commander"
  :quality :free
  :set :basic
  :class :warrior
  :cost 3
  :minion {
    :attack 2
    :health 3
    :aura {:filter [:my :minion] :charge true}
  }
}
{
  :id 183
  :name "Wicked Knife"
  :quality :free
  :set :basic
  :class :rogue
  :cost 1
  :weapon {
    :attack 1
    :durability 2
  }
}
{
  :id 282
  :name "Wild Growth"
  :quality :free
  :set :basic
  :class :druid
  :cost 2
  :spell (target [:my :hero] (give-target {:mana-crystal 1}))
}
{
  :id 146
  :name "Windfury"
  :quality :free
  :set :basic
  :class :shaman
  :cost 2
  :spell (target [:minion] (give-target :windfury))
}
{
  :id 174
  :name "Wolfrider"
  :quality :free
  :set :basic
  :cost 3
  :minion {
    :attack 3
    :health 1
    :properties [:charge]
  }
}
{
  :id 365
  :name "Wrath of Air Totem"
  :quality :free
  :set :basic
  :class :shaman
  :cost 1
  :minion {
    :attack 0
    :health 2
    :type :totem
    :spell-damage 1
  }
}
])
