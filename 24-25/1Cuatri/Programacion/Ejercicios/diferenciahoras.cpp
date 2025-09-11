
/*Diseña un algoritmo que lea de teclado dos instantes de tiempo en un mismo día, cada uno
representado por dos números naturales que indican la hora (en formato 24 horas) y los minutos, y
muestre por pantalla la diferencia entre ellos, también expresada en horas y minutos. Podemos
suponer que los datos de entrada son correctos y primero se introduce el instante anterior y después
el posterior.*/

#include <iostream>

using namespace std;

int main()

{
    int hora1, hora2, min1, min2, diferencia, diferenciahora, diferenciamin;

    cout << "Introduzca el primer tiempo(horas minutos): ";
    cin >> hora1 >> min1;
    cout << "Introduzca el segundo tiempo(horas minutos): ";
    cin >> hora2 >> min2;

    diferencia = ((hora2 * 60) + min2) - ((hora1 * 60) + min1);
    diferenciahora = diferencia / 60;
    diferenciamin = diferencia % 60;

    cout << "La diferencia de tiempo es: " << diferenciahora << " horas y " << diferenciamin << " minutos." << endl;
    return 0;
}
