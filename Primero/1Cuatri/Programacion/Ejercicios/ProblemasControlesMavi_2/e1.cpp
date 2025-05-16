#include <iostream>
using namespace std;

int main()
{
    int numero, n;
    int cantidad = 1;
    int resultado = 1;

    cout << "Introduzca el numero: ";
    cin >> numero;
    cout << "Introduzca el valor de n: ";
    cin >> n;

    do
    {
        resultado++;
        cantidad = 1;
        for (int i = 1; i <= n; i++)
        {
            cantidad = cantidad * resultado;
        }
    } while (cantidad < numero);
    resultado--;
    cout << "La raiz n-esima de " << numero << " es: " << resultado << endl;

    return 0;
}
