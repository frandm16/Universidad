#include<iostream>
#include<array>
#include<string>
using namespace std;
const int MAX_PAL_DIST = 15; 

struct Treg{
string palabra;
int priPos;
int ultPos;
};
typedef array<Treg, MAX_PAL_DIST> Tarray;
struct TDatos{
Tarray elementos;
int numElem;
};

int buscarInd(const string& pal, const TDatos& datos){

    int ind = 0;
    while((ind<datos.numElem)&&(pal!=datos.elementos[ind].palabra)){
    ind++;
    }

    return ind;

}

void almacenar(TDatos& datos, const string& pal, int pos){

int ind = buscarInd(pal,datos);
if(ind>=datos.numElem){

    datos.elementos[datos.numElem].palabra = pal;
    datos.elementos[datos.numElem].priPos = pos;
    datos.elementos[datos.numElem].ultPos = pos;
    datos.numElem++;
}else{

    datos.elementos[ind].ultPos = pos;//cuando la palabra ya ha aparecido antes


}

}

void mostrar(const TDatos& datos){

cout<<"Posiciones primera y ultima: ";
for(int i = 0; i<datos.numElem; i++){
cout<<datos.elementos[i].palabra<<" "<<datos.elementos[i].priPos<<" "<<datos.elementos[i].ultPos<<endl;

}

}

int main(){
TDatos datos;
string pal;
int pos;
datos.numElem=0;
cout<<"Introduzca el texto(FIN para acabar): ";
cin>>pal;

while(pal!= "FIN"){
almacenar(datos,pal,pos);
cin>>pal;
pos++; 
}
mostrar(datos);

return 0;
}