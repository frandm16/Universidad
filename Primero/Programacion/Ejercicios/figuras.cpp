
/*Desarrolle varios programas donde cada uno lee de teclado un número y
muestra una figura como en los siguientes ejemplos si el número leído fuera el
número 5.
+++++ +++++
+++++ ++++
+++++ +++
+++++ ++
+++++ +*/

#include <iostream>

using namespace std;

int main()
{
    int num, num2;
    cout << "Introduzca un numero: ";
    cin >> num2;
    num = num2;

    cout << "Figura 1: " << endl;

    for (int i = 0; i < num; i++)
    {
        for (int j = 0; j < num; j++)
        {
            cout << "+";
        }
        cout << endl;
    }
    cout << "-----" << endl;

    cout << "Figura 2: " << endl;

    for (int i = 0; i < num; i++)
    {
        for (int j = 0; j < num2; j++)
        {
            cout << "+";
        }

        num2--;
        cout << endl;
    }
}