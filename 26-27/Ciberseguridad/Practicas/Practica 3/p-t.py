# Francisco Dorado Maldonado Ing. Informatica
from Crypto.Hash import SHA256, HMAC
import base64
import json
import sys
from socket_class import SOCKET_SIMPLE_TCP
import funciones_aes

# Paso 0: Crea las claves que T comparte con B y A
##################################################

# Crear Clave KAT, guardar a fichero
KAT = funciones_aes.crear_AESKey()
FAT = open("KAT.bin", "wb")
FAT.write(KAT)
FAT.close()

# Crear Clave KBT, guardar a fichero
KBT = funciones_aes.crear_AESKey()
FBT = open("KBT.bin", "wb")
FBT.write(KBT)
FBT.close()

# Paso 1) B->T: KBT(Bob, Nb) en AES-GCM
#######################################

# Crear el socket de escucha de Bob (5551)
print("Esperando a Bob...")
socket_Bob = SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socket_Bob.escuchar()

# Crea la respuesta para B y A: K1 y K2
K1 = funciones_aes.crear_AESKey()
K2 = funciones_aes.crear_AESKey()

# Recibe el mensaje
cifrado = socket_Bob.recibir()
cifrado_mac = socket_Bob.recibir()
cifrado_nonce = socket_Bob.recibir()

# Descifro los datos con AES GCM
datos_descifrado_ET = funciones_aes.descifrarAES_GCM(KBT, cifrado_nonce, cifrado, cifrado_mac)

# Decodifica el contenido: Bob, Nb
json_ET = datos_descifrado_ET.decode("utf-8" ,"ignore")
print("B->T (descifrado): " + json_ET)
msg_ET = json.loads(json_ET)

# Extraigo el contenido
t_bob, t_nb = msg_ET
t_nb = bytearray.fromhex(t_nb)

# Paso 2) T->B: KBT(K1, K2, Nb) en AES-GCM
##########################################

# Enviar K1, K2 y Nb a Bob
msg_TB = [K1.hex(), K2.hex(), t_nb.hex()]
json_TB = json.dumps(msg_TB)

aes_engine = funciones_aes.iniciarAES_GCM(KBT)
cifrado, mac, nonce = funciones_aes.cifrarAES_GCM(aes_engine, json_TB.encode("utf-8"))

socket_Bob.enviar(cifrado)
socket_Bob.enviar(mac)
socket_Bob.enviar(nonce)

# Cerramos conexion con Bob
socket_Bob.cerrar()

# Paso 3) A->T: KAT(Alice, Na) en AES-GCM
#########################################

print("Esperando a Alice...")
socket_Alice = SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socket_Alice.escuchar()

cifrado = socket_Alice.recibir()
mac = socket_Alice.recibir()
nonce = socket_Alice.recibir()

datos_descifrados = funciones_aes.descifrarAES_GCM(KAT, nonce, cifrado, mac)
if not datos_descifrados:
    print("Error al descifrar mensaje de Alice")
    socket_Alice.cerrar()
    sys.exit()

json_AT = datos_descifrados.decode("utf-8", "ignore")
print("A->T (descifrado): " + json_AT)
msg_AT = json.loads(json_AT)

t_alice, t_na = msg_AT
t_na = bytearray.fromhex(t_na)

# Paso 4) T->A: KAT(K1, K2, Na) en AES-GCM
##########################################

# Enviar K1, K2 y Na a Alice
msg_TA = [K1.hex(), K2.hex(), t_na.hex()]
json_TA = json.dumps(msg_TA)

aes_engine = funciones_aes.iniciarAES_GCM(KAT)
cifrado, mac, nonce = funciones_aes.cifrarAES_GCM(aes_engine, json_TA.encode("utf-8"))

socket_Alice.enviar(cifrado)
socket_Alice.enviar(mac)
socket_Alice.enviar(nonce)

socket_Alice.cerrar()