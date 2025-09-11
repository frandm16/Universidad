#include <iostream>
using namespace std;

int main()
{
    int numero, cifra, alreves, copia;

    cout<<"Introduzca un secuencia de numeros terminada en -1: ";
    cin>>numero;
    while(numero!=-1){
        alreves=0;
        cifra=0;
        copia=numero;
        while(copia!=0){

            cifra=copia%10;
            alreves=alreves*10+cifra;
            copia /=10;


        }
        if(alreves==numero){
        cout<<alreves<<" ";
        }
        cin>>numero;
    }
    return 0;
}
