/**
* @brief Longitud máxima del nombre de una tarea (en caracteres).
*/
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#define MAX_NAME_LEN 20

/** Estructura que representa un paciente. */
typedef struct Paciente {
char nombre[MAX_NAME_LEN+1]; /**< Nombre del paciente y un espacio
extra para el terminador */
unsigned edad; /**< Edad del paciente */
}Paciente;

/** Nodo de la lista enlazada simple. */
typedef struct Nodo {
struct Paciente * persona; /**< Información del paciente */
struct Nodo* siguiente; /**< Puntero al siguiente nodo */
}Nodo;

/** Cola basada en lista enlazada simple. */
typedef struct Cola {
struct Nodo* primero; /**< Primer nodo (frente) de la cola */
struct Nodo* ultimo; /**< Último nodo (cola) de la cola */
unsigned tam; /**< Número de elementos actuales en la cola.
*/
}Cola;




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
unsigned longitudCola(const struct Cola* ptrCola){
    if(ptrCola==NULL){
        return 0;
    }
    return ptrCola->tam;
};

/**
* @brief Añade el paciente cuyos datos se proporcionan al final de la
cola.
* @param nombre Nombre de la persona a insertar en la cola, si la
longitud es superior a MAX_NAME_LEN, no se encola y se devuelve false.
* @param edad Edad de la persona a insertar en la cola.
* @return true si se ha podido encolar, false en otro caso, por ejemplo
cola sin inicializar o parámetros incorrectos.
*/
bool encolar(struct Cola* ptrCola, char * nombre, unsigned edad){
    if(ptrCola==NULL||nombre==NULL||strlen(nombre)>MAX_NAME_LEN){
        return false;
    }

    struct Paciente *nuevoPaciente=(struct Paciente *)malloc(sizeof(struct Paciente));
    if(nuevoPaciente==NULL){
        return false;
    }
    strcpy(nuevoPaciente->nombre,nombre);
    nuevoPaciente->edad=edad;

    struct Nodo *nuevoNodo=(struct Nodo *)malloc(sizeof(struct Nodo));
    if(nuevoNodo==NULL){
        free(nuevoPaciente);
        return false;
    }

    nuevoNodo->persona=nuevoPaciente;
    nuevoNodo->siguiente=NULL;

    if(ptrCola->tam==0){
        ptrCola->primero=nuevoNodo;
        ptrCola->ultimo=nuevoNodo;
    }else{
        ptrCola->ultimo->siguiente=nuevoNodo;
        ptrCola->ultimo=nuevoNodo;
    }
   


    
    ptrCola->tam++;
    return true;
};

/**
* @brief Recorre la cola mostrando todos los pacientes, uno por linea,
mostrando su nombre y edad y posición en la lista. En caso de no estar
inicializada, saca el mensaje, Cola no inicializada. En caso de estar
vacía, Cola vacía.
* @param ptrCola Dirección de memoria dónde está struct Cola. Debe valer
NULL si no ha sido inicializada, esto lo debe garantizar el que usa esta
librería.
*/
void mostrarCola(const struct Cola* ptrCola){
    if(ptrCola==NULL){
        printf("Cola no inicializada\n");
        return;
    }

    if(ptrCola->tam==0){
        printf("Cola vacia\n");
        return;
    }

    struct Nodo *actual=ptrCola->primero;
    unsigned index=1;
    while(actual!=NULL){
        printf("Orden %u: %s, %u\n",index,actual->persona->nombre,actual->persona->edad);
        actual=actual->siguiente;
        index++;
    }
    
};

/**
* @brief Elimina al primer paciente de la cola.
* @param ptrCola Dirección de memoria dónde está struct Cola. Debe valer
NULL si no ha sido inicializada, esto lo debe garantizar el que usa esta
librería.
* @return true si se ha podido desencolar, en otro caso false (por
ejemplo cola sin inicializar)
*/
bool desencolar(struct Cola* ptrCola){
    if(ptrCola==NULL){
        return false;
    }
    if(ptrCola->tam==0){
        return false;
    }  

    struct Nodo *aux=ptrCola->primero;

    ptrCola->primero=ptrCola->primero->siguiente;

    free(aux->persona);
    free(aux);
    ptrCola->tam--;

    if(ptrCola->tam==0){
        ptrCola->ultimo=NULL;
    }
    return true;

};

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
struct Paciente * primero(const struct Cola* ptrCola){
if(ptrCola==NULL){
    return NULL;
}
if(ptrCola->tam==0){
    return NULL;
}

struct Paciente *copiaPrimero=(struct Paciente*)malloc(sizeof(struct Paciente));
if(copiaPrimero==NULL){
    return NULL;
}
strncpy(copiaPrimero->nombre,ptrCola->primero->persona->nombre,MAX_NAME_LEN);
copiaPrimero->nombre[MAX_NAME_LEN]='\0';
copiaPrimero->edad=ptrCola->primero->persona->edad;
return copiaPrimero;
};

/**
* @brief Libera todos los nodos de la cola y la cola. En caso de no poder
liberar, no hace nada.
*
* @param ptrPtrCola Dirección de memoria dónde está la dirección en la
que está la struct Cola. *ptrPtrCola debe valer NULL si no ha sido
inicializada, esto lo debe garantizar el que usa esta librería.
*/
void liberarCola(struct Cola** ptrPtrCola){
    if(ptrPtrCola==NULL || *ptrPtrCola==NULL){
        return;
    }
    struct Cola *ptrCola=*ptrPtrCola;
    struct Nodo *actual=ptrCola->primero;
    struct Nodo *siguiente;

    while(actual!=NULL){
        siguiente=actual->siguiente;
        if(actual->persona !=NULL){
            free(actual->persona);
            actual->persona=NULL;
        }
        free(actual);
        actual=siguiente;
    }
    free(ptrCola);

};