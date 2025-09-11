#include <iostream>
#include <array>
using namespace std;
const int RANGO=26;


struct TDatos{
int ultimaPos;
int mayorDis;
bool repet;
};

typedef array<TDatos, RANGO> TArray;

void calcularMayorDis(TArray& letras){
    char c;
    int pos, dis;
    cout<<"Introduzca una secuencia de caracteres acabada en . ";
    pos=1;
    cin.get(c);
    while(c !='.'){
        if(('A'<=c)&&(c<='Z')){

            if(letras[c-'A'].ultimaPos==0){
                letras[c-'A'].ultimaPos=pos;
            }else{
                letras[c-'A'].repet=true;
                dis=pos-letras[c-'A'].ultimaPos-1;
                letras[c-'A'].ultimaPos=pos;

                if(dis>letras[c-'A'].mayorDis){
                    letras[c-'A'].mayorDis=dis;
                }
            }
        }
        cin.get(c);
        pos++;
    }


}

void escribirMayorDis(const TArray& letras){
    for(int i=0; i<RANGO; i++){
        if(letras[i].repet){
            cout<<"Distancia entre "<<char(i+'A')<<": "<<letras[i].mayorDis<<endl;
        }
    }
}

int main(){
    TArray letras={{}};
    calcularMayorDis(letras);
    escribirMayorDis(letras);

return 0;
}
