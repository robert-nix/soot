#ifndef _partial_h
#define _partial_h

// idea:  give_card(s, count(filter_all(s,...))) partially applied without the s
// args. we refer to this function with a pointer to the data structure and 3
// bits saying that the pointer is a partial, not an actual function

typedef void *(Applicee)(void *, void *, void *, void *);

typedef enum {
  Function,
  Partial_function,
  Value
} Pointer_type; // 3 bits

typedef struct {
  uint64_t ref_count;
  Applicee *f;
  void *state;
  void *args[3];
} Partial; // 48 bytes

typedef struct {
  uintptr_t pool_end_address;
} Partial_pool;

Partial_pool *partial_pool_new(void);

// Although Partials are ideologically immutable, their creation is described
// using multiple mutating functions to better describe the Partial itself.
// ---
// Creates a new partial within the provided pool.  The returned pointer has
// lower bits set to denote it as a partial.  f should be a plain function
// pointer to a function of exactly 4 arguments.
Partial *partial_new(Partial_pool *pool, Applicee *f);

// Binds a value in the provided partial at the provided argument index.  When
// called, the partial will supply the underlying function with this argument.
void partial_bind_value(Partial *p, int arg, intptr_t value);

// Binds a partial in the provided partial at the provided argument index. When
// called, the partial will supply the underlying function's argument at this
// index with the result of applying this partial.
void partial_bind_partial(Partial *p, int arg, Partial *partial);

// Applies the partial with the given state pointer.  It's worth noting, in the
// simulation, state is totally mutable (state is copied at branching points)
// and the return value doesn't matter, but it wouldn't feel right without it.
void *partial_apply(Partial *p, void *state);

// Given the current partial p and another partial c, combine_partial makes it
// so that when p is applied, c will first be applied to find the resulting
// state which will then be provided to p.
//   e.g.:
// combine_partial(p, c) := p(s, ...) => p(c(s, ...), ...)
// also := p(c1(s, ...), ...) => p(c(c1(s, ...), ...), ...)
void partial_combine(Partial *p, Partial *c);

// Reference counting
void partial_inc_ref(Partial *p);
void partial_dec_ref(Partial *p);

uint64_t partial_test(uint64_t, uint64_t, uint64_t, uint64_t);

#endif // _partial_h
