#include <iostream>
#include <string>
using namespace std;

bool estaContenida(const string& prim, const string& seg){
    bool encontrado=false;
    int tope= seg.size()-prim.size();

    int i=0;

    while(i<=tope && !encontrado){
        if(prim[0]==seg[i] && (seg.substr(i, prim.size())==prim)){
            encontrado=true;
        }

        i++;
    }
    return encontrado;
}

int main(){
    string primera, segunda;
    cout<<"Introduzca dos palabras: ";
    cin>>primera>>segunda;

    if(estaContenida(primera,segunda)){
        cout<<"La primera palabra SI esta contenida en la segunda"<<endl;

    }else{
        cout<<"La primera palabra NO esta contenida en la segunda"<<endl;
    }
    return 0;
}
