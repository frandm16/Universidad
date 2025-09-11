
#include <iostream>
#include <array>
using namespace std;
const int MAX=10;


typedef array <int, MAX> TArray;
struct TLista{
TArray elementos;
int numElem;
};
void leerDatos(TLista& lista){
    int num;
    lista.numElem=0;
    cout<<"Introduzca una secuencia de elementos (0 para acabar): ";
    cin>>num;
    while(num!=0){

        if(lista.numElem<MAX){
            lista.numeros[lista.numElem]=num;
            lista.numElem++;
        }
        cin>>num;
    }
}
void borrar(TLista& lista, int num){
int pos=buscar(lista,num){
if(pos<lista.numElem){
    for(int cont=pos+1;cont<lista.numElem;cont++){
        lista.numeros[cont-1]=lista.numeros[cont];
    }
    lista.numElem--;
}

}

}

int main(){
Tlista lista;
int valor;
leerDatos(lista);
cout<<"Introduzca el valor a borrar: ";
cin>>valor;
borrar(lista, borrar);
return 0;
}
