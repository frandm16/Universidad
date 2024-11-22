#include<iostream>
using namespace std;

void leerNumero(int& num){
do{
    cout<<"Introduzca un numero natural: ";
    cin>>num;
}while(num<0);
}
int sumaNaturales(int num){
    int resul=0;

    if(num>=1){
        resul=num+sumaNaturales(num-1);
    }
    return resul;
}


int main(){
    int num;

    leerNumero(num);
    cout<<sumaNaturales(num)<<endl;

    return 0;
}

