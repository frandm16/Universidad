#include <iostream>
using namespace std;
const float PART_TEORIA= 0.7;
const float PART_PRACTICA=0.3;

int main()
{
float teorico,problemas,total;
cout<<"Introduzca la nota de teoria: ";
cin>>teorico;
cout<<"Introduzca la nota de problemas: ";
cin>>problemas;
total=teorico*PART_TEORIA+problemas*PART_PRACTICA;
cout<<"La nota final es: "<<total;
return 0;

}
