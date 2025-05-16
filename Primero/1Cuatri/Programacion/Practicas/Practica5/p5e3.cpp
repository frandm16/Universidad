#include<iostream>
using namespace std;

void leerNumero(int& x, int& y){
    do{
        cout<<"Introduzca dos numeros naturales: ";
        cin>>x>>y;
    }while(x<0 && y<0);
}

int productoRecursivo(int x,int y){
    int resul=0;
    if(y>=1){
        resul= x+productoRecursivo(x,y-1);
    }

    return resul;
}


int main(){
    int x,y;
    leerNumero(x,y);
    cout<<"El producto es: "<<productoRecursivo(x,y)<<endl;

    return 0;
}
