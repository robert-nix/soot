; as of 12/30/2013 via Hearthpwn's card db
(ns soot.db)

(def cards [
; Misc
{
  :id 552
  :name "Chicken"
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
  :class :druid
  :cost 0
  :spell (draw-cards 1)
}
{
  :id 714
  :name "I Am Murloc"
  :spell #((summon-minion (repeat (rand-nth [3 4 5]) 715)) %) ; 715 = 1/1 Murloc
}
{
  :id 715
  :name "Murloc"
  :cost 1
  :minion {
    :attack 1
    :health 1
    :type :beast
  }
}
{
  :id 717
  :name "Power of the Horde"
  :spell #((summon-minion (rand-nth ["Frostwolf Grunt" "Sen'jin Shieldmasta"
    "Cairne Bloodhoof" "Tauren Warrior" "Thrallmar Farseer"
    "Silvermoon Guardian"])) %)
}
{
  :id 716
  :name "Rogues Do It..."
  :spell (-> % (deal-damage 4) (draw-cards 1))
}
; The Coin
{
  :id 141
  :name "The Coin"
  :cost 0
  :spell (add-temporary-mana 1)
}
; Legendaries
{
  :id 335
  :name "Al'Akir the Windlord"
  :quality :legendary
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
  :cost 9
  :minion {
    :attack 8
    :health 8
    :type :dragon
    :battlecry (set-hero-health 15)
  }
}
{
  :id 220
  :name "Archmage Antonidas"
  :quality :legendary
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
  :class :druid
  :cost 9
  :minion {
    :attack 5
    :health 8
    :battlecry (choose (buff-minions 2 2) (summon-minion [181 181])) ; 181 = Taunt Treant
  }
}
{
  :id 474
  :name "Deathwing"
  :quality :legendary
  :cost 10
  :minion {
    :attack 12
    :health 12
    :type :dragon
    :battlecry (comp (destroy-minions) (discard :all))
  }
}
{
  :id 3
  :name "Edwin VanCleef"
  :quality :legendary
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
  :cost 6
  :minion {
    :attack 7
    :health 5
    :type :demon
    :when-my-card-played (summon-minion "Flame of Azzinoth")
  }
}
{
  :id 194
  :name "King Krush"
  :quality :legendary
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
  :class :warlock
  :hero {
    :health 15
    :type :demon
    :power "INFERNO!"
  }
}
{
  :id 456
  :name "Lorewalker Cho"
  :quality :legendary
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
  :cost 9
  :minion {
    :attack 8
    :health 8
    :type :dragon
    :battlecry (summon-minion (repeat 6 527)) ; 527 = 1/1 Whelp
  }
}
{
  :id 228
  :name "Prophet Velen"
  :quality :legendary
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
  :cost 8
  :minion {
    :attack 8
    :health 8
    :properties [:cant-attack]
    :end-of-my-turn (damage-random-enemy 8)
  }
}
{
  :id 33
  :name "Sylvanas Windrunner"
  :quality :legendary
  :cost 5
  :minion {
    :attack 5
    :health 5
    :deathrattle (mind-control-random)
  }
}
{
  :id 179
  :name "The Beast"
  :quality :legendary
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
  :cost 3
  :minion {
    :attack 2
    :health 2
    :battlecry (target :minion
      #((polymorph-target (rand-nth ["Devilsaur" "Squirrel"])) %))
  }
}
{
  :id 391
  :name "Tirion Fordring"
  :quality :legendary
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
  :cost 9
  :minion {
    :attack 4
    :health 12
    :end-of-my-turn #((give-card (rand-nth ["Dream"
      "Emerald Drake" "Laughing Sister" "Nightmare" "Ysera Awakens"])) %)
  }
}
{
  :id 180
  :name "Adrenaline Rush"
  :quality :epic
  :class :rogue
  :cost 1
  :spell (combo (draw-cards 1) (draw-cards 2))
}
{
  :id 34
  :name "Ancient of Lore"
  :quality :epic
  :class :druid
  :cost 7
  :minion {
    :attack 5
    :health 5
    :battlecry (choose (draw-cards 2) (heal-hero 5))
  }
}
{
  :id 242
  :name "Ancient of War"
  :quality :epic
  :class :druid
  :cost 7
  :minion {
    :attack 5
    :health 5
    :battlecry (choose
      #(-> % (give-self-taunt) (buff-self 0 5)) (buff-self 5 0))
  }
}
{
  :id 142
  :name "Avenging Wrath"
  :quality :epic
  :class :paladin
  :cost 6
  :spell (apply comp (repeat 8 (target-random :opponent (deal-damage 1))))
}
{
  :id 670
  :name "Bane of Doom"
  :quality :epic
  :class :warlock
  :cost 5
  :spell #(-> %
    (deal-damage 2)
    (summon-minion-if #(is-dead % (last-targeted-character %))
      (rand-nth ["Blood Imp" "Voidwalker" "Flame Imp"
      "Dread Infernal" "Succubus" "Felguard"])))
}
{
  :id 304
  :name "Bestial Wrath"
  :quality :epic
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
  :cost 3
  :minion {
    :attack 3
    :health 3
    :battlecry #(-> %
      (apply buff-self (repeat 2 (* 3 (count-minions % :divine-shield))))
      (remove-all :divine-shield))
  }
}
{
  :id 297
  :name "Brawl"
  :quality :epic
  :class :warrior
  :cost 5
  :spell #((apply comp (repeat (+ (count-minions %) -1)
    (target-random :minion (destroy-target)))) %)
}
{
  :id 147
  :name "Cabal Shadow Priest"
  :quality :epic
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
  :class :shaman
  :cost 3
  :spell (comp
    #((modify-card-cost (last-card-drawn %) (partial + -3)) %)
    (draw-cards 1))
}
{
  :id 237
  :name "Force of Nature"
  :quality :epic
  :class :druid
  :cost 6
  :spell (summon-minion (repeat 3 337))
}
{
  :id 278
  :name "Gladiator's Longbow"
  :quality :epic
  :class :hunter
  :cost 7
  :weapon {
    :attack 5
    :durability 2
    :immune ; todo, remember to interpret this tag for weapons
  }
}
{
  :id 96
  :name "Gorehowl"
  :quality :epic
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
  :class :mage
  :cost 3
  :secret {
    :when-hero-takes-damage #(if (< (hero-health %) 1)
      (-> %
        (heal-hero (damage-taken %))
        (give-hero :immune)))
  }
}
{
  :id 562
  :name "Kidnapper"
  :quality :epic
  :class :rogue
  :cost 6
  :minion {
    :attack 5
    :health 5
    :combo (target :minion (return-to-hand))
  }
}
{
  :id 506
  :name "Lay on Hands"
  :quality :epic
  :class :paladin
  :cost 8
  :spell #(-> (heal-hero 8) (draw-cards 3))
}
{
  :id 301
  :name "Mindgames"
  :quality :epic
  :class :priest
  :cost 4
  :spell #(if (> (count (filter-all % [:opponent :undrawn :minion])) 0)
    ((summon-minion (rand-nth (filter-all % [:opponent :undrawn :minion]))) %)
    ((summon-minion 582) %)) ; 582 = Shadow of Nothing
}
{
  :id 94
  :name "Molten Giant"
  :quality :epic
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
  :cost 3
  :minion {
    :attack 3
    :health 3
    :type :murloc
    :aura {:filter :murloc :attack #(+ 2 %) :health #(+ 1 %)}
  }
}
{
  :id 14
  :name "Patient Assassin"
  :quality :epic
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
  :id 364
  :name "Preparation"
  :quality :epic
  :class :rogue
  :cost 0
  :spell (once-this-turn (spells-cost #(- % 3)))
}
{
  :id 496
  :name "Pyroblast"
  :quality :epic
  :class :mage
  :cost 8
  :spell (target :character (damage-target 10))
}
{
  :id 614
  :name "Sea Giant"
  :quality :epic
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
  :class :warrior
  :cost 1
  :spell (target :minion #((damage-target (hero-armor %)) %))
}
{
  :id 210
  :name "Snake Trap"
  :quality :epic
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
  :class :mage
  :cost 3
  :secret {
    :when-enemy-spell-cast #(if
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
  :class :warlock
  :spell (destroy-minions)
}
{
  :id 597
  :name "Abomination"
  :quality :rare
  :cost 5
  :minion {
    :attack 4
    :health 4
    :properties [:taunt]
    :deathrattle (damage-all 2)
  }
}
{
  :id 425
  :name "Alarm-o-Bot"
  :quality :rare
  :cost 3
  :minion {
    :attack 0
    :health 3
    :start-of-my-turn #(if (> (count (filter-all % [:my :drawn :minion])) 0)
      ((target-random [:drawn :minion]
        (swap-with-target)) %))
  }
}
; Epics
; Rares
; Commons
])