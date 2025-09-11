#include <iostream>

using namespace std;
const int EQUIVALENCIA=1024;
int main(){
int bytes,mbytes,kbytes;

cout<<"Introduzca la cantidad de bytes: ";
cin>>bytes;
kbytes=bytes/EQUIVALENCIA;
mbytes=kbytes/EQUIVALENCIA;
kbytes=kbytes%EQUIVALENCIA;
bytes=bytes%EQUIVALENCIA;

cout<<"Equivalen a: "<<mbytes<<" MBytes, "<<kbytes<<" KBytes y "<<bytes<<" bytes.";
return 0;

}
