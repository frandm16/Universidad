
/*Un número se dice que es perfecto si su valor coincide con la suma de sus divisores sin contar
al propio número. Así, 6 es perfecto porque 6 = 1 + 2 + 3 y 28 también lo es porque 28 = 1 + 2
+ 4 + 7 + 14. Diseña un algoritmo que muestre por pantalla el siguiente número perfecto
después del 28.*/

#include <iostream>

using namespace std;

int main()
{
    int intento = 29, suma; // Comenzamos desde 29, que es el primer numero despues de 28
    bool encontrado = false;

    while (!encontrado)
    {
        suma = 0;
        for (int cont = 1; cont <= (intento / 2); cont++)
        {
            if (intento % cont == 0)
            {
                suma += cont; // Sumar los divisores
            }
        }
        if (intento == suma)
        {
            encontrado = true; // Encontramos el numero perfecto
        }
        else
        {
            intento++; // Incrementamos para buscar el siguiente
        }
    }
    cout << "El numero perfecto mayor que 28 es: " << intento << endl;

    return 0;
}