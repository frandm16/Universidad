#include <iostream>
using namespace std;
int main()
{
    int a = 6, b = 14;
    int auxiliar;
    cout << "a vale " << a << " y b vale " << b << endl;
    // ¿Qué hacen estas tres sentencias?
    a = a + b;
    b = a - b;
    a = a - b;

    /*Este programa intercambia los valores de las variables a y b, pero sin usar ninguna
    variable auxiliar, sino que lo hace mediante los operadores + y -, consiguiendo
    retomar la variable anterior de manera simple.*/

    cout << "a vale " << a << " y b vale " << b << endl;
    return 0;
}