# Francisco Dorado Maldonado Ing. Informatica 3B
import funciones_rsa as RSA
import funciones_aes as AES
import socket_class as TCP

# EJERCICIO 1

'''
c. Cargar la clave privada de Alice y la clave pública de Bob.
'''

clavePrivadaAlice = RSA.cargar_RSAKey_Privada("AlicePrivada.txt", "AlicePassword")
clavePublicaBob = RSA.cargar_RSAKey_Publica("BobPublica.txt")


'''
d. Cifrar un array de 16 bytes ( K1 ) utilizando la clave de Bob.
'''

k1 = AES.crear_AESKey()
cifradoK1 = RSA.cifrarRSA_OAEP(k1, clavePublicaBob)


'''
e. Firmar dicho array de 16 bytes ( K1 ) utilizando la clave de Alice.
'''

firmaK1 = RSA.firmarRSA_PSS(k1, clavePrivadaAlice)


'''
f. Enviar el cifrado y la firma usando un socket cliente de la clase SOCKET_SIMPLE_TCP() , descrita en
los apéndices.
'''

socketCliente = TCP.SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socketCliente.conectar()
socketCliente.enviar(cifradoK1)
socketCliente.enviar(firmaK1)


# EJERCICIO 2

'''
b. Alice recibirá el texto cifrado con la clave simétrica y la firma digital a través del socket ya abierto en
el apartado 1. Después, descifrará la cadena de caracteres y la mostrará por pantalla tras comprobar la
validez de la firma digital.
'''

nonceBob = socketCliente.recibir()
cifradoMensajeBob = socketCliente.recibir()
firmaMensajeBob = socketCliente.recibir()

descifradorAlice = AES.iniciarAES_CTR_descifrado(k1, nonceBob)
mensajeBob = AES.descifrarAES_CTR(descifradorAlice, cifradoMensajeBob)

if RSA.comprobarRSA_PSS(mensajeBob, firmaMensajeBob, clavePublicaBob):
    print("Mensaje de Bob: ", mensajeBob.decode("utf-8"))
else:
    print("Firma del mensaje de Bob no valida")


'''
c. Finalmente, Alice repetirá los pasos a) y b) en reverso: enviando a Bob la cadena cifrada Hola Bob
con K1 en AES-CTR-128, junto con la firma digital de dicha cadena, y Bob se encargará de descifrar y
comprobar la firma digital.
'''

(cifradorAlice, nonceAlice) = AES.iniciarAES_CTR_cifrado(k1)
cifradoMensajeAlice = AES.cifrarAES_CTR(cifradorAlice, "Hola Bob".encode("utf-8"))
firmaMensajeAlice = RSA.firmarRSA_PSS("Hola Bob".encode("utf-8"), clavePrivadaAlice)

socketCliente.enviar(nonceAlice)
socketCliente.enviar(cifradoMensajeAlice)
socketCliente.enviar(firmaMensajeAlice)

socketCliente.cerrar()
