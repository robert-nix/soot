#ifndef _state_h
#define _state_h

#include <stdint.h>
#include "thing.h"

typedef struct State {
  uint8_t actor;
  uint8_t flags[7];
  uint16_t things_len;
  uint16_t last_summoned;
  uint16_t target;
  uint16_t targeter;
  uint16_t *target_stack;
  uint16_t *targeter_stack;
  uint16_t *observer_stack;
  uint16_t observer;
  uint8_t cards_played_this_turn;
  uint8_t spells_cost_this_turn;
  uint8_t spells_cost_next_turn;
} State;

#endif // _state_h
