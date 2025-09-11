#include <iostream>
using namespace std;

void leerNumero(int &num)
{
    do
    {
        cout << "Introduzca un numero natural: ";
        cin >> num;
    } while (num < 0);
}

bool esPrimoRec(int num, int divisor)
{
    bool esPrimo;
    while (esPrimo == true)
    {
        esPrimo = true;
        if (divisor < num)
        {
            if (num % divisor == 0)
            {
                esPrimo = false;
            }
            esPrimoRec(num, divisor + 1);
        }
    }

    return esPrimo;
}

int main()
{
    int num, divisor = 2;
    leerNumero(num);
    cout << esPrimoRec(num, divisor) << endl;
    return 0;
}
