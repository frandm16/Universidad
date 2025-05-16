#include <iostream>
using namespace std;

/*El programa no funciona cuando se introduce un numero mas grando del que cabe
en los 4 bytes de la variable tipo int, que tiene de rango -2.147.483.648 y 2.147.483.647,
por lo tanto al sumar 1 y 300000000 da un numero erroneo.*/
int main()
{
    int num1,num2,suma;
    cout<<"Introduzca dos numeros: ";
    cin>>num1>>num2;

    suma=num1+num2;

    cout<<"La suma es: "<<suma;

return 0;

}

