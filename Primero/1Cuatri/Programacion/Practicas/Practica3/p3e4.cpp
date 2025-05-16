//Francisco Dorado Maldonado Informatica A
#include <iostream>

using namespace std;

int main(){
    char caracter;
    int contador=0;

    cout<<"Introduzca el texto terminado en un punto: ";
    caracter=cin.get();

    while(caracter!='.'){
        cout<<int(caracter)<<" ";
        contador++;
        caracter=cin.get();
    }
    cout<<endl<<"Numero de caracteres leidos: "<<contador<<endl;
}
