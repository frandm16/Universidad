/*******************************
* Ejemplo de uso de la práctica
* Este programa lee una cantidad de números de teclado, los muestra por pantalla y muestra su suma.
*/

#include <stdio.h>
#include <stdlib.h>
#include "practica.h"


void leerBuffer( Buffer* buffer ){
	int v;
	bool ok;
	unsigned c;
	
	printf( "Cuántos números enteros vas a teclear?" );
	scanf( "%d", &c );
	
	creaBuffer( c, buffer, &ok );
	if (!ok){
		printf( "Error al crear buffer\n" );
		exit( 1 );
	}

	printf( "Teclea %u números enteros\n", c );
	
	for( unsigned k = 0; k < c; k++ ){
		scanf( "%d", &v );
		almacenaValor( buffer, k, v, &ok );
		if (!ok){
			printf( "Error al almacenar en buffer\n" );
			exit( 1 );
		}
	}
}

void imprimirBuffer( Buffer* buffer ){
	bool ok;
	
	printf( "Números en buffer:\n" );
	for( unsigned k = 0; k < buffer->capacidad; k++ ){
		printf( "%d ", obtieneValor( buffer, k, &ok ) );
		if (!ok){
			printf( "Error al obtener valor de buffer\n" );
			exit( 1 );
		}
	}
	printf( "\n" );
}


int main(){
	Buffer buffer;
	
	leerBuffer( &buffer );

	printf( "Los números que has tecleado son:\n" );
	imprimirBuffer( &buffer);
	
	printf( "La suma de los números tecleados es: %d\n", sumaValores( &buffer ) );
		
	liberaBuffer( &buffer );
	return 0;
}


