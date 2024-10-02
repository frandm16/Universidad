
/*Programa que lea de teclado tres números enteros (día, mes y año), si
la fecha es correcta mostrará el mensaje “Fecha Correcta”, en otro caso
mostrará “Error”, considerando que:
1) Cualquier valor del año es correcto.
2) Sólo son correctos los meses desde el número 1 hasta el número 12, ambos
inclusive, de tal forma que el número 1 corresponde al mes de Enero, el número 2
al mes de Febrero, y así sucesivamente hasta el número 12 que corresponde al
mes de Diciembre.
3) El número correspondiente al día debe ser mayor que cero, y menor o igual al
número de días del mes en que se encuentra, teniendo en cuenta si el año en que
se encuentra es bisiesto o no.
a. Los meses Enero, Marzo, Mayo, Julio, Agosto, Octubre, y Diciembre tienen 31 días.
b. Los meses Abril, Junio, Septiembre y Noviembre tienen 30 días.
c. El mes Febrero tiene 29 días si el año en que se encuentra es bisiesto, y en otro caso tiene 28 días.
d. Un año es bisiesto si es divisible entre 400, o si es divisible entre 4 y no es divisible entre 100*/

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
            break;
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
            break;
        default:
            break;
        }
    }
    else
    {
        cout << "Fecha incorrecta" << endl;
    }
}