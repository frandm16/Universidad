
#include <iostream>
using namespace std;

void leer(int& a, int& b);
void ordenar(int& a, int& b);
double factorial (int n);
double potencia( int base, int exp);
double termino(int a, int b);

int main(){
int a, b;

leer(a, b);
ordenar(a, b);
termino(a, b);


return 0;
}


void leer(int&a, int& b){
    do{
        cout<<"Introduzca dos numeros enteros mayores que cero: ";
        cin>>a>>b;
    }while(a<=0 || b<=0);
}

void ordenar(int& a, int& b){
    int aux=0;
    if(a>b){
        aux=b;
        b=a;
        a=aux;
    }
}
double factorial(int n){
    int factor=1;
    for(int i=1; i<=n; i++){
        factor*=i;
    }
    return factor;
}

double potencia(int base, int exp){
    int potencia=1;
    for(int i=1; i<=exp;i++){
        potencia*=base;
    }
    return potencia;
}

double termino(int a, int b){
    double resultado, numerador, denominador;

    numerador= potencia(b, a);
    denominador= factorial(a);
    resultado=numerador/denominador;
    cout<<resultado<<endl;
    return resultado;
}
