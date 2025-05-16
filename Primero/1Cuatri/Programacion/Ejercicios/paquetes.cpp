
#include <iostream>

using namespace std;

int main(){

int num;
float peso;

cout<<"Introduzca el numero del paquete: ";
cin>>num;
cout<<"Introduzca su peso: ";
cin>>peso;

if(peso<32){
    cout<<"El paquete "<<num<< " es de CLASE 1"<<endl;
}else if(peso<=128){
    cout<<"El paquete "<<num<< " es de CLASE 2"<<endl;
}else{
    cout<<"El paquete "<<num<< " es de CLASE 3"<<endl;
}
return 0;
}
