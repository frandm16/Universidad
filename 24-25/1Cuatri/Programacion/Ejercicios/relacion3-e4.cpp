#include <iostream>
using namespace std;

void leerDato( int& num);
int siguientePrimo(int num, int primo);
void factorizacionPrimos(int &numero);
bool esPrimo( int posibleNumeroPrimo);

int main(){
    int numero;

    leerDato(numero);
    cout<<"Los primos divisores de "<<numero<<" son: ";
    factorizacionPrimos(numero);

    return 0;
}

//Lee el numero cuando sea positivo
void leerDato(int& numero){
    do{
        cout<<"Introduzca un numero mayor que 0:";
        cin>>numero;
    }while(numero<0);
}


bool esPrimo(int posibleNumeroPrimo){
    bool esPrimo=true;
    int contador=2;

    while(contador< posibleNumeroPrimo/2 && esPrimo){
        if(posibleNumeroPrimo %contador == 0){
            esPrimo= false;
        }else{
        contador++;
        }
    return esPrimo;
    }
return 0;
}
int siguientePrimo(int primoactual){
    int posibleNumeroPrimo = primoactual+1;
    while(!esPrimo(posibleNumeroPrimo)){
        posibleNumeroPrimo++;
    }
    return posibleNumeroPrimo;
}


void factorizacionPrimos(int &numero){
    int primoactual=2;
    while(numero!=1){
        if(numero %primoactual ==0){

            cout<<primoactual<<" ";
            numero /=primoactual;
        }else{
            primoactual=siguientePrimo(primoactual);
        }

    }

}

