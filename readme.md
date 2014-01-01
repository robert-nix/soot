- tray contains mana crystals (current/max "spent+unspent")
- hero character has health, armor, hero power, weapon?
- hand contains cards, limit of 10 (new cards destroyed)
- board contains minions, limit of 7 per side (new minions destroyed)
- deck contains undrawn cards, starts at 30; at 0, fatigue kicks in, damaging
the player (1 2 3 4 5 ... damage) on any attempted draw

minions contain:
  - Name, Cost, Attack, Current Health, Maximum Health
  - Type (Beast, Demon, Dragon, Murloc, Pirate, Totem, General)
  - Properties (Taunt, Windfury, Charge, Stealth, Divine Shield)
  - Effects (event-based e.g. Battlecry, Deathrattle, Enrage, EoT/BoT)
  - External buffs (e.g. Spell Damage)

words in fn names:

- my: refers to the current controller in the game state or to the controller of
the minion for which an action is being performed
- opponent: the opposite of 'my'

- game state is always first arg
- functions that don't return a game state are normally called directly, whereas
functions that do return a game state are generally partially applied
filters are common
- values/functions interchangeable (call the function against the current game
state to get the current value)

card states:
 - :undrawn
 - :drawn
 - :played
 - :dead
