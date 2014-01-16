#include <stdlib.h>
#include "partial.h"

Partial *new_partial(Applicee *f, void *arg1, void *arg2, void *arg3) {
  Partial *p = (Partial *)malloc(sizeof(Partial));
  p->f = f;
  p->state = 0;
  p->args[0] = arg1;
  p->args[1] = arg2;
  p->args[2] = arg3;
  return (Partial *)((intptr_t)p | 1);
}

void *apply_partial(Partial *p, void *s) {
  if (((intptr_t)p & 7) == 0) {
    return ((Applicee *)p)(s, 0, 0, 0);
  }
  p = (Partial *)((intptr_t)p & ~7);
  if (p->state) {
    s = apply_partial(p->state, s);
  }
  return p->f(s, p->args[0], p->args[1], p->args[2]);
}

void compose_partial(Partial *p, Partial *c) {
  if (p->state) {
    compose_partial(c, p->state);
  }
  p->state = c;
}

void *partial_test(void *s, void *arg0, void *arg1, void *arg2) {
  intptr_t *result = malloc(sizeof(intptr_t) * 4);
  result[0] = (intptr_t)s;
  result[1] = (intptr_t)arg0;
  result[2] = (intptr_t)arg1;
  result[3] = (intptr_t)arg2;
  return result;
}
