#include <iostream>

using namespace std;

int main(){
int num;

/*Al ejecutar el programa e introduciendo un numero entero, funciona correctamente,
pero al introducir otro tipo de variable, el resultado es 0, ya que no se almacena
ningun tipo de variable que no sea int.*/

cout<<"Introduzca un numero entero: ";

cin>>num;

cout<<"El numero entero es: "<<num;
return 0;
}
