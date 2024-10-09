#include <iostream>

using namespace std;
const int PRECIO_UNIDAD=100;
const float IVA=1.12;
const float DESCUENTO=0.95;

int main(){
    int unidad;
    float total, descontado;

    cout<<"Numero de unidades adquiridas: ";
    cin>>unidad;

    total=(PRECIO_UNIDAD*unidad)*IVA;

    if(total>=300){
        cout<<"Se aplica un descuento de 5%"<<endl;
        descontado=total*DESCUENTO;
        cout<<"El precio total a pagar es: "<<descontado<<" euros"<<endl;
    }else{
        cout<<"El precio total a pagar es: "<<total<<" euros"<<endl;
    }
    return 0;
}

