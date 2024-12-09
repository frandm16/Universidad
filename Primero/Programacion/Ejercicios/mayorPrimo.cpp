#include <iostream>
#include <array>
using namespace std;
const int MAX=10;
typedef array <int,MAX> TArray;


void leerDatos(TArray& arr){

        cout<<"Introduzca el contenido del array (MAXIMO "<<MAX<<"): ";
    for(int i=0; i<MAX; i++){
        cin>>arr[i];
    }
}
bool esPrimo(int num){
    int x=2;
    while(x<num && num%x !=0){
        x++;
    }

    return (num !=1 && x>=num);
}

int mayorPrimo(const TArray& arr){

    int mayorPrimo=0;
    for(int i=0;i<MAX;i++){
        if(esPrimo(arr[i])){
            if(arr[i]>mayorPrimo){
                mayorPrimo=arr[i];
            }
        }
    }

    return mayorPrimo;
}






int main(){

    TArray arr;
    leerDatos(arr);
    if(mayorPrimo(arr)==0){
        cout<<"El array no contiene ningun primo"<<endl;
    }else{
    cout<<"El mayor primo del array es: "<<mayorPrimo(arr)<<endl;
    }

    return 0;
}
