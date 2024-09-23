

#include <iostream>

using namespace std;

const double pi = 3.14159265;

int main()
{
    int num1, num2 ,num3;
    double radianes;

    cout<< "Introduzca la cantidad de grados:";
    cin>> num1;
    cout<< "Introduzca la cantidad de minutos:";
    cin>>num2;
    cout<< "Introduzca la cantidad de segundos:";
    cin>>num3;

    radianes= (num1 + num2/60 +num3/3600) * (pi/180);

    cout<< "La cantidad en radianes es: "<< radianes;

    return 0;


}
