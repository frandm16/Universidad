#include <iostream>

using namespace std;

int main()
{
    int num;
    cout << "Introduzca un numero: ";
    cin >> num;

    for (int i = 0; i < num; i++)
    {
        for (int j = 0; j < num; j++)
        {
            cout << "+";
        }
        cout << endl;
    }
    cout << endl
         << "-----" << endl;

    for (int i = 0; i < num; i++)
    {
        for (int j = 0; j < num; j++)
        {
            cout << "+";
            num--;
        }
        cout << endl;
    }
}