#include "card.h"

Card cards[] = {{
  "Chicken", 552,
  Free, Debug, 0, Minion,
  Beast, 0, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Excess Mana", 520,
  Free, Basic, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Flames of Azzinoth", 349,
  Free, Missions, 0, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "I Am Murloc", 714,
  Free, Reward, 0, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Illidan Stormrage", 223,
  Free, Missions, Hunter, Hero,
  0, 0, 0, 30,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lorewalker Cho", 655,
  Free, Missions, 0, Hero,
  0, 0, 0, 25,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Murloc", 715,
  Free, Reward, 0, Minion,
  Beast, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "NOOOOOOOOOOOO", 687,
  Free, Basic, 0, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Power of the Horde", 717,
  Free, Reward, 0, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Rogues Do It...", 716,
  Free, Reward, 0, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "The Coin", 141,
  Free, Basic, 0, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Al'Akir the Windlord", 335,
  Legendary, Expert, Shaman, Minion,
  0, 8, 3, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Alexstrasza", 303,
  Legendary, Expert, 0, Minion,
  Dragon, 9, 8, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Archmage Antonidas", 220,
  Legendary, Expert, Mage, Minion,
  0, 7, 5, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ashbringer", 53,
  Legendary, Expert, Paladin, Weapon,
  0, 5, 5, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Baine Bloodhoof", 359,
  Legendary, Expert, 0, Minion,
  0, 4, 4, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Baron Geddon", 539,
  Legendary, Expert, 0, Minion,
  0, 7, 7, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bloodmage Thalnos", 525,
  Legendary, Expert, 0, Minion,
  0, 2, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Cairne Bloodhoof", 498,
  Legendary, Expert, 0, Minion,
  0, 6, 4, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Captain Greenskin", 267,
  Legendary, Expert, 0, Minion,
  Pirate, 5, 5, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Cenarius", 605,
  Legendary, Expert, Druid, Minion,
  0, 9, 5, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Deathwing", 474,
  Legendary, Expert, 0, Minion,
  Dragon, 10, 12, 12,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Edwin VanCleef", 3,
  Legendary, Expert, Rogue, Minion,
  0, 3, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Elite Tauren Chieftan", 682,
  Legendary, Reward, 0, Minion,
  0, 5, 5, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Finkle Einhorn", 541,
  Legendary, Expert, 0, Minion,
  0, 2, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Gelbin Mekkatorque", 251,
  Legendary, Reward, 0, Minion,
  0, 6, 6, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Grommash Hellscream", 643,
  Legendary, Expert, Warrior, Minion,
  0, 8, 4, 9,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Gruul", 18,
  Legendary, Expert, 0, Minion,
  0, 8, 7, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Harrison Jones", 602,
  Legendary, Expert, 0, Minion,
  0, 5, 5, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hogger", 39,
  Legendary, Expert, 0, Minion,
  0, 6, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Illidan Stormrage", 203,
  Legendary, Expert, 0, Minion,
  Demon, 6, 7, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "King Krush", 194,
  Legendary, Expert, Hunter, Minion,
  Beast, 9, 8, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "King Mukla", 373,
  Legendary, Expert, 0, Minion,
  Beast, 3, 5, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Leeroy Jenkins", 674,
  Legendary, Expert, 0, Minion,
  0, 4, 6, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lord Jaraxxus", 482,
  Legendary, Expert, Warlock, Minion,
  Demon, 9, 3, 15,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lord Jaraxxus", 406,
  Legendary, Expert, Warlock, Hero,
  0, 0, 0, 15,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lorewalker Cho", 456,
  Legendary, Expert, 0, Minion,
  0, 2, 0, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Malygos", 241,
  Legendary, Expert, 0, Minion,
  Dragon, 9, 4, 12,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Millhouse Manastorm", 339,
  Legendary, Expert, 0, Minion,
  0, 2, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Nat Pagle", 19,
  Legendary, Expert, 0, Minion,
  0, 2, 0, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Nozdormu", 285,
  Legendary, Expert, 0, Minion,
  Dragon, 9, 8, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Old Murk-Eye", 217,
  Legendary, Reward, 0, Minion,
  Murloc, 4, 2, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Onyxia", 432,
  Legendary, Expert, 0, Minion,
  Dragon, 9, 8, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Prophet Velen", 228,
  Legendary, Expert, Priest, Minion,
  0, 7, 7, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ragnaros the Firelord", 503,
  Legendary, Expert, 0, Minion,
  0, 8, 8, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sylvanas Windrunner", 33,
  Legendary, Expert, 0, Minion,
  0, 5, 5, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "The Beast", 179,
  Legendary, Expert, 0, Minion,
  Beast, 6, 9, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "The Black Knight", 396,
  Legendary, Expert, 0, Minion,
  0, 6, 4, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Tinkmaster Overspark", 245,
  Legendary, Expert, 0, Minion,
  0, 3, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Tirion Fordring", 391,
  Legendary, Expert, Paladin, Minion,
  0, 8, 6, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ysera", 495,
  Legendary, Expert, 0, Minion,
  0, 9, 4, 12,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Adrenaline Rush", 180,
  Epic, Expert, Rogue, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ancient of Lore", 34,
  Epic, Expert, Druid, Minion,
  0, 7, 5, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ancient of War", 242,
  Epic, Expert, Druid, Minion,
  0, 7, 5, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Avenging Wrath", 142,
  Epic, Expert, Paladin, Spell,
  0, 6, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bane of Doom", 670,
  Epic, Expert, Warlock, Spell,
  0, 5, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bestial Wrath", 304,
  Epic, Expert, Hunter, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Big Game Hunter", 73,
  Epic, Expert, 0, Minion,
  0, 3, 4, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Blood Knight", 75,
  Epic, Expert, 0, Minion,
  0, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Brawl", 297,
  Epic, Expert, Warrior, Spell,
  0, 5, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Cabal Shadow Priest", 147,
  Epic, Expert, Priest, Minion,
  0, 6, 4, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Captain's Parrot", 559,
  Epic, Reward, 0, Minion,
  Beast, 2, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Doomhammer", 172,
  Epic, Expert, Shaman, Weapon,
  0, 5, 2, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Doomsayer", 467,
  Epic, Expert, 0, Minion,
  0, 2, 0, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Earth Elemental", 124,
  Epic, Expert, Shaman, Minion,
  0, 5, 7, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Faceless Manipulator", 450,
  Epic, Expert, 0, Minion,
  0, 5, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Far Sight", 107,
  Epic, Expert, Shaman, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Force of Nature", 237,
  Epic, Expert, Druid, Spell,
  0, 6, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Gladiator's Longbow", 278,
  Epic, Expert, Hunter, Weapon,
  0, 7, 5, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Gorehowl", 96,
  Epic, Expert, Warrior, Weapon,
  0, 7, 7, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hungry Crab", 660,
  Epic, Expert, 0, Minion,
  Beast, 1, 1, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ice Block", 28,
  Epic, Expert, Mage, Secret,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Kidnapper", 562,
  Epic, Expert, Rogue, Minion,
  0, 6, 5, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lay on Hands", 506,
  Epic, Expert, Paladin, Spell,
  0, 8, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mindgames", 301,
  Epic, Expert, Priest, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Molten Giant", 94,
  Epic, Expert, 0, Minion,
  0, 31, 8, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mountain Giant", 264,
  Epic, Expert, 0, Minion,
  0, 31, 8, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Murloc Warleader", 222,
  Epic, Expert, 0, Minion,
  Murloc, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Patient Assassin", 14,
  Epic, Expert, Rogue, Minion,
  0, 2, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Pit Lord", 402,
  Epic, Expert, Warlock, Minion,
  Demon, 4, 5, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Placeholder Card", 102,
  Epic, None, Mage, Minion,
  0, 9, 6, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Preparation", 364,
  Epic, Expert, Rogue, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Pyroblast", 496,
  Epic, Expert, Mage, Spell,
  0, 8, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sea Giant", 614,
  Epic, Expert, 0, Minion,
  0, 31, 8, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shadow of Nothing", 582,
  Epic, Expert, Priest, Minion,
  0, 0, 0, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shadowform", 421,
  Epic, Expert, Priest, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shield Slam", 50,
  Epic, Expert, Warrior, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Snake Trap", 210,
  Epic, Expert, Hunter, Secret,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Southsea Captain", 324,
  Epic, Expert, 0, Minion,
  Pirate, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Spellbender", 309,
  Epic, Expert, Mage, Secret,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Spellbender", 645,
  Epic, Expert, Mage, Minion,
  0, 0, 1, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sword of Justice", 567,
  Epic, Expert, Paladin, Weapon,
  0, 3, 1, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Twisting Nether", 398,
  Epic, Expert, Warlock, Spell,
  0, 8, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Abomination", 597,
  Rare, Expert, 0, Minion,
  0, 5, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Alarm-o-Bot", 425,
  Rare, Expert, 0, Minion,
  0, 3, 0, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Aldor Peacekeeper", 23,
  Rare, Expert, Paladin, Minion,
  0, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ancestral Spirit", 526,
  Rare, Expert, Shaman, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ancient Mage", 176,
  Rare, Expert, 0, Minion,
  0, 4, 2, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ancient Watcher", 153,
  Rare, Expert, 0, Minion,
  0, 2, 4, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Angry Chicken", 57,
  Rare, Expert, 0, 0,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Arcane Golem", 97,
  Rare, Expert, 0, Minion,
  0, 3, 4, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Argent Commander", 463,
  Rare, Expert, 0, Minion,
  0, 6, 4, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Armorsmith", 644,
  Rare, Expert, Warrior, Minion,
  0, 2, 1, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Auchenai Soulpriest", 656,
  Rare, Expert, Priest, Minion,
  0, 4, 3, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Azure Drake", 280,
  Rare, Expert, 0, Minion,
  Dragon, 5, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bite", 266,
  Rare, Expert, Druid, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Blade Flurry", 244,
  Rare, Expert, Rogue, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Blessed Champion", 7,
  Rare, Expert, Paladin, Spell,
  0, 5, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Blizzard", 276,
  Rare, Expert, Mage, Spell,
  0, 6, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bloodsail Corsair", 453,
  Rare, Expert, 0, Minion,
  Pirate, 1, 1, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Coldlight Oracle", 88,
  Rare, Expert, 0, Minion,
  Murloc, 3, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Coldlight Seer", 424,
  Rare, Expert, 0, Minion,
  Murloc, 3, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Commanding Shout", 166,
  Rare, Expert, Warrior, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Counterspell", 531,
  Rare, Expert, Mage, Secret,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Crazed Alchemist", 612,
  Rare, Expert, 0, Minion,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Defender of Argus", 542,
  Rare, Expert, 0, Minion,
  0, 4, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Demolisher", 212,
  Rare, Expert, 0, Minion,
  0, 3, 1, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Divine Favor", 581,
  Rare, Expert, Paladin, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Doomguard", 507,
  Rare, Expert, Warlock, Minion,
  Demon, 5, 5, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Eaglehorn Bow", 363,
  Rare, Expert, Hunter, Weapon,
  0, 3, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Emperor Cobra", 625,
  Rare, Expert, 0, Minion,
  Beast, 3, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Equality", 383,
  Rare, Expert, Paladin, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ethereal Arcanist", 125,
  Rare, Expert, Mage, Minion,
  0, 4, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Explosive Shot", 114,
  Rare, Expert, Hunter, Spell,
  0, 5, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Felguard", 236,
  Rare, Expert, Warlock, Minion,
  Demon, 3, 3, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Feral Spirit", 214,
  Rare, Expert, Shaman, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Flare", 630,
  Rare, Expert, Hunter, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Frothing Berserker", 69,
  Rare, Expert, Warrior, Minion,
  0, 3, 2, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Gadgetzan Auctioneer", 131,
  Rare, Expert, 0, Minion,
  0, 5, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Headcrack", 135,
  Rare, Expert, Rogue, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Holy Fire", 457,
  Rare, Expert, Priest, Spell,
  0, 6, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Holy Wrath", 355,
  Rare, Expert, Paladin, Spell,
  0, 5, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hyena", 689,
  Rare, Expert, Hunter, Minion,
  Beast, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Imp", 321,
  Rare, Expert, 0, Minion,
  Demon, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Imp Master", 178,
  Rare, Expert, 0, Minion,
  0, 3, 1, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Injured Blademaster", 209,
  Rare, Expert, 0, Minion,
  0, 3, 4, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Keeper of the Grove", 459,
  Rare, Expert, Druid, Minion,
  0, 4, 2, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Kirin Tor Mage", 411,
  Rare, Expert, Mage, Minion,
  0, 3, 4, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Knife Juggler", 422,
  Rare, Expert, 0, Minion,
  0, 2, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lava Burst", 679,
  Rare, Expert, Shaman, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lightning Storm", 676,
  Rare, Expert, Shaman, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lightwarden", 436,
  Rare, Expert, 0, Minion,
  0, 1, 1, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lightwell", 117,
  Rare, Expert, Priest, Minion,
  0, 2, 0, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mana Addict", 67,
  Rare, Expert, 0, Minion,
  0, 2, 1, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mana Tide Totem", 613,
  Rare, Expert, Shaman, Minion,
  Totem, 3, 0, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mana Wraith", 197,
  Rare, Expert, 0, Minion,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mass Dispel", 249,
  Rare, Expert, Priest, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Master of Disguise", 127,
  Rare, Expert, Rogue, Minion,
  0, 4, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Master Swordsmith", 584,
  Rare, Expert, 0, Minion,
  0, 2, 1, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mind Control Tech", 368,
  Rare, Expert, 0, Minion,
  0, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Misdirection", 447,
  Rare, Expert, Hunter, Secret,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mortal Strike", 345,
  Rare, Expert, Warrior, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Murloc Tidecaller", 420,
  Rare, Expert, 0, Minion,
  Murloc, 1, 1, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Nourish", 120,
  Rare, Expert, Druid, Spell,
  0, 5, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Perdition's Blade", 82,
  Rare, Expert, Rogue, Weapon,
  0, 3, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Pint-Sized Summoner", 54,
  Rare, Expert, 0, Minion,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Questing Adventurer", 157,
  Rare, Expert, 0, Minion,
  0, 3, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ravenholdt Assassin", 518,
  Rare, Expert, 0, Minion,
  0, 7, 7, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Savagery", 148,
  Rare, Expert, Druid, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Savannah Highmane", 8,
  Rare, Expert, Hunter, Minion,
  Beast, 6, 6, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Secretkeeper", 483,
  Rare, Expert, 0, Minion,
  0, 1, 1, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shadow Madness", 442,
  Rare, Expert, Priest, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shadowflame", 673,
  Rare, Expert, Warlock, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "SI7 Agent", 286,
  Rare, Expert, Rogue, Minion,
  0, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Siphon Soul", 573,
  Rare, Expert, Warlock, Spell,
  0, 6, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Spirit Wolf", 451,
  Rare, Expert, Shaman, Minion,
  0, 2, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Stampeding Kodo", 389,
  Rare, Expert, 0, Minion,
  Beast, 5, 3, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Starfall", 464,
  Rare, Expert, Druid, Spell,
  0, 5, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sunfury Protector", 372,
  Rare, Expert, 0, Minion,
  0, 2, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sunwalker", 221,
  Rare, Expert, 0, Minion,
  0, 6, 4, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Twilight Drake", 360,
  Rare, Expert, 0, Minion,
  Dragon, 4, 4, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Upgrade!", 638,
  Rare, Expert, Warrior, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Vaporize", 160,
  Rare, Expert, Mage, Secret,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Violet Teacher", 523,
  Rare, Expert, 0, Minion,
  0, 4, 3, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Void Terror", 119,
  Rare, Expert, Warlock, Minion,
  Demon, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Wild Pyromancer", 25,
  Rare, Expert, 0, Minion,
  0, 2, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Young Priestess", 123,
  Rare, Expert, 0, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Abusive Sergeant", 577,
  Common, Expert, 0, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Acolyte of Pain", 428,
  Common, Expert, 0, Minion,
  0, 3, 1, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Amani Berserker", 641,
  Common, Expert, 0, Minion,
  0, 2, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ancient Brewmaster", 572,
  Common, Expert, 0, Minion,
  0, 4, 5, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ancient Secrets", 243,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ancient Teachings", 517,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Arathi Weaponsmith", 504,
  Common, Expert, Warrior, Minion,
  0, 4, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Argent Protector", 191,
  Common, Expert, Paladin, Minion,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Argent Squire", 473,
  Common, Expert, 0, Minion,
  0, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bananas", 231,
  Free, Expert, 0, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Battle Axe", 403,
  Free, Expert, Warrior, Weapon,
  0, 1, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Battle Rage", 664,
  Common, Expert, Warrior, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bear Form", 662,
  Common, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Betrayal", 198,
  Common, Expert, Rogue, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Blessing of Wisdom", 100,
  Common, Expert, Paladin, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Blood Fury", 669,
  Free, Expert, Warlock, Weapon,
  0, 3, 3, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Blood Imp", 196,
  Common, Expert, Warlock, Minion,
  Demon, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bloodsail Raider", 637,
  Common, Expert, 0, Minion,
  Pirate, 2, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Cat Form", 287,
  Common, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Circle of Healing", 38,
  Common, Expert, Priest, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Cold Blood", 92,
  Common, Expert, Rogue, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Conceal", 284,
  Common, Expert, Rogue, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Cone of Cold", 26,
  Common, Expert, Mage, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Cruel Taskmaster", 328,
  Common, Expert, Warrior, Minion,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Cult Master", 140,
  Common, Expert, 0, Minion,
  0, 4, 4, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Damaged Golem", 200,
  Common, Expert, 0, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dark Iron Dwarf", 128,
  Common, Expert, 0, Minion,
  0, 4, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Deadly Shot", 239,
  Common, Expert, Hunter, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Defender", 318,
  Common, Expert, Paladin, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Defias Bandit", 9,
  Free, Expert, Rogue, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Defias Ringleader", 417,
  Common, Expert, Rogue, Minion,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Demigod's Favor", 358,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Demonfire", 452,
  Common, Expert, Warlock, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Devilsaur", 354,
  Common, Expert, 0, Minion,
  Beast, 5, 5, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dire Wolf Alpha", 305,
  Common, Expert, 0, Minion,
  Beast, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dispel", 524,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dread Corsair", 261,
  Common, Expert, 0, Minion,
  Pirate, 31, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dream", 561,
  Free, Expert, 0, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Druid of the Claw", 587,
  Common, Expert, Druid, Minion,
  0, 5, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Druid of the Claw", 408,
  Common, Expert, Druid, Minion,
  0, 5, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Druid of the Claw", 45,
  Common, Expert, Druid, Minion,
  0, 5, 4, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dust Devil", 129,
  Common, Expert, Shaman, Minion,
  0, 1, 3, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Earth Shock", 77,
  Common, Expert, Shaman, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Earthen Ring Farseer", 557,
  Common, Expert, 0, Minion,
  0, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Emerald Drake", 534,
  Free, Expert, 0, Minion,
  Dragon, 4, 7, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Eviscerate", 382,
  Common, Expert, Rogue, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Explosive Trap", 344,
  Common, Expert, Hunter, Secret,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Eye for an Eye", 206,
  Common, Expert, Paladin, Secret,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Faerie Dragon", 213,
  Common, Expert, 0, Minion,
  Dragon, 2, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Fen Creeper", 476,
  Common, Expert, 0, Minion,
  0, 5, 3, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Flame Imp", 85,
  Common, Expert, Warlock, Minion,
  Demon, 1, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Flame of Azzinoth", 685,
  Free, Expert, 0, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Flesheating Ghoul", 610,
  Common, Expert, 0, Minion,
  0, 3, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Forked Lightning", 530,
  Common, Expert, Shaman, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Freezing Trap", 99,
  Common, Expert, Hunter, Secret,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Frost Elemental", 598,
  Common, Expert, 0, Minion,
  0, 6, 5, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Gnoll", 565,
  Free, Expert, 0, Minion,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Harvest Golem", 386,
  Common, Expert, 0, Minion,
  0, 3, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Heavy Axe", 583,
  Free, Expert, Warrior, Weapon,
  0, 1, 1, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ice Barrier", 672,
  Common, Expert, Mage, Secret,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ice Lance", 188,
  Common, Expert, Mage, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Infernal", 121,
  Common, Expert, Warlock, Minion,
  Demon, 6, 6, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "INFERNO!", 83,
  Free, Expert, Warlock, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Inner Fire", 207,
  Common, Expert, Priest, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Inner Rage", 366,
  Common, Expert, Warrior, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ironbeak Owl", 500,
  Common, Expert, 0, Minion,
  Beast, 2, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Jungle Panther", 392,
  Common, Expert, 0, Minion,
  Beast, 3, 4, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Laughing Sister", 116,
  Free, Expert, 0, Minion,
  0, 3, 3, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Leader of the Pack", 204,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Leper Gnome", 513,
  Common, Expert, 0, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lightning Bolt", 10,
  Common, Expert, Shaman, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lightspawn", 192,
  Common, Expert, Priest, Minion,
  0, 4, 0, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Loot Hoarder", 395,
  Common, Expert, 0, Minion,
  0, 2, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mad Bomber", 80,
  Common, Expert, 0, Minion,
  0, 2, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mana Wyrm", 263,
  Common, Expert, Mage, Minion,
  0, 1, 1, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mark of Nature", 149,
  Common, Expert, Druid, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mark of Nature", 430,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mark of Nature", 133,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mind Shatter", 229,
  Free, Expert, Priest, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mind Spike", 70,
  Free, Expert, Priest, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mirror Entity", 569,
  Common, Expert, Mage, Secret,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mogu'shan Warden", 346,
  Common, Expert, 0, Minion,
  0, 4, 1, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Moonfire", 111,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Naturalize", 154,
  Common, Expert, Druid, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Nightmare", 334,
  Free, Expert, 0, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Noble Sacrifice", 158,
  Common, Expert, Paladin, Secret,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Nourish", 58,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Nourish", 485,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Panther", 190,
  Common, Expert, Druid, Minion,
  Beast, 2, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Power of the Wild", 165,
  Common, Expert, Druid, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Power Overwhelming", 170,
  Common, Expert, Warlock, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Priestess of Elune", 138,
  Common, Expert, 0, Minion,
  0, 6, 5, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Raging Worgen", 95,
  Common, Expert, 0, Minion,
  0, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Rampage", 454,
  Common, Expert, Warrior, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Redemption", 657,
  Common, Expert, Paladin, Secret,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Repentance", 642,
  Common, Expert, Paladin, Secret,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Rooted", 375,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Scarlet Crusader", 475,
  Common, Expert, 0, Minion,
  0, 3, 3, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Scavenging Hyena", 279,
  Common, Expert, Hunter, Minion,
  Beast, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sense Demons", 327,
  Common, Expert, Warlock, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shadowstep", 550,
  Common, Expert, Rogue, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shan'do's Lesson", 159,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shieldbearer", 24,
  Common, Expert, 0, Minion,
  0, 1, 0, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Silence", 544,
  Common, Expert, Priest, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Silver Hand Knight", 648,
  Common, Expert, 0, Minion,
  0, 5, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Silvermoon Guardian", 634,
  Common, Expert, 0, Minion,
  0, 4, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Skeleton", 255,
  Free, Expert, 0, Minion,
  0, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Slam", 215,
  Common, Expert, Warrior, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Snake", 512,
  Common, Expert, Hunter, Minion,
  Beast, 0, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Snipe", 553,
  Common, Expert, Hunter, Secret,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sorcerer's Apprentice", 4,
  Common, Expert, Mage, Minion,
  0, 2, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Soul of the Forest", 311,
  Common, Expert, Druid, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Southsea Deckhand", 103,
  Common, Expert, 0, Minion,
  Pirate, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Spellbreaker", 42,
  Common, Expert, 0, Minion,
  0, 4, 4, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Spiteful Smith", 627,
  Common, Expert, Warrior, Minion,
  0, 5, 4, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Squire", 21,
  Common, Expert, 0, Minion,
  0, 1, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Squirrel", 469,
  Common, Expert, 0, Minion,
  Beast, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Starfall", 653,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Starfall", 195,
  Common, 0, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Stormforged Axe", 152,
  Common, Expert, Shaman, Weapon,
  0, 2, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Stranglethorn Tiger", 338,
  Common, Expert, 0, Minion,
  Beast, 5, 5, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Summon a Panther", 219,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Summoning Portal", 566,
  Common, Expert, Warlock, Minion,
  0, 4, 0, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Tauren Warrior", 477,
  Common, Expert, 0, Minion,
  0, 3, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Temple Enforcer", 232,
  Common, Expert, Priest, Minion,
  0, 6, 6, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Thoughtsteal", 62,
  Common, Expert, Priest, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Thrallmar Farseer", 265,
  Common, Expert, 0, Minion,
  0, 3, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Treant", 272,
  Free, Expert, Druid, Minion,
  0, 1, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Treant", 337,
  Common, Expert, Druid, Minion,
  0, 1, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Treant", 181,
  Free, Expert, Druid, Minion,
  0, 1, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Unbound Elemental", 51,
  Common, Expert, Shaman, Minion,
  0, 3, 2, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Unleash the Hounds", 317,
  Common, Expert, Hunter, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Uproot", 262,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Venture Co. Mercenary", 509,
  Common, Expert, 0, Minion,
  0, 5, 7, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Violet Apprentice", 63,
  Free, Expert, 0, Minion,
  0, 0, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Whelp", 527,
  Free, Expert, 0, Minion,
  Dragon, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Windfury Harpy", 675,
  Common, Expert, 0, Minion,
  0, 6, 4, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Wisp", 273,
  Common, Expert, 0, Minion,
  0, 0, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Worgen Infiltrator", 112,
  Common, Expert, 0, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Worthless Imp", 230,
  Common, Expert, Warlock, Minion,
  Demon, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Wrath", 633,
  Common, Expert, Druid, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Wrath", 234,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Wrath", 501,
  Free, Expert, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Young Dragonhawk", 629,
  Common, Expert, 0, Minion,
  Beast, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Youthful Brewmaster", 247,
  Common, Expert, 0, Minion,
  0, 2, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ysera Awakens", 235,
  Free, Expert, 0, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Acidic Swamp Ooze", 74,
  Common, Basic, 0, Minion,
  0, 2, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Animal Companion", 578,
  Common, Basic, Hunter, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Arcanite Reaper", 182,
  Common, Basic, Warrior, Weapon,
  0, 5, 5, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Archmage", 545,
  Common, Basic, 0, Minion,
  0, 6, 4, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Assassin's Blade", 433,
  Common, Basic, Rogue, Weapon,
  0, 5, 3, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bananas", 156,
  Common, Missions, 0, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Barrel", 376,
  Common, Missions, 0, Minion,
  0, 0, 0, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Barrel Toss", 606,
  Common, Missions, 0, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Blessing of Kings", 29,
  Common, Basic, Paladin, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bloodlust", 256,
  Common, Basic, Shaman, Spell,
  0, 5, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bluegill Warrior", 289,
  Common, Basic, 0, Minion,
  Murloc, 2, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Boar", 65,
  Common, Basic, 0, Minion,
  Beast, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Booty Bay Bodyguard", 27,
  Common, Basic, 0, Minion,
  0, 5, 5, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Brewmaster", 397,
  Common, Missions, 0, Minion,
  0, 4, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Chillwind Yeti", 31,
  Common, Basic, 0, Minion,
  0, 4, 4, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Cleave", 81,
  Common, Basic, Warrior, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Consecration", 260,
  Common, Basic, Paladin, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Core Hound", 173,
  Common, Basic, 0, Minion,
  Beast, 7, 9, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Corruption", 252,
  Common, Basic, Warlock, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Crazed Hunter", 377,
  Common, Missions, 0, Minion,
  0, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Crazy Monkey", 393,
  Common, Missions, 0, Minion,
  0, 1, 1, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dalaran Mage", 388,
  Common, Basic, 0, Minion,
  0, 3, 1, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Darkscale Healer", 84,
  Common, Basic, 0, Minion,
  0, 4, 4, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Divine Spirit", 554,
  Common, Basic, Priest, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dragonling Mechanic", 472,
  Common, Basic, 0, Minion,
  0, 4, 2, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dread Infernal", 36,
  Common, Basic, Warlock, Minion,
  Demon, 6, 6, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dual Warglaives", 599,
  Common, Missions, 0, Weapon,
  0, 6, 4, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Elven Archer", 356,
  Common, Basic, 0, Minion,
  0, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Emboldener 3000", 240,
  Common, Reward, 0, Minion,
  0, 1, 0, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Fan of Knives", 378,
  Common, Basic, Rogue, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Fire Elemental", 636,
  Common, Basic, 0, Minion,
  0, 6, 6, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Flame Burst", 622,
  Common, Missions, 0, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Flame of Azzinoth", 455,
  Common, Missions, 0, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Flamestrike", 44,
  Common, Basic, Mage, Spell,
  0, 7, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Flametongue Totem", 390,
  Common, Basic, Shaman, Minion,
  Totem, 2, 0, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Frog", 115,
  Common, Basic, 0, Minion,
  Beast, 0, 0, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Frost Nova", 49,
  Common, Basic, Mage, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Frostbolt", 177,
  Common, Basic, Mage, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Frostwolf Grunt", 663,
  Common, Basic, 0, Minion,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Frostwolf Warlord", 604,
  Common, Basic, 0, Minion,
  0, 5, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Gnoll", 381,
  Common, Missions, 0, Minion,
  0, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Gnomish Inventor", 246,
  Common, Basic, 0, Minion,
  0, 4, 2, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Goldshire Footman", 564,
  Common, Basic, 0, Minion,
  0, 1, 1, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Grimscale Oracle", 510,
  Common, Basic, 0, Minion,
  Murloc, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Guardian of Kings", 283,
  Common, Basic, Paladin, Minion,
  0, 7, 5, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Gurubashi Berserker", 624,
  Common, Basic, 0, Minion,
  0, 5, 2, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hemet Nesingwary", 470,
  Common, Missions, 0, Hero,
  0, 0, 0, 20,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hidden Gnome", 387,
  Common, Missions, 0, Minion,
  0, 2, 1, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hogger", 490,
  Common, Missions, 0, Hero,
  0, 0, 0, 10,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hogger SMASH!", 443,
  Common, Missions, 0, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Holy Nova", 671,
  Common, Basic, Priest, Spell,
  0, 5, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Homing Chicken", 423,
  Common, Reward, 0, Minion,
  0, 1, 0, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Huffer", 369,
  Common, Basic, Hunter, Minion,
  Beast, 3, 4, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Humility", 189,
  Common, Basic, Paladin, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hunter's Mark", 22,
  Common, Basic, Hunter, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ironbark Protector", 238,
  Common, Basic, Druid, Minion,
  0, 8, 8, 8,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ironforge Rifleman", 41,
  Common, Basic, 0, Minion,
  0, 3, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ironfur Grizzly", 519,
  Common, Basic, 0, Minion,
  Beast, 3, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Jaina Proudmoore", 139,
  Common, Missions, Mage, Hero,
  0, 0, 0, 27,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Kill Command", 488,
  Common, Basic, Hunter, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "King Mukla", 444,
  Common, Missions, 0, Hero,
  0, 0, 0, 26,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Kobold Geomancer", 479,
  Common, Basic, 0, Minion,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Kor'kron Elite", 130,
  Common, Basic, Warrior, Minion,
  0, 4, 4, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Legacy of the Emperor", 441,
  Common, Missions, 0, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Leokk", 32,
  Common, Basic, Hunter, Minion,
  0, 3, 2, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lord of the Arena", 414,
  Common, Basic, 0, Minion,
  0, 6, 6, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Massive Gnoll", 137,
  Common, Missions, 0, Minion,
  0, 4, 5, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mechanical Dragonling", 680,
  Common, Basic, 0, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Metamorphosis", 592,
  Common, Missions, 0, Spell,
  0, 6, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Millhouse Manastorm", 330,
  Common, Missions, Mage, Hero,
  0, 0, 0, 20,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mind Control", 401,
  Common, Basic, Priest, Spell,
  0, 10, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mind Vision", 438,
  Common, Basic, Priest, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mirror Image", 30,
  Common, Basic, Mage, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mirror Image", 650,
  Common, Basic, Mage, Minion,
  0, 0, 0, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Misha", 593,
  Common, Basic, Hunter, Minion,
  Beast, 3, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Moonfire", 619,
  Common, Basic, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mortal Coil", 43,
  Common, Basic, Warlock, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mukla's Big Brother", 404,
  Common, Missions, 0, Minion,
  0, 6, 10, 10,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Murloc Scout", 486,
  Common, Basic, 0, Minion,
  Murloc, 0, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Murloc Tidehunter", 357,
  Common, Basic, 0, Minion,
  Murloc, 2, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Naga Myrmidon", 16,
  Common, Missions, 0, Minion,
  0, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ogre Magi", 659,
  Common, Basic, 0, Minion,
  0, 4, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Pandaren Scout", 399,
  Common, Missions, 0, Minion,
  0, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Poultryizer", 405,
  Common, Reward, 0, Minion,
  0, 1, 0, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Razorfen Hunter", 47,
  Common, Basic, 0, Minion,
  0, 3, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Repair Bot", 439,
  Common, Reward, 0, Minion,
  0, 1, 0, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Riverpaw Gnoll", 678,
  Common, Missions, 0, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sacrificial Pact", 348,
  Common, Basic, Warlock, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Savage Roar", 329,
  Common, Basic, Druid, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shado-Pan Monk", 288,
  Common, Missions, 0, Minion,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shadow Word: Death", 547,
  Common, Basic, Priest, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shattered Sun Cleric", 434,
  Common, Basic, 0, Minion,
  0, 3, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sheep", 218,
  Common, Basic, 0, Minion,
  Beast, 0, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shield Block", 493,
  Common, Basic, Warrior, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shiv", 164,
  Common, Basic, Rogue, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shotgun Blast", 580,
  Common, Missions, Hunter, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Silverback Patriarch", 611,
  Common, Basic, 0, Minion,
  0, 3, 1, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Soulfire", 529,
  Common, Basic, Warlock, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sprint", 90,
  Common, Basic, Rogue, Spell,
  0, 7, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Starfire", 667,
  Common, Basic, Druid, Spell,
  0, 6, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Starving Buzzard", 101,
  Common, Basic, Hunter, Minion,
  Beast, 2, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Stomp", 105,
  Common, Missions, 0, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Stormpike Commando", 325,
  Common, Basic, 0, Minion,
  0, 5, 4, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Stormwind Champion", 310,
  Common, Basic, 0, Minion,
  0, 7, 6, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Stormwind Knight", 603,
  Common, Basic, 0, Minion,
  0, 4, 2, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Swipe", 620,
  Common, Basic, Druid, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Totemic Might", 367,
  Common, Basic, Shaman, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Transcendence", 299,
  Common, Missions, 0, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Truesilver Champion", 293,
  Common, Basic, Paladin, Weapon,
  0, 4, 4, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Tundra Rhino", 162,
  Common, Basic, Hunter, Minion,
  Beast, 5, 2, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Vanish", 658,
  Common, Basic, Rogue, Spell,
  0, 6, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "War Golem", 323,
  Common, Basic, 0, Minion,
  0, 7, 7, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Warglaive of Azzinoth", 494,
  Common, Missions, 0, Weapon,
  0, 2, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Water Elemental", 274,
  Common, Basic, Mage, Minion,
  0, 4, 3, 6,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Whirlwind", 161,
  Common, Basic, Warrior, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Will of Mukla", 586,
  Common, Missions, 0, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Windspeaker", 151,
  Common, Basic, Shaman, Minion,
  0, 4, 3, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Ancestral Healing", 216,
  Free, Basic, Shaman, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Anduin Wrynn", 110,
  Free, Basic, Priest, Hero,
  0, 0, 0, 30,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Arcane Explosion", 56,
  Free, Basic, Mage, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Arcane Intellect", 489,
  Free, Basic, Mage, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Arcane Missiles", 589,
  Free, Basic, Mage, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Arcane Shot", 167,
  Free, Basic, Hunter, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Armor Up!", 253,
  Free, Basic, Warrior, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Assassinate", 568,
  Free, Basic, Rogue, Spell,
  0, 5, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Avatar of the Coin", 666,
  Free, Basic, 0, Minion,
  0, 0, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Backstab", 471,
  Free, Basic, Rogue, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Blessing of Might", 394,
  Free, Basic, Paladin, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Bloodfen Raptor", 576,
  Free, Basic, 0, Minion,
  Beast, 2, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Boulderfist Ogre", 60,
  Free, Basic, 0, Minion,
  0, 6, 6, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Charge", 646,
  Free, Basic, Warrior, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Claw", 532,
  Free, Basic, Druid, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Dagger Mastery", 201,
  Free, Basic, Rogue, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Deadly Poison", 87,
  Free, Basic, Rogue, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Drain Life", 332,
  Free, Basic, Warlock, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Execute", 227,
  Free, Basic, Warrior, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Fiery War Axe", 632,
  Free, Basic, Warrior, Weapon,
  0, 2, 3, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Fireball", 522,
  Free, Basic, Mage, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Fireblast", 677,
  Free, Basic, Mage, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Frost Shock", 233,
  Free, Basic, Shaman, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Garrosh Hellscream", 635,
  Free, Basic, Warrior, Hero,
  0, 0, 0, 30,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Gul'dan", 618,
  Free, Basic, Warlock, Hero,
  0, 0, 0, 30,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hammer of Wrath", 350,
  Free, Basic, Paladin, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hand of Protection", 499,
  Free, Basic, Paladin, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Healing Totem", 275,
  Free, Basic, Shaman, Minion,
  Totem, 1, 0, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Healing Touch", 258,
  Free, Basic, Druid, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hellfire", 122,
  Free, Basic, Warlock, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Heroic Strike", 1,
  Free, Basic, Warrior, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Hex", 270,
  Free, Basic, Shaman, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Holy Light", 108,
  Free, Basic, Paladin, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Holy Smite", 409,
  Free, Basic, Priest, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Houndmaster", 225,
  Free, Basic, Hunter, Minion,
  0, 4, 4, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Innervate", 548,
  Free, Basic, Druid, Spell,
  0, 0, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Jaina Proudmoore", 320,
  Free, Basic, Mage, Hero,
  0, 0, 0, 30,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Lesser Heal", 126,
  Free, Basic, Priest, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Life Tap", 20,
  Free, Basic, Warlock, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Light's Justice", 250,
  Free, Basic, Paladin, Weapon,
  0, 1, 1, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Magma Rager", 362,
  Free, Basic, 0, Minion,
  0, 3, 5, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Malfurion Stormrage", 621,
  Free, Basic, Druid, Hero,
  0, 0, 0, 30,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mark of the Wild", 480,
  Free, Basic, Druid, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Mind Blast", 415,
  Free, Basic, Priest, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Multi-Shot", 407,
  Free, Basic, Hunter, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Murloc Raider", 55,
  Free, Basic, 0, Minion,
  Murloc, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Nightblade", 184,
  Free, Basic, 0, Minion,
  0, 5, 4, 4,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Northshire Cleric", 600,
  Free, Basic, Priest, Minion,
  0, 1, 1, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Novice Engineer", 435,
  Free, Basic, 0, Minion,
  0, 2, 1, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Oasis Snapjaw", 15,
  Free, Basic, 0, Minion,
  Beast, 4, 2, 7,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Polymorph", 595,
  Free, Basic, Mage, Spell,
  0, 4, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Power Word: Shield", 431,
  Free, Basic, Priest, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Raid Leader", 502,
  Free, Basic, 0, Minion,
  0, 3, 2, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Reckless Rocketeer", 560,
  Free, Basic, 0, Minion,
  0, 6, 5, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Reinforce", 248,
  Free, Basic, Paladin, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Rexxar", 484,
  Free, Basic, Hunter, Hero,
  0, 0, 0, 30,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "River Crocolisk", 535,
  Free, Basic, 0, Minion,
  Beast, 2, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Rockbiter Weapon", 491,
  Free, Basic, Shaman, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sap", 385,
  Free, Basic, Rogue, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Searing Totem", 98,
  Free, Basic, Shaman, Minion,
  Totem, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sen'jin Shieldmasta", 326,
  Free, Basic, 0, Minion,
  0, 4, 3, 5,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shadow Bolt", 647,
  Free, Basic, Warlock, Spell,
  0, 3, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shadow Word: Pain", 315,
  Free, Basic, Priest, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Shapeshift", 185,
  Free, Basic, Druid, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Silver Hand Recruit", 268,
  Free, Basic, Paladin, Minion,
  0, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Sinister Strike", 205,
  Free, Basic, Rogue, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Steady Shot", 481,
  Free, Basic, Hunter, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Stoneclaw Totem", 298,
  Free, Basic, Shaman, Minion,
  Totem, 1, 0, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Stonetusk Boar", 76,
  Free, Basic, 0, Minion,
  Beast, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Succubus", 208,
  Free, Basic, Warlock, Minion,
  Demon, 2, 4, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Thrall", 319,
  Free, Basic, Shaman, Hero,
  0, 0, 0, 30,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Timber Wolf", 86,
  Free, Basic, Hunter, Minion,
  Beast, 1, 1, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Totemic Call", 316,
  Free, Basic, Shaman, Hero_power,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Tracking", 163,
  Free, Basic, Hunter, Spell,
  0, 1, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Uther Lightbringer", 257,
  Free, Basic, Paladin, Hero,
  0, 0, 0, 30,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Valeera Sanguinar", 2,
  Free, Basic, Rogue, Hero,
  0, 0, 0, 30,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Voidwalker", 340,
  Free, Basic, Warlock, Minion,
  Demon, 1, 1, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Voodoo Doctor", 410,
  Free, Basic, 0, Minion,
  0, 1, 2, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Warsong Commander", 193,
  Free, Basic, Warrior, Minion,
  0, 3, 2, 3,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Wicked Knife", 183,
  Free, Basic, Rogue, Weapon,
  0, 1, 1, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Wild Growth", 282,
  Free, Basic, Druid, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Windfury", 146,
  Free, Basic, Shaman, Spell,
  0, 2, 0, 0,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Wolfrider", 174,
  Free, Basic, 0, Minion,
  0, 3, 3, 1,
  nil, nil, nil, nil, nil, nil, nil, nil
}, {
  "Wrath of Air Totem", 365,
  Free, Basic, Shaman, Minion,
  Totem, 1, 0, 2,
  nil, nil, nil, nil, nil, nil, nil, nil
}};