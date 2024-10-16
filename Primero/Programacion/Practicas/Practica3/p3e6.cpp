//Francisco Dorado Maldonado Informatica A
#include <iostream>

using namespace std;

int main(){
    char simbolo;
    int oper1,oper2,operacion;
    //Esto solo es para la primera vez
    cout<<"Operacion: ";
    cin>>simbolo;

    while(simbolo!='&'){
        switch(simbolo){
        case '+':
            cout<<"Operando 1: ";
            cin>>oper1;
            cout<<"Operando 2: ";
            cin>>oper2;
            operacion=oper1+oper2;
            cout<<"Resultado: "<<operacion<<endl;
            break;
        case '-':
            cout<<"Operando 1: ";
            cin>>oper1;
            cout<<"Operando 2: ";
            cin>>oper2;
            operacion=oper1-oper2;
            cout<<"Resultado: "<<operacion<<endl;
            break;
        case '*':
            cout<<"Operando 1: ";
            cin>>oper1;
            cout<<"Operando 2: ";
            cin>>oper2;
            operacion=oper1*oper2;
            cout<<"Resultado: "<<operacion<<endl;
            break;
        case '/':
            cout<<"Operando 1: ";
            cin>>oper1;
            cout<<"Operando 2: ";
            cin>>oper2;
            if(oper2<=0){
                cout<<"ERROR: divisor no valido"<<endl;
            }else{
            operacion=oper1/oper2;
            cout<<"Resultado: "<<operacion<<endl;
            }
            break;
        default://En caso de simbolo diferente
            cout<<"ERROR: operacion no valida"<<endl;
            break;
        }
    //Antes de cerrar el bucle nos aseguramos de cambiar la operacion
    cout<<"Operacion: ";
    cin>>simbolo;
    }

    cout<<"FIN DEL PROGRAMA"<<endl;

}
