#include <iostream>
#include <array>
using namespace std;
const int MAX=10;
typedef array <int,MAX> TArray;
struct TLista{
    int tam;
    TArray arr;
};

void leerDatos(TLista& lista){
    do{
        cout<<"Introduzca la cantidad de elementos de la lista (MAXIMO "<<MAX<<"): ";
        cin>>lista.tam;
    }while(lista.tam<0 || lista.tam>MAX);
    cout<<"Introduzca los "<<lista.tam<<" elementos: ";
    for(int i=0; i<lista.tam; i++){
        cin>>lista.arr[i];
    }
}
bool esPrimo(int num){
    int x=2;
    while(x<num && num%x !=0){
        x++;
    }

    return (num !=1 && x>=num);
}
int posicionPrimerPrimo(const TLista& lista){
    int i=0, posicion;
    while(i<lista.tam && !esPrimo(lista.arr[i])){
        i++;
    }
    if(i>=lista.tam){
        posicion= -1;
    }else{
    posicion= i;
    }

    return posicion;
}

void eliminarPosicion(TLista& lista,int pos){
    if(pos<lista.tam){
        for(int i=pos; i<lista.tam;i++){
            lista.arr[i]=lista.arr[i+1];
        }
        lista.tam--;
    }else{
    cout<<"ERROR en eliminaPosicion(), el array no es suficientemente grande para alcanzar pos: "<<pos<<endl;
    }
}

void pintaLista(const TLista& lista){
    for(int i=0;i<lista.tam;i++){
        cout<<lista.arr[i]<<" ";
    }
    cout<<endl;
}




int main(){

    TLista lista;
    lista.tam=0;
    leerDatos(lista);
    if(posicionPrimerPrimo(lista)==-1){
        cout<<"No hay primos en la lista."<<endl;
    }else{
    cout<<"El primer primo es "<<lista.arr[posicionPrimerPrimo(lista)]<<" y esta en la posicion "<<posicionPrimerPrimo(lista)<<endl;
    eliminarPosicion(lista, posicionPrimerPrimo(lista));
    cout<<"La lista tras la eliminacion es: ";
    pintaLista(lista);
    }


    return 0;
}
