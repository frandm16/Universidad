#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "cola.h"

struct Cola * inicializarCola(void){
    struct Cola * new_cola = malloc(sizeof(struct Cola));

    if(new_cola != NULL){
        new_cola -> primero = NULL;
        new_cola -> ultimo = NULL;
        new_cola -> tam = 0;
    }

    return new_cola;
}

unsigned longitudCola(const struct Cola * ptrCola){
    int longitud = 0;
    if(ptrCola != NULL){
        longitud = ptrCola -> tam;
    }

    return longitud;
}

bool encolar(struct Cola * ptrCola, char * nombre, unsigned edad){
    bool ok = false;

    if(ptrCola != NULL && strlen(nombre) <= MAX_NAME_LEN){

        struct Paciente * new_paciente = malloc(sizeof(struct Paciente));

        if(new_paciente != NULL){
            strcpy(new_paciente -> nombre, nombre);
            new_paciente -> edad = edad;

            struct Nodo * new_node = malloc(sizeof(struct Nodo));
            if(new_node != NULL){
                new_node -> persona = new_paciente;
                new_node -> siguiente = NULL;
                
                if(ptrCola -> tam > 0){
                    ptrCola -> ultimo -> siguiente = new_node;
                    
                } else{
                    ptrCola -> primero = new_node;
            
                }
                ptrCola -> ultimo = new_node;
                ptrCola -> tam += 1;
                ok = true;
            } else {
                free(new_paciente);
                new_paciente = NULL;
            }
        }

    }

    return ok;
}

bool desencolar(struct Cola * ptrCola){
    bool ok = false;

    if(ptrCola != NULL && ptrCola -> tam != 0){

        struct Nodo * primer_nodo = ptrCola -> primero;

        if(ptrCola -> tam == 1){
            ptrCola -> primero = NULL;
            ptrCola -> ultimo = NULL;

        }else {
            ptrCola -> primero = primer_nodo -> siguiente;
            
        } 

        if (primer_nodo -> persona != NULL) {
            free(primer_nodo -> persona);
        }
        free(primer_nodo);
        primer_nodo = NULL;
        ptrCola -> tam -= 1;
        ok = true;
    }

    return ok;
}

struct Paciente * primero(const struct Cola * ptrCola){
    struct Paciente * primer_paciente = NULL;

    if (ptrCola != NULL && ptrCola->primero != NULL) {
        primer_paciente = ptrCola->primero->persona;
    }

    return primer_paciente;
}

void mostrarCola(const struct Cola * ptrCola){
    if(ptrCola != NULL){
        if(ptrCola ->primero != NULL){
            struct Nodo * indice = ptrCola ->primero;
            unsigned pos = 1;
            while (indice != NULL){
                printf("Nombre: %s, Edad: %u, Pos: %u \n", indice ->persona ->nombre, indice->persona->edad, pos); 
                indice = indice -> siguiente;
                pos++;
            }
        }else{
            printf("Cola vacía. \n");
        }
        
    }else{
        printf("Cola no inicializada. \n");
    }
    
}

void liberarCola(struct Cola ** ptrPtrCola){
    if(ptrPtrCola !=NULL && *ptrPtrCola != NULL){
        struct Cola * cola = ptrPtrCola;
        struct Nodo * actual = cola -> primero;

        while (actual != NULL){
            struct Nodo * aux = actual;
            actual = actual -> siguiente;

            if(aux->persona != NULL){
                free(aux->persona);
            }
            free(aux);
            aux=NULL;
        }
        free(cola);
        *ptrPtrCola = NULL;
        
    }
}
