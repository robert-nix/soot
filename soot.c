#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "state.h"

int main(int argc, char **argv) {
  printf("meow\n");
  printf("%d %s\n", argc, *argv);
  printf("sizeof(Thing) = %d, sizeof(State) = %d\n",
    sizeof(Thing),
    sizeof(State));
  Thing t;
  set_owner(&t, 1);
  printf("owner: %d\n", get_owner(&t));
  set_state(&t, 3);
  printf("state: %d\n", get_state(&t));
  set_kirin_tor_mage(&t, 1);
  printf("kirin_tor_mage: %d\n", get_kirin_tor_mage(&t));
  return 0;
}
