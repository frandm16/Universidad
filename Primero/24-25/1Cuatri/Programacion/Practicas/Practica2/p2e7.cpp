#include <iostream>

using namespace std;

int main(){
    int mes;
    cout<<"Introduzca numero del mes: ";
    cin>>mes;

    switch(mes){
        case 1 :
            cout<<"Ese mes tiene 31 dias"<<endl;
            break;
        case 2 :
            cout<<"Ese mes tiene 28 dias"<<endl;
            break;
        case 3 :
            cout<<"Ese mes tiene 31 dias"<<endl;
            break;
        case 4 :
            cout<<"Ese mes tiene 30 dias"<<endl;
            break;
        case 5 :
            cout<<"Ese mes tiene 31 dias"<<endl;
            break;
        case 6 :
            cout<<"Ese mes tiene 30 dias"<<endl;
            break;
        case 7 :
            cout<<"Ese mes tiene 31 dias"<<endl;
            break;
        case 8 :
            cout<<"Ese mes tiene 31 dias"<<endl;
            break;
        case 9 :
            cout<<"Ese mes tiene 30 dias"<<endl;
            break;
        case 10 :
            cout<<"Ese mes tiene 31 dias"<<endl;
            break;
        case 11 :
            cout<<"Ese mes tiene 30 dias"<<endl;
            break;
        case 12 :
            cout<<"Ese mes tiene 31 dias"<<endl;
            break;
        default:
            cout<<"Mes incorrecto"<<endl;
            break;
    }
    return 0;
}

