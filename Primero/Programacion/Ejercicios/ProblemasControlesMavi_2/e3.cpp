#include <iostream>

using namespace std;

int main()
{
    int numero;

    // Verificar que el numero introducido sea positivo
    do
    {
        cout << "Introduzca un numero entero mayor que 0: ";
        cin >> numero;
    } while (numero < 0);

    // Primera fila de +
    for (int i = 0; i < numero; i++)
    {
        cout << "+";
    }
    cout << endl;

    // Filas entre la primera y ultima
    for (int i = 1; i < numero - 1; i++)
    {
        cout << "+"; // primer +
        for (int j = 1; j < numero - 1; j++)
        {
            cout << " "; // espacios en medio
        }
        cout << "+" << endl; // ultimo +
    }

    // Solo pone la ultima fila si es mayor que 1
    if (numero > 1)
    {
        for (int i = 0; i < numero; i++)
        {
            cout << "+";
        }
        cout << endl;
    }

    return 0;
}