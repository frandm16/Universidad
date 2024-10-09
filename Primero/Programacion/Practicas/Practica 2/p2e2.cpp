#include <iostream>

using namespace std;

int main(){
    int num1,num2,num3;

    cout<<"Introduzca tres numeros enteros: ";
    cin>>num1>>num2>>num3;

    if(num1>num2 && num1<num3){
        cout<<"El mayor estricto es: "<<num1<<endl;
    }else if(num2>num1 && num2>num3){
        cout<<"El mayor estricto es: "<<num2<<endl;
    }else if(num3>num1 && num3>num2){
        cout<<"El mayor estricto es: "<<num3<<endl;
    }else{
        cout<<"No existe mayor estricto."<<endl;
    }
    return 0;
}

