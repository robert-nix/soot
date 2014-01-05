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

functions:

- game state is always first arg
- functions that don't return a game state are normally called directly, whereas
functions that do return a game state are generally partially applied
- filters are common
- values/functions interchangeable (call the function against the current game
state to get the current value)

card states:
 - :undrawn
 - :drawn
 - :played
 - :dead

card types:
 - :weapon
 - :character (includes :hero, :minion)
 - :hero
 - :minion
 - :spell
 - :hero-power

tempted to remove `-target` from loads of function names, need to double check
"normativity" of that though

might replace 'opponent' with 'enemy'

lots of todos in cards:  saving them for when I have a complete picture of the
structure of the game state

in give- and in :aura, :health is assumed to buff :max-health as well (as
otherwise it'd be a restore)

a target is damaged if it is below its original health? test with warrior...

really need to distinguish spell damage buffed abilities.  arcane missiles needs
a way to send more missiles by spell damage, too, (same with avenging wrath
etc.)

## function notes

### targeting

- `(target filter expr)`; filter may be a single or a coll of keywords and/or
functions.  functions are applied with the contextual target as the sole
argument and should return a boolean (true include/false exclude).  colls are
combined using logical and.  when filtering cards, cards are filtered for :state
:played and :character unless an overriding keyword is provided.  an empty
vector allows the default filtering.  this behavior belongs to filter-all. expr
functions are applied with the state as the sole argument, and this state
contains the target as a part of its context.  often for target, colls are used
for single keywords just cause it looks nicer.

- `(target-random)`; while (target ...) will require input from the user if more
than one viable target is found, (target-random ...) will choose randomly from
viable targets.  if a number is supplied to the filter coll for target-random,
that number of ''unique'' targets will be chosen.  a second expr may be supplied

`(target-random filter expr fallback)` that is called for the times where a
viable target does not exist.

- `(target-all)`; (target-all ...) applies expr to every viable target, not just
one.

### choose one

- `(choose one two)`; requires input to choose between two possible spells.
spells can be numbers or strings referring to ids or names of the spell to cast

### combo

- `(combo normal-expr combo-expr)`; wrapper for rogue combo spells.  combo on
minions is an optional battlecry, but on spells the spell effect branches based
on combo state, so it's sometimes helpful to describe it as a branch instead of
as a property of the card (although :combo can be used on spells as well)

### verbs on cards

- draw-: undrawn -> drawn
- summon-: (undrawn, drawn, nil) -> played; summon- is often called with a
string, number, or collection of strings/numbers, in which case cards are
created before being summoned.
- play-: drawn -> played
- return-: played -> drawn
