#include <iostream>
using namespace std;

struct TTiempo{
    int horas, minutos, segundos;
};
void convertir(int seg, TTiempo& t){
    t.horas= seg/3600;
    seg%=3600;
    t.minutos= seg/60;
    t.segundos=seg%60;

}
void  mostrar(const TTiempo& t){
    cout<<t.horas<<" horas, "<<t.minutos<<" minutos y "<<t.segundos<<" segundos."<<endl;
}
int main(){
    int seg;
    TTiempo t;
    cout<<"Introduzca la cantidad de segundos: ";
    cin>>seg;
    cout<<"------Convirtiendo------"<<endl;
    convertir(seg,t);
    mostrar(t);

    return 0;
}

