
#include <iostream>
#include <array>
using namespace std;

//11:00

const int N=4;
const int M=6;

typedef array<int,N> TFila;
typedef array<TFila,M> TMatriz;

void IntroducirMatriz(TMatriz& matriz){
    cout<<"Introduzca la matriz "<<N<<"x"<<M<<" por filas:"<<endl;
    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            cin>>matriz[i][j];
        }
    }

}
void MostrarMatriz(TMatriz& matriz){
    cout<<"La matriz introducida es:"<<endl;
    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            cout<<matriz[i][j]<<" ";
        }
        cout<<endl;
    }


}
int main(){
TMatriz matriz={};
IntroducirMatriz(matriz);
MostrarMatriz(matriz);

return 0;
}
