#include "support.h"
#include "state.h"
#include "card.h"
#include "partial.h"

#include <Windows.h>
#include <asmlibran.h>

void _main(void);
void _main(void) {
  init_large_pages();

  Partial_pool *pool = partial_pool_new();
  Partial *p = partial_new(pool, (Applicee *)partial_test);
  partial_bind_value(p, 1, 2);   // * 2
  partial_bind_value(p, 2, -15); // + -15
  partial_bind_value(p, 3, 4);   // * 4
  print_s("pointer="); print_p((intptr_t)p); print_s("\n");
  uint64_t stuff = (uint64_t)partial_apply(p, (void *)10);
  print_s("stuff="); print_p(stuff); print_s("\n");

  print_s("sizeof(State)="); print_p(sizeof(State)); print_s(", ");
  print_s("sizeof(Thing)="); print_p(sizeof(Thing)); print_s(", ");
  print_s("sizeof(Card)="); print_p(sizeof(Card)); print_s(", ");
  print_s("sizeof(Partial)="); print_p(sizeof(Partial)); print_s("\n");
  print_s("page_size="); print_p(get_page_size()); print_s("\n");
  SFMTgenRandomInit(0x752, 0);
  print_p((intptr_t)SFMTgenIRandom(0, 0x100000)); print_s("\n");
  print_p((intptr_t)SFMTgenIRandom(0, 0x100000)); print_s("\n");
  print_p((intptr_t)SFMTgenIRandom(0, 0x100000)); print_s("\n");
  print_p((intptr_t)SFMTgenIRandom(0, 0x100000)); print_s("\n");
  print_p((intptr_t)SFMTgenIRandom(0, 0x100000)); print_s("\n");
  print_p((intptr_t)SFMTgenIRandom(0, 0x100000)); print_s("\n");
  print_p((intptr_t)SFMTgenIRandom(0, 0x100000)); print_s("\n");
  ExitProcess(0);
}
