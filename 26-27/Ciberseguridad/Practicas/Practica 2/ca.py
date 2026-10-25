# Francisco Dorado Maldonado Ing. Informatica 3B
import funciones_rsa as RSA

# EJERCICIO 1

'''
a. Crear una clave pública y una clave privada RSA de 2048 bits para Alice. Guardar cada clave en un fichero.
'''

claveAlice = RSA.crear_RSAKey()

RSA.guardar_RSAKey_Publica("AlicePublica.txt", claveAlice)
RSA.guardar_RSAKey_Privada("AlicePrivada.txt", claveAlice, "AlicePassword")


'''
b. Crear una clave pública y una clave privada RSA de 2048 bits para Bob. Guardar cada clave en un fichero.
'''

claveBob = RSA.crear_RSAKey()

RSA.guardar_RSAKey_Publica("BobPublica.txt", claveBob)
RSA.guardar_RSAKey_Privada("BobPrivada.txt", claveBob, "BobPassword")