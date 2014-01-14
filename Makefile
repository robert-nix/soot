CFLAGS += -Wall -Werror -Wextra
CFLAGS += -Wmissing-prototypes
CFLAGS += -Wstrict-prototypes
CFLAGS += -Wmissing-declarations
CFLAGS += -std=c99 -O3

all: soot

soot: soot.c state.h thing.h
	${CC} -o $@ soot.c ${CFLAGS} ${LDFLAGS}

clean:
	rm -f soot *.o
