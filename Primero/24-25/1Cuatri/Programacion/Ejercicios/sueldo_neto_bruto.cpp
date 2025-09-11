/*
Diseña un algoritmo para calcular el salario neto de un trabajador en una determinada empresa.
Para ello se leerán dos números de teclado. El primero será un número real que representa el sueldo
base del empleado. El segundo será un número natural que representa la antigüedad (en años) del
empleado en la empresa. El salario bruto del empleado se calcula sumando al sueldo base unas
gratificaciones por antigüedad. En concreto, el empleado recibirá 60 € por quinquenio trabajado y 6
€ por cada año del tramo para conseguir el siguiente quinquenio. Finalmente, el salario neto se
calcula restando al salario bruto un 20% de impuestos sobre el salario bruto más un 5% de seguro
médico también sobre el salario bruto.*/

#include <iostream>

using namespace std;

int main()
{
    float base, bruto, neto;
    int antiguedad;

    cout << "Introduzca el sueldo base:";
    cin >> base;
    cout << "Introduzca la antiguedad:";
    cin >> antiguedad;

    bruto = base + 60 * (antiguedad / 5) + 6 * (antiguedad % 5);
    neto = bruto * 0.75;

    cout << "El salario bruto es: " << bruto << endl;
    cout << "El salario neto es: " << neto << endl;

    return 0;
}