#include <iostream>

using namespace std;

int main(){
    int num,provincia,numoperacion,dcontrol,comprobacion;

    cout<<"Introduzca un numero de 4 digitos(el primero distinto de cero): ";
    cin>>num;

    if(num>=1000 && num<=9999){
        provincia=num/1000;
        numoperacion=(num%1000)/10;
        dcontrol=(num%10);
        comprobacion=numoperacion%provincia;

        if(dcontrol==comprobacion){
            cout<<"PROVINCIA: "<<provincia<<endl;
            cout<<"NUMERO DE OPERACION: "<<numoperacion<<endl;
            cout<<"DIGITO DE CONTROL: "<<dcontrol<<endl;
        }else{
            cout<<"ERROR: CODIGO INVALIDO (digito de control erroneo)"<<endl;
        }

    }else{
        cout<<"ERROR: CODIGO INVALIDO (no tiene 4 digitos)"<<endl;
    }
    return 0;
}
