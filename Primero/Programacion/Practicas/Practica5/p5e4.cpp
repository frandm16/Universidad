#include<iostream>
using namespace std;

void leerNumero(int& n){
    do{
        cout<<"Introduzca un numero natural: ";
        cin>>n;
    }while(n<0);
}

void inverso(int n){
    if(n>=1){
        cout<<n%10;
        inverso(n/10);
    }
}

int main(){
    int n;
    leerNumero(n);
    inverso(n);

    return 0;
}
