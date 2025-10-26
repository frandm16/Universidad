/*******************************
* Test de la práctica
*/

#ifndef TEST_BUFFER_H
#define TEST_BUFFER_H

#include "practica.h"
#include <stdbool.h>

void test_creaBuffer( Buffer* buffer );

bool test_comparaBuffer( Buffer* buffer1, Buffer* buffer2 );

bool test_comparaSuma( Buffer* buffer, int suma );

void test_liberaBuffer( Buffer* buffer );

#endif

