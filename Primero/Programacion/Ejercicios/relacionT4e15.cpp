#include <iostream>
#include <array>
using namespace std;
const int MAX=10;


typedef array <int, MAX> TArray;
struct TLista{
TArray elementos;
int numElem;
};

void leerDatos(TLista& lista, int& repet){
    do{
        cout<<"Cuantos numeros deseas introducir (maximo "<<MAX<<"): ";
        cin>>lista.numElem;
    }while(lista.numElem<=0 || lista.numElem>MAX);
    cout<<"Introduzca "<<lista.numElem<<" numeros: ";


    for(int i=0;i<lista.numElem; i++){
        cin>>lista.elementos[i];

    }
    cout<<"Introduzca el numero de repeticiones para realizar la criba: ";
    cin>>repet;
}

int repeticiones(const TLista& lista, int elem){
    int res=0;
    for(int i=0;i<lista.numElem;i++){
        if(lista.elementos[i]==elem){
            res++;
        }
    }
return res;
}
bool esta(const TLista& lista, int elem){
bool encontrado=false;
int i=0;
while((i<lista.numElem)&&(!encontrado)){
    if(lista.elementos[i]==elem){
        encontrado=true;
    }
    i++;
}
return encontrado;
}

void criba(const TLista& lista1, TLista& lista2, int x){
    lista2.numElem=0;
    for(int i=0; i<lista1.numElem; i++){
        if(repeticiones(lista1, lista1.elementos[i])==x && !esta(lista2, lista1.elementos[i])){
            lista2.elementos[lista2.numElem]=lista1.elementos[i];
            lista2.numElem++;
        }
    }

}

void mostrar(const TLista& lista){
    cout<<"La lista cribada es: ";
    for(int i=0;i<lista.numElem;i++){
        cout<<lista.elementos[i]<<" ";
    }
    cout<<endl;
}



int main(){
TLista lista1, lista2;
int repet;

leerDatos(lista1,repet);
criba(lista1,lista2, repet);
mostrar(lista2);

return 0;
}
