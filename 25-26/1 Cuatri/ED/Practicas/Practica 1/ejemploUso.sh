#!/bin/sh

clear
gcc -Wall -o ejemploUso ejemploUso.c practica.c
valgrind ./ejemploUso
