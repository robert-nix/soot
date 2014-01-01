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
  :spell (target [:character] (-> % (damage-target 4) (draw-cards 1)))
}
; The Coin
{
  :id 141
  :name "The Coin"
  :cost 0
  :spell (target [:my :hero] (give-target {:mana 1}))
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
    :battlecry (choose "Demigod's Favor" "Shan'do's Lesson")
      ; (target-all [:my :minion] (buff-target 2 2))
      ; (summon-minion [181 181])) ; 181 = Taunt Treant
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
    :battlecry (comp (destroy-minions) (target-all [:drawn] :all))
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
    :end-of-my-turn (target-random :opponent (damage-target 8))
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
    :deathrattle (target-random [:opponent :minion] (mind-control-target))
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
; Epics
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
    :battlecry (choose "Ancient Teachings" "Ancient Secrets")
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
    :battlecry (choose "Rooted" "Uproot")
      ; (target :self #(-> % (give-target :taunt) (buff-target 0 5)))
      ; (buff-self 5 0))
  }
}
{
  :id 142
  :name "Avenging Wrath"
  :quality :epic
  :class :paladin
  :cost 6
  :spell (apply comp (repeat 8 (target-random :opponent (damage-target 1))))
}
{
  :id 670
  :name "Bane of Doom"
  :quality :epic
  :class :warlock
  :cost 5
  :spell #(-> %
    (target [:character]
      (damage-target 2)
      #(if (is-dead (current-target %))
        ((summon-minion (rand-nth ["Blood Imp" "Voidwalker" "Flame Imp"
        "Dread Infernal" "Succubus" "Felguard"])) %)))
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
      (target-all :minion (set-target {:divine-shield false})))
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
  :spell (target-random [:my :undrawn] #(-> %
    (give-target {:cost -3})
    (draw-target)))
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
  :spell (target-random [1 :opponent :undrawn :minion]
    #(-> % (summon-minion (current-target %)))
    (summon-minion "Shadow of Nothing"))
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
    :aura {:filter :murloc :attack #(+ 2 %) :health inc}
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
  ; how do I plan for this to work?  these get their own modifiers for each
  ; time-dependent function (once-this-turn next-turn aura ...) on the state
  ; object and are managed/handled appropriately?  seems needlessly complex...
  :spell (once-this-turn (spells-cost #(- % 3)))
}
{
  :id 496
  :name "Pyroblast"
  :quality :epic
  :class :mage
  :cost 8
  :spell (target [:character] (damage-target 10))
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
; Rares
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
{ ; todo: be certain swap-with-target works essentially like brewmaster + summon
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
{
  :id 23
  :name "Aldor Peacekeeper"
  :quality :rare
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
  :class :shaman
  :cost 2
  :spell (target [:minion]
    (give-target {:deathrattle #((summon-minion (self %)) %)}))
}
{
  :id 176
  :name "Ancient Mage"
  :quality :rare
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
  :class :druid
  :cost 4
  :spell (target [:my :hero] (give-target {:armor 4 :attack-this-turn 4}))
}
{
  :id 244
  :name "Blade Flurry"
  :quality :rare
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
  :class :paladin
  :cost 5
  :spell (target [:minion]
    (give-target {:attack (partial * 2)}))
}
{
  :id 276
  :name "Blizzard"
  :quality :rare
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
  :cost 1
  :minion {
    :attack 1
    :health 2
    :type :pirate
    :battlecry (target [:opponent :hero] (give-target {:weapon-durability -1}))
  }
}
{
  :id 88
  :name "Coldlight Oracle"
  :quality :rare
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
  :cost 2
  :minion {
    :attack 2
    :health 2
    :battlecry (target [:minion] #(-> %
      (set-target {:health (:attack %)})
      (set-target {:attack (:health %)})))
  }
}
{
  :id 542
  :name "Defender of Argus"
  :quality :rare
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
  :class :warlock
  :cost 5
  :minion {
    :attack 5
    :health 7
    :type :demon
    :properties [:charge]
    :battlecry (target-random [2 :my :drawn]
      (destroy-target))
  }
}
{
  :id 363
  :name "Eaglehorn Bow"
  :quality :rare
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
  :class :paladin
  :cost 2
  :spell (target-all :minion (set-target {:health 1}))
}
{
  :id 125
  :name "Ethereal Arcanist"
  :quality :rare
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
  :class :shaman
  :cost 3
  :overload 2
  :spell (summon-minion (repeat 2 "Spirit Wolf"))
}
{
  :id 630
  :name "Flare"
  :quality :rare
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
  :class :rogue
  :cost 3
  :spell (target [:opponent :hero] (damage-target 2))
  :combo (once-my-next-turn (give-card "Headcrack"))
}
{
  :id 457
  :name "Holy Fire"
  :quality :rare
  :class :priest
  :cost 6
  :spell #(-> %
    (target [:character] (damage-target 5))
    (target [:my :hero] (restore-health 5)))
}
{
  :id 355
  :name "Holy Wrath"
  :quality :rare
  :class :paladin
  :cost 5
  ; let this be known as the first time I've restored to let
  :spell (target [:character] #(let [s ((draw-cards 1) %)]
    (-> s (damage-target (:cost (last-card-drawn s))))))
}
{
  :id 689
  :name "Hyena"
  :quality :rare
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
  :class :druid
  :cost 4
  :minion {
    :attack 2
    :health 4
    :battlecry (choose "Moonfire" "Dispel")
      ; (target [:character] (damage-target 2))
      ; (target [:minion] (silence-target)))
  }
}
{
  :id 411
  :name "Kirin Tor Mage"
  :quality :rare
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
  :class :shaman
  :cost 3
  :overload 2
  :spell (target [:character] (damage-target 5))
}
{
  :id 676
  :name "Lightning Storm"
  :quality :rare
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
  :cost 2
  :minion {
    :attack 2
    :health 2
    :aura (minions-cost inc)
  }
}
{
  :id 249
  :name "Mass Dispel"
  :quality :rare
  :class :priest
  :cost 4
  :spell (target-all [:opponent :minion] (silence-target))
}
{
  :id 127
  :name "Master of Disguise"
  :quality :rare
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
  :class :hunter
  :cost 2
  :secret {
    :when-my-hero-attacked (target-random [:character {:not :attacker}]
      (redirect-attack-target))
  }
}
{
  :id 345
  :name "Mortal Strike"
  :quality :rare
  :class :warrior
  :cost 4
  :spell #(if (<= (hero-health %) 12)
    (target [:character] (damage-target 6))
    (target [:character] (damage-target 4)))
}
{
  :id 420
  :name "Murloc Tidecaller"
  :quality :rare
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
  :class :rogue
  :cost 3
  :weapon {
    :attack 2
    :durability 2
    :battlecry (combo
      (target [:character] (damage-target 1))
      (target [:character] (damage-target 2)))
  }
}
{
  :id 54
  :name "Pint-Sized Summoner"
  :quality :rare
  :cost 2
  :minion {
    :attack 2
    :health 2
    :aura (once-each-turn (minions-cost #(- % 2)))
  }
}
{
  :id 157
  :name "Questing Adventurer"
  :quality :rare
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
  :class :druid
  :cost 1
  :spell (target [:character]
    #((damage-target (hero-attack %)) %))
}
{
  :id 8
  :name "Savannah Highmane"
  :quality :rare
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
  :class :priest
  :cost 4
  :spell (target [:opponent :minion #(<= (:attack %) 3)]
    (mind-control-target))
}
{
  :id 673
  :name "Shadowflame"
  :quality :rare
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
  :class :rogue
  :cost 3
  :minion {
    :attack 3
    :health 3
    :combo (target [:character] (damage-target 2))
  }
}
{
  :id 573
  :name "Siphon Soul"
  :quality :rare
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
  :cost 2
  :minion {
    :attack 3
    :health 2
    :when-my-spell-cast (target-all [:minion] (damage-target 1))
  }
}
{
  :id 123
  :name "Young Priestess"
  :quality :rare
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
  :quality :common
  :class :druid
  :cost 0
  :spell (target [:character] (restore-health 5))
}
{
  :id 517
  :name "Ancient Teachings"
  :quality :common
  :class :druid
  :cost 0
  :spell (draw-cards 2)
}
{
  :id 504
  :name "Arathi Weaponsmith"
  :quality :common
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
  :quality :common
  :cost 1
  :spell (target [:minion] (buff-target 1 1))
}
{
  :id 403
  :name "Battle Axe"
  :quality :common
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
  :class :warrior
  :cost 2
  :spell (target-all [:my :damaged] (draw-cards 1))
}
{
  :id 662
  :name "Bear Form"
  :quality :common
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
  :class :rogue
  :cost 2
  :spell (target [:opponent :minion]
    #(let [damage (:attack (current-target %))]
      ((target-all [:adjacent-to-target] (damage-target damage)) %)))
}
{
  :id 100
  :name "Blessing of Wisdom"
  :quality :common
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
  :quality :common
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
  :class :druid
  :cost 0
  :spell (target [:minion] (give-target :charge)) ; todo: :self / poly
}
{
  :id 38
  :name "Circle of Healing"
  :quality :common
  :class :priest
  :cost 0
  :spell (target-all [:minion] (restore-health 4))
}
{
  :id 92
  :name "Cold Blood"
  :quality :common
  :class :rogue
  :cost 1
  :spell (target [:minion] (combo (buff-target 2 0) (buff-target 4 0)))
}
{
  :id 284
  :name "Conceal"
  :quality :common
  :class :rogue
  :cost 1
  :spell (target-all [:my :minion] (give-target
    {:stealth-this-turn true :stealth-next-turn true}))
}
{
  :id 26
  :name "Cone of Cold"
  :quality :common
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
  :class :hunter
  :cost 3
  :spell (target-random [:opponent :minion] (destroy-target))
}
{
  :id 318
  :name "Defender"
  :quality :common
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
  :quality :common
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
  :quality :common
  :class :druid
  :cost 0
  :spell (target-all [:my :minion {:not :self}] (buff-target 2 2))
}
{
  :id 452
  :name "Demonfire"
  :quality :common
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
  :quality :common
  :class :druid
  :spell (target [:minion] (silence-target))
}
{
  :id 261
  :name "Dread Corsair"
  :quality :common
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
  :quality :common
  :cost 0
  :spell (target [:minion] (return-target))
}
{
  :id 587
  :name "Druid of the Claw"
  :quality :common
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
  :class :shaman
  :cost 1
  :spell (target [:minion] #(-> % (silence-target) (damage-target 1)))
}
{
  :id 557
  :name "Earthen Ring Farseer"
  :quality :common
  :cost 3
  :minion {
    :attack 3
    :health 3
    :battlecry (target [:character] (restore-health 3))
  }
}
{
  :id 534
  :name "Emerald Drake"
  :quality :common
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
  :class :rogue
  :cost 2
  :spell (target [:character] (combo (damage-target 2) (damage-target 4)))
}
{
  :id 344
  :name "Explosive Trap"
  :quality :common
  :class :hunter
  :cost 2
  :secret {
    :when-my-hero-attacked (target-all [:opponent :character] (damage-target 2))
  }
}
{
  :id 206
  :name "Eye for an Eye"
  :quality :common
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
  :quality :common
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
  :class :shaman
  :cost 1
  :overload 2
  :spell (target-random [:opponent :minion] (damage-target 2))
}
{
  :id 99
  :name "Freezing Trap"
  :quality :common
  :class :hunter
  :cost 2
  :secret {
    ; todo: make sure this supplies target as the attacker
    :when-opponent-minion-attacks #(-> %
      (return-target)
      (give-target {:cost 2}))
  }
}
{
  :id 598
  :name "Frost Elemental"
  :quality :common
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
  :quality :common
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
  :quality :common
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
  :class :mage
  :cost 1
  :spell (target [:character] #(if (:freeze (current-target %))
    (damage-target 4)
    (give-target :freeze)))
}
{
  :id 121
  :name "Infernal"
  :quality :common
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
  :quality :common
  :class :warlock
  :cost 2
  :hero-power (summon-minion "Infernal")
}
{
  :id 207
  :name "Inner Fire"
  :quality :common
  :class :priest
  :cost 1
  :spell (target [:minion] #(let [health (:health (current-target))]
    (-> % (set-target {:attack health}))))
}
{
  :id 500
  :name "Ironbeak Owl"
  :quality :common
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
  :quality :common
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
  :quality :common
  :class :druid
  :cost 0
  :spell (target-all [:my :minion] (buff-target 1 1))
}
{
  :id 513
  :name "Leper Gnome"
  :quality :common
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
  :class :shaman
  :cost 1
  :overload 1
  :spell (target [:character] (damage-target 3))
}
{
  :id 192
  :name "Lightspawn"
  :quality :common
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
  :cost 2
  :minion {
    :attack 3
    :health 2
    :battlecry (apply comp (repeat 3 (target-random [:character {:not :self}]
      (damage-target 1))))
  }
}
{
  :id 263
  :name "Mana Wyrm"
  :quality :common
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
  :class :druid
  :cost 3
  :spell (choose 430 133)
}
{
  :id 430
  :name "Mark of Nature"
  :quality :common
  :class :druid
  :cost 0
  :spell (target [:minion] (buff-target 4 0))
}
{
  :id 133
  :name "Mark of Nature"
  :quality :common
  :class :druid
  :cost 0
  :spell (target [:minion] (give-target {:health 4 :taunt true}))
}
{
  :id 229
  :name "Mind Shatter"
  :quality :common
  :class :priest
  :cost 2
  :hero-power (target [:character] (damage-target 3))
}
{
  :id 70
  :name "Mind Spike"
  :quality :common
  :class :priest
  :cost 2
  :hero-power (target [:character] (damage-target 2))
}
{
  :id 569
  :name "Mirror Entity"
  :quality :common
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
  :quality :common
  :class :druid
  :cost 0
  :spell (target [:character] (damage-target 2))
}
{
  :id 154
  :name "Naturalize"
  :quality :common
  :class :druid
  :cost 1
  :spell (target [:minion] #(-> % (destroy-target) (draw-opponent-cards 2)))
}
{
  :id 334
  :name "Nightmare"
  :quality :common
  :cost 0
  :spell (target [:minion] (give-target {
    :health 5 :attack 5
    :start-of-my-turn (destroy-self)}))
}
{
  :id 158
  :name "Noble Sacrifice"
  :quality :common
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
  :quality :common
  :class :druid
  :cost 0
  :spell (target [:my :hero] (give-target {:mana-crystal 2}))
}
{
  :id 485
  :name "Nourish"
  :quality :common
  :class :druid
  :cost 0
  :spell (draw-cards 3)
}
{
  :id 190
  :name "Panther"
  :quality :common
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
  :class :druid
  :cost 2
  :spell (choose "Summon a Panther" "Leader of the Pack")
}
{
  :id 170
  :name "Power Overwhelming"
  :quality :common
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
  :class :warrior
  :cost 2
  :spell (target [:minion :damaged] (buff-target 3 3))
}
{
  :id 657
  :name "Redemption"
  :quality :common
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
  :quality :common
  :class :druid
  :cost 0
  :spell (target [:self] (give-target {:health 5 :taunt true}))
}
{
  :id 475
  :name "Scarlet Crusader"
  :quality :common
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
  :class :warlock
  :cost 3
  :spell (target-random [2 :undrawn :demon]
    (draw-target) (give-card "Worthless Imp"))
}
{
  :id 550
  :name "Shadowstep"
  :quality :common
  :class :rogue
  :cost 0
  :spell (target [:my :minion] #(-> %
    (return-target)
    (give-target {:cost -2})))
}
{
  :id 159
  :name "Shan'do's Lesson"
  :quality :common
  :class :druid
  :cost 0
  :spell (summon-minion [181 181]) ; 181 = 2/2 Treant w/ Taunt
}
{
  :id 24
  :name "Shieldbearer"
  :quality :common
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
  :class :priest
  :cost 0
  :spell (target [:minion] (silence-target))
}
{
  :id 648
  :name "Silver Hand Knight"
  :quality :common
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
  :quality :common
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
  :class :warrior
  :cost 2
  :spell (target [:minion] #(-> %
    (damage-target 2)
    (if (not (is-dead (current-target %)))
      (draw-cards 1)))
}
{
  :id 512
  :name "Snake"
  :quality :common
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
  :quality :common
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
  :quality :common
  :class :druid
  :cost 0
  :spell (summon-minion "Panther")
}
{
  :id 566
  :name "Summoning Portal"
  :quality :common
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
  :class :priest
  :cost 3
  :spell (target-random [2 :opponent :undrawn] #(-> %
    (give-card (:id (current-target %)))))
}
{
  :id 265
  :name "Thrallmar Farseer"
  :quality :common
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
  :quality :common
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
  :quality :common
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
  :class :hunter
  :cost 4
  :spell (target-all [:opponent :minion] (summon-minion "Hound"))
}
{
  :id 262
  :name "Uproot"
  :quality :common
  :class :druid
  :cost 0
  :spell (target [:self] (buff-target 5 0))
}
{
  :id 509
  :name "Venture Co. Mercenary"
  :quality :common
  :cost 5
  :minion {
    :attack 7
    :health 6
    :aura (minions-cost #(+ % 3))
  }
}
{
  :id 63
  :name "Violet Apprentice"
  :quality :common
  :cost 0
  :minion {
    :attack 1
    :health 1
  }
}
{
  :id 527
  :name "Whelp"
  :quality :common
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
  :class :druid
  :cost 2
  :spell (choose 234 501)
}
{
  :id 234
  :name "Wrath"
  :quality :common
  :class :druid
  :cost 0
  :spell (target [:minion] (damage-target 3))
}
{
  :id 501
  :name "Wrath"
  :quality :common
  :class :druid
  :cost 0
  :spell (target [:minion] #(-> % (damage-target 1) (draw-cards 1)))
}
{
  :id 629
  :name "Young Dragonhawk"
  :quality :common
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
  :quality :common
  :cost 2
  :spell (target-all [#(not= (:name %) "Ysera")]
    (damage-target 5))
}
{
  :id 74
  :name "Acidic Swamp Ooze"
  :quality :free
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
  :quality :free
  :class :hunter
  :cost 3
  :spell #(-> % (summon-minion (rand-nth ["Misha" "Leokk" "Huffer"])))
}
{
  :id 182
  :name "Arcanite Reaper"
  :quality :free
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
  :quality :free
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
  :quality :free
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
  :quality :free
  :collectible false
  :cost 1
  :spell (target [:my :minion] (buff-target 1 1))
}
{
  :id 376
  :name "Barrel"
  :quality :free
  :collectible false
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
  :quality :free
  :collectible false
  :cost 1
  :spell (target [:character] (damage-target 2))
}
{
  :id 29
  :name "Blessing of Kings"
  :quality :free
  :class :paladin
  :cost 4
  :spell (target [:minion] (buff-target 4 4))
}
{
  :id 256
  :name "Bloodlust"
  :quality :free
  :class :shaman
  :cost 5
  :spell (target-all [:my :minion] (give-target {:attack-this-turn 3}))
}
{
  :id 289
  :name "Bluegill Warrior"
  :quality :free
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
  :quality :free
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
  :quality :free
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
  :quality :free
  :cost 4
  :minion {
    :attack 4
    :health 4
  }
}
{
  :id 31
  :name "Chillwind Yeti"
  :quality :free
  :cost 4
  :minion {
    :attack 4
    :health 5
  }
}
{
  :id 81
  :name "Cleave"
  :quality :free
  :class :warrior
  :cost 2
  ; todo: determine how to apply spell damage effects
  :spell (target-random [2 :opponent :minion] (damage-target 2))
}
{
  :id 260
  :name "Consecration"
  :quality :free
  :class :paladin
  :cost 4
  :spell (target-all [:opponent] (damage-target 2))
}
{
  :id 173
  :name "Core Hound"
  :quality :free
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
  :quality :free
  :class :warlock
  :cost 1
  :spell (target [:opponent :minion] (give-target
    {:start-of-my-turn (destroy-self)}))
}
{
  :id 377
  :name "Crazed Hunter"
  :quality :free
  :cost 1
  :minion {
    :attack 1
    :health 1
  }
}
{
  :id 393
  :name "Crazy Monkey"
  :quality :free
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
  :quality :free
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
  :quality :free
  :cost 4
  :minion {
    :attack 4
    :health 5
    :battlecry (target-all [:my :character] (restore-health 2))
  }
}
{
  :id 554
  :name "Divine Spirit"
  :quality :free
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
    ] (set-target {
      :max-health new-max-health
      :health doubled-health})))
}
])