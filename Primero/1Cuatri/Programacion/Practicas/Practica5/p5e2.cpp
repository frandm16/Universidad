#include<iostream>
using namespace std;

void leerNumero(int& x, int& y){
    do{
        cout<<"Introduzca dos numeros naturales: ";
        cin>>x>>y;
    }while(x<0 && y<0);
}

int potenciaRecursiva(int x,int y){
    int resul=0;
    if(y>=1){
        resul= x*potenciaRecursiva(x,y-1);
    }else{
    resul+=1;
    }

    return resul;
}

int main(){
    int x,y;
    leerNumero(x,y);
    cout<<"La potencia es: "<<potenciaRecursiva(x,y)<<endl;

    return 0;
}
