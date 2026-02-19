//Clase 24/10/2025
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


/** Nodo de la lista enlazada simple. */
typedef struct Nodo {
int dato; /** Información */
struct Nodo* sig; /** Puntero al siguiente nodo */
}Nodo;

/** Cola basada en lista enlazada simple. */
typedef struct Lista {
struct Nodo* pri; /** Primer nodo (frente) de la cola */
}Lista;

typedef Nodo* pNodo;

void eliminarDuplicados1(Lista *l){
    pNodo rec,ant,act;
    rec=l->pri;
    while(rec!=NULL){
        ant=l->pri;         //Problema
        act=ant->sig;       //Problema
        while(act!=NULL){
            if(act->dato==rec->dato){
                ant->sig=act->sig;
                free(act);
                act=ant->sig; //ya que si hacemos free, se nos queda act colgado y no podemos ejecutar las lineas 36,37
            }else{
            ant=act;
            act=act->sig;
            }
        }
    }
}

void eliminarDuplicadosMio(Lista *l){
    pNodo primero,anterior,actual;
    primero=l->pri;
    if(primero==NULL){
        return;
    }
    anterior=primero;
    actual=primero->sig;
    while(actual!=NULL){
       // ant=l->pri;         //Problema
       // act=ant->sig;       //Problema
       
            if(actual->dato==primero->dato){
                anterior->sig=actual->sig;
                free(actual);
                actual=anterior->sig; //ya que si hacemos free, se nos queda act colgado y no podemos ejecutar las lineas 36,37
            }else{
            anterior=actual;
            actual=actual->sig;
            }
    }
    
}

void eliminarValores(pNodo* l, int v){
    pNodo ant,act;
    ant=NULL;
    act=*l;
    while(act!=NULL){
        if(act->dato==v){
            if(ant==NULL){
                *l=act->sig;
                free(act);
                act=*l;
            }else{
                ant->sig=act->sig;
                free(act);
                act=ant->sig;
            }
        }else{
            ant=act;
            act=act->sig;
        }
    }
}

void eliminarDuplicados2(Lista *l){
    pNodo rec, aux;
    rec=l->pri;
    while(rec!=NULL){
        aux=rec->sig;                       //Problema
        eliminarValores(&aux,rec->dato);    //Problema
        rec=rec->sig;
    }
}

void eliminarDuplicados3(Lista *l){
    unsigned tam,k,n;
    tam=longitud(l->pri);
    k=0;
    while(k<tam){
        n=k+1;
        while(n<tam){
            if(obtenerEnPosicion(l->pri,k)==obtenerEnPosicion(l->pri,n)){
                tam--;
                eliminarEnPosicion(&(l->pri),n);
            }else{
                n++;
            }
        }
        k++;
    }
            
}