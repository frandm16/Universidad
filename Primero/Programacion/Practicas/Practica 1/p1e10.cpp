#include <iostream>
using namespace std;

const int EQUIV_SEMANA=604800;
const int EQUIV_DIA=86400;
const int EQUIV_HORA=3600;
const int EQUIV_MINUTO=60;

int main()
{
int semanas,dias,horas,minutos,segundos;
cout<<"Introduzca la cantidad de segundos: ";
cin>>segundos;
semanas=segundos/EQUIV_SEMANA;
dias=(segundos-(semanas*EQUIV_SEMANA))/EQUIV_DIA;
horas=(segundos-(semanas*EQUIV_SEMANA)-(dias*EQUIV_DIA))/EQUIV_HORA;
minutos=(segundos-(semanas*EQUIV_SEMANA)-(dias*EQUIV_DIA)-(horas*EQUIV_HORA))/EQUIV_MINUTO;
segundos=segundos-(semanas*EQUIV_SEMANA)-(dias*EQUIV_DIA)-(horas*EQUIV_HORA)-(minutos*EQUIV_MINUTO);
cout<<"Equivale a: "<<semanas<<" semanas, "<<dias<<" dias, "<<horas<<" horas, "<<minutos<<" minutos y "<<segundos<<" segundos.";

return 0;

}


