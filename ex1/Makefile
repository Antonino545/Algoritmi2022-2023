CC=gcc
CFLAGS = -g -Wall -Wextra -Wpedantic -Wconversion

all: build/main_ex1

build/main_ex1: build/client.o build/libsort.a | build
	$(CC) $(CFLAGS) -o build/main_ex1 build/client.o -Lbuild -lsort
build/test: build
	$(CC) $(CFLAGS) src/test.c src/merge_binary_insertion_sort.c src/unity/src/unity.c -o build/Test
build/client.o: src/client.c src/merge_binary_insertion_sort.h | build
	$(CC) $(CFLAGS) -c src/client.c -o build/client.o -I../

build/libsort.a: build/merge_binary_insertion_sort.o | build
	ar rcs build/libsort.a build/merge_binary_insertion_sort.o

build/merge_binary_insertion_sort.o: src/merge_binary_insertion_sort.c src/merge_binary_insertion_sort.h | build
	$(CC) $(CFLAGS) -c src/merge_binary_insertion_sort.c -o build/merge_binary_insertion_sort.o -I../
build:
	mkdir -p build
test: build/test
	./build/Test
clean:
	rm -rf build

.PHONY: run