/**
* @brief Longitud máxima del nombre de una tarea (en caracteres).
*/
#include <stdio.h>
#define MAX_NAME_LEN 20

/** Estructura que representa un paciente. */
struct Paciente {
char nombre[MAX_NAME_LEN+1]; /**< Nombre del paciente y un espacio
extra para el terminador */
unsigned edad; /**< Edad del paciente */
};

/** Nodo de la lista enlazada simple. */
struct Nodo {
struct Paciente * persona; /**< Información del paciente */
struct Nodo* siguiente; /**< Puntero al siguiente nodo */
};

/** Cola basada en lista enlazada simple. */
struct Cola {
struct Nodo* primero; /**< Primer nodo (frente) de la cola */
struct Nodo* ultimo; /**< Último nodo (cola) de la cola */
unsigned tam; /**< Número de elementos actuales en la cola.
*/
};




/**
* @brief Inicializa la cola dejándola vacía. Una cola vacía tendrá tam 0,
y primero y último a NULL.
*
* @return Dirección de memoria de la cola creada. NULL si no se puede
crear.
*/
struct Cola * inicializarCola(void){
    struct Cola *ptrCola=(struct Cola*)malloc(sizeof(struct Cola));
    if(ptrCola!=NULL){
        ptrCola->primero=NULL;
        ptrCola->ultimo=NULL;
        ptrCola->tam=0;
    }

return ptrCola;
};

/**
* @brief Devuelve el tamaño de la cola, 0 si está sin inicializar.
*
* @param ptrCola Dirección de memoria dónde está struct Cola. Debe valer
NULL si no ha sido inicializada, esto lo debe garantizar el que usa esta
librería.
*/
unsigned longitudCola(const struct Cola* ptrCola);

/**
* @brief Añade el paciente cuyos datos se proporcionan al final de la
cola.
* @param nombre Nombre de la persona a insertar en la cola, si la
longitud es superior a MAX_NAME_LEN, no se encola y se devuelve false.
* @param edad Edad de la persona a insertar en la cola.
* @return true si se ha podido encolar, false en otro caso, por ejemplo
cola sin inicializar o parámetros incorrectos.
*/
bool encolar(struct Cola* ptrCola, char * nombre, unsigned edad);

/**
* @brief RRecorre la cola mostrando todos los pacientes, uno por linea,
mostrando su nombre y edad y posición en la lista. En caso de no estar
inicializada, saca el mensaje, Cola no inicializada. En caso de estar
vacía, Cola vacía.
* @param ptrCola Dirección de memoria dónde está struct Cola. Debe valer
NULL si no ha sido inicializada, esto lo debe garantizar el que usa esta
librería.
*/
void mostrarCola(const struct Cola* ptrCola);

/**
* @brief Elimina al primer paciente de la cola.
* @param ptrCola Dirección de memoria dónde está struct Cola. Debe valer
NULL si no ha sido inicializada, esto lo debe garantizar el que usa esta
librería.
* @return true si se ha podido desencolar, en otro caso false (por
ejemplo cola sin inicializar)
*/
bool desencolar(struct Cola* ptrCola);

/**
* @brief Devuelve una COPIA del primer paciente. El que invoca la función
es responsable de liberar esa memoria devuelta.
* @param ptrCola Dirección de memoria dónde está struct Cola. Debe valer
NULL si no ha sido inicializada, esto lo debe garantizar el que usa esta
librería.
* @return Dirección de memoria que contiene el struct Paciente con los
datos de primer paciente. Devuelve NULL cuando no se puede devolver el
primer paciente (por ejemplo cola no inicializada, sin elementos, o no se
puede reservar memoria para la copia).
*/
struct Paciente * primero(const struct Cola* ptrCola);

/**
* @brief Libera todos los nodos de la cola y la cola. En caso de no poder
liberar, no hace nada.
*
* @param ptrPtrCola Dirección de memoria dónde está la dirección en la
que está la struct Cola. *ptrPtrCola debe valer NULL si no ha sido
inicializada, esto lo debe garantizar el que usa esta librería.
*/
void liberarCola(struct Cola** ptrPtrCola);