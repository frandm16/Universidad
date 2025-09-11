
/*Diseña un algoritmo que lea un carácter (suponemos que será una letra mayúscula), lo convierta a
minúscula y lo muestre por pantalla.*/

#include <iostream>

using namespace std;

int main()
{
    char letra;

    cout << "Introduzca un caracter: ";
    cin >> letra;

    letra = letra + 32;
    cout << "En minusculas es: " << letra << endl;
    return 0;
}
