#include <iostream>
using namespace std;

int main()
{
    int numero, comprobacion_digitos, calculo_narcisista;
    int cantidad_digitos = 0;
    int aux = 1, digito = 0, resultado = 0;

    // Verificar que el numero introducido sea positivo
    do
    {
        cout << "Introduzca un numero entero mayor que 0: ";
        cin >> numero;
    } while (numero < 0);

    comprobacion_digitos = numero;
    calculo_narcisista = numero;

    // Contar los digitos que tiene el numero
    while (comprobacion_digitos > 0)
    {
        comprobacion_digitos /= 10;
        cantidad_digitos++;
    }

    // Calculo de numero narcisista
    while (calculo_narcisista > 0)
    {
        digito = calculo_narcisista % 10; // Valor del digito
        aux = 1;
        for (int i = 1; i <= cantidad_digitos; i++) // Elevo ese valor a la cantidad de digitos
        {
            aux *= digito;
        }
        resultado += aux;         // Sumo el valor de cada cifra
        calculo_narcisista /= 10; // Divido para buscar el valor del siguiente digito
    }

    // Comprobacion de si el numero es narcisista o no
    if (resultado == numero)
    {
        cout << "El numero " << numero << " SI es narcisista" << endl;
    }
    else
    {
        cout << "El numero " << numero << " NO es narcisista" << endl;
    }

    return 0;
}