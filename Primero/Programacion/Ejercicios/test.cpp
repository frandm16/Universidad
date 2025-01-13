
#include <iostream>
#include <array>
const int N=10;
const int CONT=2;
const int NUM=7;
using namespace std;

typedef array<int,N> TArray;


int main(){
    TArray array;
    int i=0, Pos=-1;
    //array={0,1,2,3,4,5,6,7,8,9};
    array={7,1,2,3,4,5,6,7,8,9};

    while(i<N ){
            if(Pos!=-1){
                cout<<array[i]<<" ";
                if(i==Pos+CONT){
                    Pos=-1;
                    cout<<endl;
                }
            }
           if(array[i]==NUM){
            Pos=i;
           }
        i++;
    }
}
