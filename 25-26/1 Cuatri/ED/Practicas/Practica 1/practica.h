/*******************************
* Práctica
* Realizar una ejercicio que utilice una estructura de datos consistente en un registro
* que contiene un buffer de números enteros y su capacidad
*/

#ifndef PRACTICA_H
#define PRACTICA_H

#include <stdbool.h>

typedef struct{
	unsigned capacidad;
	int* buffer;
} Buffer;


/*
* Crea un buffer de números enteros con la capacidad recibida como parámetro y lo inicializa al valor 0
* Parámetros:
*   capacidad : número de enteros que caben en el buffer
*   buffer :    estructura de datos con el buffer de memoria y su capacidad
*   ok :        devuelve verdadero si la reserva de memoria fue correcta
*/
void creaBuffer( unsigned capacidad, Buffer* buffer, bool* ok );

/*
* Almacena un valor en el buffer recibido como parámetro en la posición pos
* Parámetros:
*   buffer :    buffer donde almacenar el valor
*   pos :       posición donde almacenar el valor en el buffer. Si no es válida el subalgoritmo
*               no almacena nada
*   valor :     valor que se almacena en el buffer
*   ok :        devuelve verdadero si la posición es válida. Falso en caso contrario
*/
void almacenaValor( Buffer* buffer, unsigned pos, int valor, bool* ok );

/*
* Obtiene un valor en una posición del buffer
* Parámetros:
*   buffer : buffer de memoria
*   pos :    posición que se quiere leer del buffer
*   ok  :    devuelve verdadero si la posición es válida. Falso en caso contrario
* Devuelve:
*   Valor almacenado en el buffer en pos o 0 si la posición no es válida
*/
int obtieneValor( Buffer* buffer, unsigned pos, bool* ok );

/*
* Suma todos los valores almacenados en un buffer de números enteros
* Parámetros:
*   buffer :    buffer de memoria de números enteros
* Devuelv:
*   La suma de todos los elementos almacenados en el buffer
*/
int sumaValores( Buffer* buffer );

/* 
* Libera la memoria asignada a un buffer
* Debe dejar el puntero del buffer a NULL y su capacidad a 0
*/
void liberaBuffer( Buffer* buffer );

#endif


