//Francisco Dorado Maldonado Informatica A
#include <iostream>

using namespace std;

int main(){
int cantidad_modelos,modelo,total=0;

cout<<"Introduzca el numero de modelos de coche: ";
cin>>cantidad_modelos;
//Printea tantos modelos como cantidad_modelos
for(int i=1;i<=cantidad_modelos;i++){

    cout<<"Precio modelo "<<i<<": ";
    cin>>modelo;
    total+=modelo;
    modelo=0;
}
total /=cantidad_modelos;
cout<<"El valor medio de los "<<cantidad_modelos<<" modelos de coche asciende a: "<<total<<" euros"<<endl;
}
