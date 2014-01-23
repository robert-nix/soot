#ifndef _support_h
#define _support_h

#include <stdint.h>
#include <asmlib.h>

// console output
void print(char *, size_t);
void print_s(char *);
void print_p(intptr_t);
void print_n(uint64_t);

// memory allocation
void init_large_pages(void);
uintptr_t get_page(uintptr_t );
size_t get_page_size(void);
void free_page(void *);

#endif // _support_h
