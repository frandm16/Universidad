/*******************************
* Prueba de la práctica
*/

#include <stdio.h>
#include <assert.h>
#include "practica.h"
#include "test_buffer.h"

#define NPRUEBAS 10000

void test_prueba(){
	Buffer buffer1;
	Buffer buffer2;
	int* p;
	bool ok;
	
	test_creaBuffer( &buffer1 );
	creaBuffer( buffer1.capacidad, &buffer2, &ok );

	assert( ok );
	
	p = buffer1.buffer;
	for( unsigned k = 0; k < buffer1.capacidad; k++ ){
		assert( obtieneValor( &buffer2, k, &ok ) == 0 );
		assert( ok );
		almacenaValor( &buffer2, k, *p, &ok );
		assert( ok );
		assert( obtieneValor( &buffer2, k, &ok ) == *p );
		assert( ok );
		p++;
	}
	
	almacenaValor( &buffer2, buffer1.capacidad, 0, &ok );
	assert( !ok );
	obtieneValor( &buffer2, buffer1.capacidad, &ok );
	assert( !ok );
		
	assert( test_comparaBuffer( &buffer1, &buffer2 ) );
	
	assert( test_comparaSuma( &buffer1, sumaValores( &buffer2 ) ) );
	
	test_liberaBuffer( &buffer1 );
	liberaBuffer( &buffer2 );
	assert( buffer2.buffer == NULL );
	assert( buffer2.capacidad == 0 );
}

int main() {
	for( unsigned k = 0; k < NPRUEBAS; k++ ){
		printf( "." );
		test_prueba();
	}

   printf("Todas las pruebas pasaron exitosamente.\n");
   return 0;
}

