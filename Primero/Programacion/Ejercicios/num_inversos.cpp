
/*Programa que muestra los N primeros números enteros mayores o iguales a
cero (en orden inverso), siendo N un número leído desde teclado, ej. Para N=6:
5 4 3 2 1 0*/

#include <iostream>

using namespace std;

int main()
{

    int num;
    cout << "Introduzca un numero: ";
    cin >> num;

    while (num >= 1)
    {

        num = num - 1;
        cout << num << " ";
    }
}