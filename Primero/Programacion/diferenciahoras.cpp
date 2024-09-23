
#include <iostream>

using namespace  std;

int main()

{
    int hora1, hora2, min1, min2,diferencia ,diferenciahora,diferenciamin;


    cout<<"Introduzca el primer tiempo(horas minutos)";
    cin>> hora1 >> min1 ;
    cout<<"Introduzca el segundo tiempo(horas minutos)";
    cin>> hora2 >> min2 ;

     diferencia = ((hora2*60)+min2)- ((hora1*60)+min1);
    diferenciahora=diferencia/60;
    diferenciamin=diferencia%60;

    cout<< "La diferencia de tiempo es:"<< diferencia;
    return 0;
}


