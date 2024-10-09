#include <iostream>

using namespace std;

int main(){
    int num;

    cout<<"Introduzca un numero entero: ";
    cin>>num;

    if(num>=0){
        cout<<"El numero es positivo"<<endl;
    }else{
        cout<<"El numero es negativo"<<endl;
    }
    return 0;
}
