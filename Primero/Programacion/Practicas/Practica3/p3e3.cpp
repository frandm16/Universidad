//Francisco Dorado Maldonado Informatica A
#include <iostream>

using namespace std;
const char PRIMER_SIMB='x';
const char SEGUNDO_SIMB='o';

int main(){
    int numero;

    //Comprobacion de numero positivo
    do{
        cout<<"Introduzca el numero: ",
        cin>>numero;
    }while(numero<=0);
    //Cantidad de filas
    for(int i=0;i<numero;i++){

        //Printear cantidad de caracteres por fila
        for(int j=0;j<numero/2;j++){
            cout<<PRIMER_SIMB<<SEGUNDO_SIMB;
        }
        //En caso de impar, printear otro simbolo
        if(numero%2!=0){
            cout<<PRIMER_SIMB;
        }
        cout<<endl;
    }

}
