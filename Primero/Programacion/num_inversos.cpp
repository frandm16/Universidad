#include <iostream>

using namespace std;

int main()
{

    int num;
    cout << "Introduzca un numero: ";
    cin >> num;

    while (num >= 1)
    {

        num = num - 1;
        cout << num << " ";
    }
}