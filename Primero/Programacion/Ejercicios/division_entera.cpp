
#include <iostream>

using namespace std;

int main(){
int numerador, denominador, division;
cout<<"Introduzca el numerador: ";
cin>>numerador;

do{
    cout<<"Introduzca el denominador: ";
    cin>>denominador;
} while (denominador==0);
division=numerador/denominador;
cout<<"La division es: "<<division<<endl;
return 0;


}
