/*Escribe un algoritmo que lea un lista de números enteros terminada en 0 y que encuentre y
escriba en la pantalla la posición de la primera y de la última aparición del número 12 dentro de
la lista. Ejemplos de ejecución:
Introduzca la secuencia de numeros (0 para terminar): 3 -2 4 6 0
Primera Aparicion del 12: 0
Ultima Aparicion del 12: 0
Introduzca la secuencia de numeros (0 para terminar): 3 12 4 6 0
Primera Aparicion del 12: 2
Ultima Aparicion del 12: 2
Introduzca la secuencia de numeros (0 para terminar): 3 12 4 12 -5 8 12 0
Primera Aparicion del 12: 2
Ultima Aparicion del 12: 7*/

#include <iostream>

using namespace std;

int main()
{
    int posicion = 0, ultimo = 0, primero = 0, num;

    cout << "Introduzca la secuencia de numeros(0 para terminar): ";
    cin >> num;

    while (num != 0)
    {
        posicion++;
        if (num == 12)
        {

            if (primero == 0)
            {
                primero = posicion;
                ultimo = posicion;
            }
            else
            {
                ultimo = posicion;
            }
        }
        cin >> num;
    }
    cout << "Primera aparicion de 12: " << primero << endl;

    cout << "Ultima aparicion de 12: " << ultimo << endl;
}