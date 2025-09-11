#include <iostream>

using namespace std;
const int GASTO_FIJO=1;
const float GASTO_100=0.5;
const float GASTO_150=0.35;
const float GASTO_RESTANTE=0.25;

int main(){
    int consumo;
    float precio;

    cout<<"Introduzca el consumo del contador: ";
    cin>>consumo;

    if(consumo>=0 && consumo<=100){
        precio=GASTO_FIJO+(consumo*GASTO_100);
        cout<<"El importe a pagar es: "<<precio<<" euros"<<endl;
    }else if(consumo>=0 && consumo<=250){
        precio=GASTO_FIJO+(100*GASTO_100)+(consumo-100)*GASTO_150;
        cout<<"El importe a pagar es: "<<precio<<" euros"<<endl;
    }else if(consumo>=0){
        precio=GASTO_FIJO+(100*GASTO_100)+(150*GASTO_150)+(consumo-250)*GASTO_RESTANTE;
        cout<<"El importe a pagar es: "<<precio<<" euros"<<endl;
    }else{
        cout<<"El consumo negativo no se admite"<<endl;
    }

    return 0;
}
