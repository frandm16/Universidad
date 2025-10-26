#!/bin/sh

clear
gcc -Wall -o test test.c practica.c test_buffer.o
valgrind ./test

