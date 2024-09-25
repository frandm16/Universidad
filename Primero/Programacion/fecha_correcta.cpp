#include <iostream>

using namespace std;

int main()
{
    int dia, mes, anyo;
    cout << "Introduzca la fecha(dia mes año): ";
    cin >> dia >> mes >> anyo;

    if ((mes >= 1) && (mes <= 12))
    {
        switch (mes)
        {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            if ((dia >= 1) && (dia <= 31))
            {
                cout << "Fecha correcta" << endl;
            }
            else
            {
                cout << "Fecha incorrecta" << endl;
            }
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            if ((dia >= 1) && (dia <= 30))
            {
                cout << "Fecha correcta" << endl;
            }
        case 2:
            if ((anyo % 400 == 0) || (anyo % 4 == 0) && (anyo % 100 != 0))
            {
                if ((dia >= 1) && (dia <= 29))
                {
                    cout << "Fecha correcta" << endl;
                }
                else
                {
                    cout << "Fecha incorrecta" << endl;
                }
            }
            else
            {
                if ((dia >= 1) && (dia <= 28))
                {
                    cout << "Fecha correcta" << endl;
                }
                else
                {
                    cout << "Fecha incorrecta" << endl;
                }
            }
        default:
            break;
        }
    }
    else
    {
        cout << "Fecha incorrecta" << endl;
    }
}