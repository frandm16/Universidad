#include <iostream>
using namespace std;
const float TASA = 25.0;
const float PRECIO_HORA = 60.0;
int main()
{
double horas,dias,total,neto;
cout << "Introduzca las horas trabajadas: ";
cin >> horas;
cout << "Introduzca los dias trabajados: ";
cin >> dias;
total=horas*PRECIO_HORA*dias;

neto = total-TASA;
cout << "El valor total a pagar es: ";
cout << total << endl;
cout << "El valor neto a pagar es: ";
cout << neto << endl;
return 0;
}
