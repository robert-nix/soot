// idea:  give_card(s, count(filter_all(s,...))) partially applied without the s
// args. we refer to this function with a pointer to the data structure and 3
// bits saying that the pointer is a partial, not an actual function

typedef void *(Applicee)(void *, void *, void *, void *);

typedef enum {
  Function,
  Partial_function,
  Value_pointer,
  Value
} Pointer_type;

typedef struct {
  Applicee *f;
  void *state;
  void *args[3];
} Partial;

Partial *new_partial(Applicee *f, void *, void *, void *);

void *apply_partial(Partial *p, void *state);

// compose_partial(p, c) := p(s, ...) => p(c(s, ...), ...)
// also := p(c1(s, ...), ...) => p(c(c1(s, ...), ...), ...)
void compose_partial(Partial *p, Partial *c);

void *partial_test(void *, void *, void *, void *);
