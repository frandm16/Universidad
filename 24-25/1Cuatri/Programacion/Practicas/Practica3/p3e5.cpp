//Francisco Dorado Maldonado Informatica A
#include <iostream>

using namespace std;

int main(){
    int numero,centinela=1;
    double pi=4,numerador=2,denominador=3;

    cout<<"Introduzca la cantidad de fracciones: ";
    cin>>numero;

    while(centinela<=numero){
        //Esta condicion es para n impar
        pi*=numerador/denominador;
        numerador+=2;
        centinela++;

        //El if solo se hace para n par
        if(centinela<=numero){
            pi*=numerador/denominador;
            denominador+=2;
            centinela++;
        }
    }

    cout<<"El valor para n="<<numero<<" es: "<<pi<<endl;
}
