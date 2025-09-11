
/*Diseña un algoritmo que lea de teclado tres números naturales que representan el valor de un
ángulo expresado en grados, minutos y segundos, y muestre por pantalla ese valor expresado en
radianes. Podemos suponer que los datos de entrada son correctos. Por ejemplo, si se introducen
190 grados, 25 minutos y 7 segundos, la salida será 3.32344 radianes.*/

#include <iostream>

using namespace std;

const float pi = 3.14159265;

int main()
{
    int num1, num2, num3;
    float radianes;

    cout << "Introduzca la cantidad de grados:";
    cin >> num1;
    cout << "Introduzca la cantidad de minutos:";
    cin >> num2;
    cout << "Introduzca la cantidad de segundos:";
    cin >> num3;

    radianes = (num1 + (float(num2) / 60) + (float(num3) / 3600)) * pi / 180;

    cout << "La cantidad en radianes es: " << radianes << endl;

    return 0;
}
