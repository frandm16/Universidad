/*
Diseña un algoritmo que lea de teclado tres caracteres dígitos, obtenga el número natural que
representan y lo muestre por pantalla. Podemos suponer que los datos de entrada son correctos.*/

#include <iostream>

using namespace std;

int main()
{
    char car1, car2, car3;

    cout << "Introduzca el primer caracter:";
    cin >> car1;
    cout << "Introduzca el segundo caracter:";
    cin >> car2;
    cout << "Introduzca el tercer caracter:";
    cin >> car3;

    cout << "Los caracteres introducidos representan: " << int(car1) << " " << int(car2) << " " << int(car3) << endl;

    return 0;
}