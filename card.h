#ifndef _card_h
#define _card_h

#include <stdint.h>
#include "partial.h"

#define nil (void *)0

typedef enum {
  Legendary,
  Epic,
  Rare,
  Common,
  Free
} Card_quality;

typedef enum {
  None,
  Debug,
  Missions,
  Reward,
  Basic,
  Expert
} Card_set;

typedef enum {
  Neutral,
  Druid,
  Hunter,
  Mage,
  Paladin,
  Priest,
  Rogue,
  Shaman,
  Warlock,
  Warrior
} Card_class;

typedef enum {
  Hero,
  Minion,
  Weapon,
  Spell,
  Secret,
  Hero_power
} Card_type;

typedef enum {
  General,
  Beast,
  Demon,
  Dragon,
  Murloc,
  Pirate,
  Totem
} Card_minion_type;

typedef struct Card {
  const char *name;
  uint16_t id;
  uint8_t quality;
  uint8_t set;
  uint8_t class;
  uint8_t type;
  uint8_t minion_type;
  uint8_t cost;
  uint8_t attack;
  uint8_t health;
  void *on_attacked;
  void *on_damaged;
  void *on_healed;
  void *on_played;
  void *on_summoned;
  void *on_destroyed;
  void *on_turn_start;
  void *on_turn_end;
} Card;

Card cards[524];

#endif // _card_h
