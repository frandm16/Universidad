#include <iostream>
#include <array>
using namespace std;

const int N=3;

typedef array<int,N> TFilas;
typedef array<TFilas,N> TMatriz;

void ACero(TMatriz& matriz){
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            matriz[i][j]=0;
        }
    }

}
void DibujarMatriz(TMatriz& matriz){
    cout<<"Introduzca los numeros enteros para una matriz cuadrada de "<<N<<"x"<<N<<":"<<endl;

    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            cin>>matriz[i][j];
        }
    }
}
void PrintearMatriz(TMatriz& matriz){
    cout<<"La matriz introducida es:"<<endl;

    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            cout<<matriz[i][j]<<" ";
        }
        cout<<endl;
    }


}

bool PrimRegla(TMatriz& matriz){
int cont=0;
for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
                if(matriz[i][j]>=0 && matriz[i][j]<100){
                cont++;
                }
        }
}

return cont==N*N;
}

bool SegRegla(TMatriz& matriz, bool esValido){


    for(int i=0;i<N;i++){
        int suma=0;
        for(int j=0;j<N;j++){
            suma+=matriz[i][j];
        }
        if(esValido!=false){
            if(suma!=100){
            esValido=false;
        }
        }
    }
    return esValido==true;
}

bool TerRegla(TMatriz& matriz, bool esValido){


    for(int j=0;j<N;j++){
        int suma=0;
        for(int i=0;i<N;i++){
            suma+=matriz[i][j];
        }
        if(esValido!=false){
            if(suma!=100){
            esValido=false;
        }
        }
    }
    return esValido==true;
}
void Resultado(bool esValido){

}

int main(){
TMatriz matriz;
bool esValido=true;

ACero(matriz);
DibujarMatriz(matriz);
PrintearMatriz(matriz);
if(PrimRegla(matriz)&&SegRegla(matriz,esValido)&&TerRegla(matriz,esValido)){
        cout<<"La matriz introducida SI es doblemente estocastica normalizada"<<endl;
    }else{
        cout<<"La matriz introducida NO es doblemente estocastica normalizada"<<endl;
    }

return 0;
}
