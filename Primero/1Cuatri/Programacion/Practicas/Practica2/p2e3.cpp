#include <iostream>

using namespace std;

int main(){
    char car;
    cout<<"Introduzca un caracter: ";
    cin>>car;

    if(car>='A' && car<='Z'){
        cout<<"Es letra"<<endl;
    }else if(car>='a' && car<='z'){
        cout<<"Es letra"<<endl;
    }else if(car=='.'){
        cout<<"Es punto"<<endl;
    }else{
        cout<<"Error"<<endl;
    }

    return 0;
}
