//Francisco Dorado Maldonado Informatica A
#include <iostream>

using namespace std;

int main(){
int numero, resultado=0;

do{
    cout<<"Introduzca un numero: ";
    cin>>numero;
}while(numero<1);
/*Usando while
while(numero>0){
    resultado+=numero;
    numero--;
}
*/

/*Usando do-while
do{
    resultado+=numero;
    numero--;
}while(numero>0);
*/

//Usando for
for(int i=0;i<numero;){
    resultado+=numero;
    numero--;
}

cout<<"El resultado es: "<<resultado<<endl;
}
