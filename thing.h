#include <stdint.h>

typedef struct Thing {
  uint16_t id;
  uint8_t attack;
  uint8_t health;
  uint16_t left;
  uint16_t right;
  uint32_t flags1; /*
    owner : 1
    state : 3 (undrawn, drawn, mulliganed, played, discarded)
    card_type : 3 (hero, minion, weapon, spell, secret)
    minion_type : 3 (general, beast, demon, dragon, murloc, pirate, totem)
    times_attacked : 2
    mana : 4
    mana_crystals : 4
    times_power_used : 1
    use_cost : 5
    armor : 6 */
  uint32_t flags2; /*
    taunt : 1
    stealth : 1
    conceal : 1
    summoned : 1
    exhaustion : 1
    silenced : 1
    immune : 1
    spell_immunity : 1
    spell_damage : 4
    divine_shield : 1
    freeze : 1
    charge : 1
    windfury : 1
    preparation : 1
    ancestral_spirit : 1
    summoning_portals : 3
    headcracks : 3
    auchenai_soulpriest : 1
    counterspell : 1
    kirin_tor_mage : 1
    (unused) : 5 */
  int8_t spells_cost;
  int8_t minions_cost;
  uint8_t attack_this_turn;
  uint8_t max_health;
  // 8*8 = 64 bytes
  void *on_attacked;
  void *on_damaged;
  void *on_healed;
  void *on_played;
  void *on_summoned;
  void *on_destroyed;
  void *on_turn_start;
  void *on_turn_end;
} Thing;

/* unsafe bitfields macro - name, type, field, size, offset */
#define _bitfield(n,t,f,s,o) \
  static inline uint8_t get_##n(t *); \
  static inline uint8_t get_##n(t *thing) { \
    return (uint8_t)((thing->f & (((1 << s) - 1) << o)) >> o); \
  } \
  static inline void set_##n(t *, uint8_t); \
  static inline void set_##n(t *thing, uint8_t value) { \
    thing->f = (thing->f & ~(((1 << s) - 1) << o)) | (((uint32_t)value) << o); \
  }

/* flags1 bitfields */
_bitfield(owner, Thing, flags1, 1, 0);
_bitfield(state, Thing, flags1, 3, 1);
_bitfield(card_type, Thing, flags1, 3, 4);
_bitfield(minion_type, Thing, flags1, 3, 7);
_bitfield(times_attacked, Thing, flags1, 2, 10);
_bitfield(mana, Thing, flags1, 4, 12);
_bitfield(mana_crystals, Thing, flags1, 4, 16);
_bitfield(times_power_used, Thing, flags1, 1, 20);
_bitfield(use_cost, Thing, flags1, 5, 21);
_bitfield(armor, Thing, flags1, 6, 26);

/* flags2 bitfields */
_bitfield(taunt, Thing, flags2, 1, 0);
_bitfield(stealth, Thing, flags2, 1, 1);
_bitfield(conceal, Thing, flags2, 1, 2);
_bitfield(summoned, Thing, flags2, 1, 3);
_bitfield(exhaustion, Thing, flags2, 1, 4);
_bitfield(silenced, Thing, flags2, 1, 5);
_bitfield(immune, Thing, flags2, 1, 6);
_bitfield(spell_immunity, Thing, flags2, 1, 7);
_bitfield(spell_damage, Thing, flags2, 4, 8);
_bitfield(divine_shield, Thing, flags2, 1, 12);
_bitfield(freeze, Thing, flags2, 1, 13);
_bitfield(charge, Thing, flags2, 1, 14);
_bitfield(windfury, Thing, flags2, 1, 15);
_bitfield(preparation, Thing, flags2, 1, 16);
_bitfield(ancestral_spirit, Thing, flags2, 1, 17);
_bitfield(summoning_portals, Thing, flags2, 3, 18);
_bitfield(headcracks, Thing, flags2, 3, 21);
_bitfield(auchenai_soulpriest, Thing, flags2, 1, 24);
_bitfield(counterspell, Thing, flags2, 1, 25);
_bitfield(kirin_tor_mage, Thing, flags2, 1, 26);
