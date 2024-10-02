#include <iostream>
using namespace std;
const int DIFERENCIA=32;
int main()
{
    char c1, c2, c3, c4;
cout<<"Introduzca cuatro letras minusculas: ";
cin>>c1>>c2>>c3>>c4;

c1=c1-DIFERENCIA;
c2=c2-DIFERENCIA;
c3=c3-DIFERENCIA;
c4=c4-DIFERENCIA;
cout<<"En mayusculas es: "<<c1<<c2<<c3<<c4;
return 0;
}
