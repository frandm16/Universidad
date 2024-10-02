/*Programa para pasar de pulgadas a cm
Autor: FDM
*/

#include <iostream>

using namespace std;

const float CTMS_PULGADAS = 2.54;

int main()
{
    float pulgadas, centimetros;

    cout << "Programa para convertir pulgadas a centimetros" << endl;

    cout << "Introduzca la cantidad de pulgadas:";
    cin >> pulgadas;

    centimetros = pulgadas * CTMS_PULGADAS;

    cout << "Esa medida de pulgadas en ctms es:" << centimetros << endl;
    return 0;
}
