#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "state.h"
#include "card.h"

int main(int argc, char **argv) {
  printf("meow\n");
  printf("%d %s\n", argc, *argv);
  printf("sizeof(Thing)=%d, sizeof(State)=%d, sizeof(Card)=%d\n",
    sizeof(Thing), sizeof(State), sizeof(Card));
  Thing t;
  set_owner(&t, 1);
  printf("owner: %d\n", get_owner(&t));
  set_state(&t, 3);
  printf("state: %d\n", get_state(&t));
  set_kirin_tor_mage(&t, 1);
  printf("kirin_tor_mage: %d\n", get_kirin_tor_mage(&t));
  printf("chicken=%s\n", cards[0].name);
  Partial *p = new_partial(partial_test, (void *)1, (void *)2, (void *)3);
  intptr_t *stuff = (intptr_t *)apply_partial(p, (void *)0);
  printf("pointer=%p\n", p);
  printf("stuff=[%d, %d, %d, %d]\n", stuff[0], stuff[1], stuff[2], stuff[3]);
  return 0;
}
