CFLAGS += -Wall -Werror -Wextra
CFLAGS += -Wmissing-prototypes
CFLAGS += -Wstrict-prototypes
CFLAGS += -Wmissing-declarations
CFLAGS += -std=c99 -O3

all: soot

soot: soot.c state.h card.h card.c thing.h partial.h partial.c
	${CC} -o $@ card.c partial.c soot.c ${CFLAGS} ${LDFLAGS}

clean:
	rm -f soot *.o
