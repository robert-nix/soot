CFLAGS += -Wall -Werror -Wextra
CFLAGS += -Wmissing-prototypes
CFLAGS += -Wstrict-prototypes
CFLAGS += -Wmissing-declarations
CFLAGS += -Wl,-e_main -lkernel32 -ladvapi32 -static
CFLAGS += -Llib -Iinc
# agner's asmlib
CFLAGS += -llibacof64o
CFLAGS += -std=c99 -O3 -flto -m64 -march=corei7 -nostdlib #yolo

all: soot

soot: soot.c state.h card.h card.c thing.h partial.h partial.c support.h support.c
	${CC} -o $@ card.c partial.c support.c soot.c ${CFLAGS} ${LDFLAGS}

clean:
	rm -f soot *.o
