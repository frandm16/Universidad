#include <iostream>

using namespace std;

void leer(int& n);
bool esPrimo(int n);
void imprimePrimos(int n);

int main() {
    int n;

    leer(n);
    imprimePrimos(n);
    return 0;
}

void leer(int& n){
    do{
        cout<<"Introduzca un numero entero mayor que cero: ";
        cin>>n;
    }while(n<=0);
}

bool esPrimo(int n){
    bool SiEsPrimo = true ;

    for(int i=2;(i <= n/2);i++){
        if(n != i){
           if(n%i == 0){
                SiEsPrimo = false;
           }
      }
    }
 return SiEsPrimo;
}

void imprimePrimos(int n){
 int contador = 0;
    for(int i=2; contador<n;i++){
        if(esPrimo(i)){
           cout << i <<" ";
           contador ++;
        }
    }
}


