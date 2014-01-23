#include <stdint.h>
#include "support.h"
#include "partial.h"

Partial_pool *partial_pool_new(void) {
  Partial_pool *p = (Partial_pool *)get_page(0);
  p->pool_end_address = (uintptr_t)p + get_page_size();
  return p;
}

Partial *partial_new(Partial_pool *pool, Applicee *f) {
  Partial *p = (Partial *)((uintptr_t)pool + 16);
  while (p->ref_count != 0) {
    p += sizeof(Partial);
    if ((uintptr_t)p >= pool->pool_end_address) {
      pool->pool_end_address =
        get_page_size() + get_page(pool->pool_end_address);
    }
  }
  p->ref_count = 1;
  p->f = f;
  return (Partial *)((intptr_t)p | Partial_function);
}

void partial_bind_value(Partial *p, int arg, intptr_t value) {
  p = (Partial *)((uintptr_t)p & -8);
  p->args[arg - 1] = (void *)((value << 3) | Value);
}

void partial_bind_partial(Partial *p, int arg, Partial *partial) {
  p = (Partial *)((uintptr_t)p & -8);
  p->args[arg - 1] = partial;
}

void *partial_apply(Partial *p, void *state) {
  // If the pointer is a function, there are no arguments:
  if (((uintptr_t)p & 7) == Function) {
    return ((Applicee *)p)(state, 0, 0, 0);
  }

  p = (Partial *)((uintptr_t)p & -8);

  // state may only be a Partial * or null:
  if (p->state) {
    state = partial_apply(p->state, state);
  }

  intptr_t arg0p = (intptr_t)p->args[0];
  void *arg0r = 0;
  if (arg0p != 0) {
    if ((arg0p & 7) == Value) {
      arg0r = (void *)(arg0p >> 3);
    } else {
      arg0r = partial_apply((Partial *)arg0p, state);
    }
  }

  intptr_t arg1p = (intptr_t)p->args[1];
  void *arg1r = 0;
  if (arg1p != 0) {
    if ((arg1p & 7) == Value) {
      arg1r = (void *)(arg1p >> 3);
    } else {
      arg1r = partial_apply((Partial *)arg1p, state);
    }
  }

  intptr_t arg2p = (intptr_t)p->args[2];
  void *arg2r = 0;
  if (arg2p != 0) {
    if ((arg2p & 7) == Value) {
      arg2r = (void *)(arg2p >> 3);
    } else {
      arg2r = partial_apply((Partial *)arg2p, state);
    }
  }

  return p->f(state, arg0r, arg1r, arg2r);
}

void partial_combine(Partial *p, Partial *c) {
  if (((uintptr_t)p & 7) == 0) {
    print_s("Tried to call partial_combine with a plain function.\n");
    A_DebugBreak();
    return;
  }
  p = (Partial *)((uintptr_t)p & -8);

  if (p->state) {
    partial_combine(c, p->state);
  }
  p->state = c;
}

void partial_inc_ref(Partial *p) {
  p = (Partial *)((uintptr_t)p & -8);
  p->ref_count++;
}

void partial_dec_ref(Partial *p) {
  p = (Partial *)((uintptr_t)p & -8);
  p->ref_count--;
}

uint64_t partial_test(uint64_t s, uint64_t arg0, uint64_t arg1, uint64_t arg2) {
  uint64_t result = 0;
  result += s;
  result *= arg0;
  result += arg1;
  result *= arg2;
  return result;
}
