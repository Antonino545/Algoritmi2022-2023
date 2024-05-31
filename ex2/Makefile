CC=gcc
CFLAGS = -g -Wall -Wextra -Wpedantic -Wconversion

all: build/main_ex2

build/main_ex2: build/find_errors.o build/libskiplist.a | build
	$(CC) $(CFLAGS) -o build/main_ex2 build/find_errors.o -Lbuild -lskiplist
lib:build/lib
build/test: build
	$(CC) $(CFLAGS) src/test.c src/skiplist.c src/unity/src/unity.c -o build/Test
build/find_errors.o: src/find_errors.c src/skiplist.h | build
	$(CC) $(CFLAGS) -c src/find_errors.c -o build/find_errors.o -I../
build/libskiplist.a: build/skiplist.o | build
	ar rcs build/libskiplist.a build/skiplist.o

build/skiplist.o: src/skiplist.c src/skiplist.h | build
	$(CC) $(CFLAGS) -c src/skiplist.c -o build/skiplist.o -I../
build:
	mkdir -p build
test: build/test
	  ./build/Test
clean:
	rm -rf build

.PHONY: run