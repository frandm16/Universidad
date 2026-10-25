# Francisco Dorado Maldonado Ing. Informatica 3B
import funciones_rsa as RSA
import funciones_aes as AES
import socket_class as TCP

# EJERCICIO 1

'''
g. Cargar la clave privada de Bob y la clave pública de Alice.
'''

clavePrivadaBob = RSA.cargar_RSAKey_Privada("BobPrivada.txt", "BobPassword")
clavePublicaAlice = RSA.cargar_RSAKey_Publica("AlicePublica.txt")


'''
h. Recibir el texto cifrado y la firma digital a través del socket servidor de la clase
SOCKET_SIMPLE_TCP() , descrita en los apéndices.
'''

socketServidor = TCP.SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socketServidor.escuchar()

cifradoK1 = socketServidor.recibir()
firmaK1 = socketServidor.recibir()


'''
i. Descifrar el array de 16 bytes ( K1 ) y mostrarlo por pantalla.
'''

k1 = RSA.descifrarRSA_OAEP(cifradoK1, clavePrivadaBob)
print("Array k1: ",k1)


'''
j. Comprobar la validez de la firma digital.
'''

if RSA.comprobarRSA_PSS(k1, firmaK1, clavePublicaAlice):
    print("Firma de K1 valida")
else:
    print("Firma de K1 no valida")


# EJERCICIO 2

'''
a. Tras el último paso del apartado 1, Bob cifrará la cadena Hola Alice utilizando AES-CTR-128 con
la clave K1 , y firmará esa cadena con su clave privada. A continuación, enviará dicho cifrado y la firma
a través del socket ya abierto en el apartado 1.
'''

(cifradorBob, nonceBob) = AES.iniciarAES_CTR_cifrado(k1)
cifradoMensajeBob = AES.cifrarAES_CTR(cifradorBob, "Hola Alice".encode("utf-8"))
firmaMensajeBob = RSA.firmarRSA_PSS("Hola Alice".encode("utf-8"), clavePrivadaBob)

socketServidor.enviar(nonceBob)
socketServidor.enviar(cifradoMensajeBob)
socketServidor.enviar(firmaMensajeBob)


'''
c. Finalmente, Alice repetirá los pasos a) y b) en reverso: enviando a Bob la cadena cifrada Hola Bob
con K1 en AES-CTR-128, junto con la firma digital de dicha cadena, y Bob se encargará de descifrar y
comprobar la firma digital.
'''

nonceAlice = socketServidor.recibir()
cifradoMensajeAlice = socketServidor.recibir()
firmaMensajeAlice = socketServidor.recibir()

descifradorBob = AES.iniciarAES_CTR_descifrado(k1, nonceAlice)
mensajeAlice = AES.descifrarAES_CTR(descifradorBob, cifradoMensajeAlice)

if RSA.comprobarRSA_PSS(mensajeAlice, firmaMensajeAlice, clavePublicaAlice):
    print("Mensaje: ",mensajeAlice.decode("utf-8"))
else:
    print("Firma del mensaje de Alice no valida")

socketServidor.cerrar()
