/*Implemente un programa que mulplique, mediante sumas, dos números
enteros (>=0) leídos de teclado.*/

#include <iostream>

using namespace std;

int main()
{
    int num1, num2, total;
    cout << "Introduzca dos numeros: ";
    cin >> num1 >> num2;
    total = num2;
    for (int i = 1; i < num1; i++)
    {
        total = total + num2;
    }

    cout << "Es: " << total << endl;
}