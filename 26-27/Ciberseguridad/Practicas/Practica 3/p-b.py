# Francisco Dorado Maldonado Ing. Informatica
from Crypto.Hash import SHA256, HMAC
import base64
import json
import sys
from socket_class import SOCKET_SIMPLE_TCP
import funciones_aes
from Crypto.Random import get_random_bytes

# Paso 0: Inicializacion
########################

# Lee clave KBT
KBT = open("KBT.bin", "rb").read()

# Paso 1) B->T: KBT(Bob, Nb) en AES-GCM
#######################################

# Crear el socket de conexion con T (5551)
print("Creando conexion con T...")
socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socket.conectar()

# Crea los campos del mensaje
t_n_origen = get_random_bytes(16)

# Codifica el contenido (los campos binarios en una cadena) y contruyo el mensaje JSON
msg_TE = []
msg_TE.append("Bob")
msg_TE.append(t_n_origen.hex())
json_ET = json.dumps(msg_TE)
print("B -> T (descifrado): " + json_ET)

# Cifra los datos con AES GCM
aes_engine = funciones_aes.iniciarAES_GCM(KBT)
cifrado, cifrado_mac, cifrado_nonce = funciones_aes.cifrarAES_GCM(aes_engine, json_ET.encode("utf-8"))

# Envia los datos
socket.enviar(cifrado)
socket.enviar(cifrado_mac)
socket.enviar(cifrado_nonce)

# Paso 2) T->B: KBT(K1, K2, Nb) en AES-GCM
##########################################

cifrado = socket.recibir()
mac = socket.recibir()
nonce = socket.recibir()

datos_descifrados = funciones_aes.descifrarAES_GCM(KBT, nonce, cifrado, mac)
if not datos_descifrados:
    print("Error al descifrar mensaje de TTP")
    socket.cerrar()
    sys.exit()

json_TB = datos_descifrados.decode("utf-8", "ignore")
print("T -> B (descifrado): " + json_TB)
msg_TB = json.loads(json_TB)

k1_hex, k2_hex, nb_hex = msg_TB
K1 = bytes.fromhex(k1_hex)
K2 = bytes.fromhex(k2_hex)
nb_recibido = bytes.fromhex(nb_hex)

# Comprobar nonce
if nb_recibido != t_n_origen:
    print("Error: nonce de Bob no coincide")
    socket.cerrar()
    sys.exit()

socket.cerrar()

# Paso 5) A->B: KAB(Nombre) en AES-CTR con HMAC
###############################################

socket_alice = SOCKET_SIMPLE_TCP('127.0.0.1', 5553)
socket_alice.escuchar()

# Recibir paquete de Alice
paquete = socket_alice.recibir()
cifrado_hex, nonce_hex, mac_hex = json.loads(paquete.decode("utf-8"))
cifrado = bytes.fromhex(cifrado_hex)
nonce = bytes.fromhex(nonce_hex)

# Comprobar integridad con HMAC-SHA256 sobre el texto cifrado
h = HMAC.new(K2, msg=cifrado, digestmod=SHA256)
try:
    h.hexverify(mac_hex)
except ValueError:
    print("Error: HMAC incorrecto")
    socket_alice.cerrar()
    sys.exit()

# Descifrar nombre
aes_dec = funciones_aes.iniciarAES_CTR_descifrado(K1, nonce)
nombre = funciones_aes.descifrarAES_CTR(aes_dec, cifrado).decode("utf-8")
print("A -> B (nombre recibido):", nombre)

# Paso 6) B->A: KAB(Apellido) en AES-CTR con HMAC
#################################################

apellido = "Dorado Maldonado"

# Cifrar apellido
aes_enc, nonce_b = funciones_aes.iniciarAES_CTR_cifrado(K1)
cifrado_apellido = funciones_aes.cifrarAES_CTR(aes_enc, apellido.encode("utf-8"))

# Calcular HMAC
h = HMAC.new(K2, msg=cifrado_apellido, digestmod=SHA256)
mac_b = h.digest()

# Enviar a Alice
paquete_b = json.dumps([cifrado_apellido.hex(), nonce_b.hex(), mac_b.hex()])
socket_alice.enviar(paquete_b.encode("utf-8"))

# Paso 7) A->B: KAB(END) en AES-CTR con HMAC
############################################

# Recibir comando END
paquete = socket_alice.recibir()
cifrado_hex, nonce_hex, mac_hex = json.loads(paquete.decode("utf-8"))
cifrado = bytes.fromhex(cifrado_hex)
nonce = bytes.fromhex(nonce_hex)

# Comprobar HMAC
h = HMAC.new(K2, msg=cifrado, digestmod=SHA256)
try:
    h.hexverify(mac_hex)
except ValueError:
    print("Error: HMAC incorrecto")
    socket_alice.cerrar()
    sys.exit()

# Descifrar comando
aes_dec = funciones_aes.iniciarAES_CTR_descifrado(K1, nonce)
comando = funciones_aes.descifrarAES_CTR(aes_dec, cifrado).decode("utf-8")
print("A -> B (comando recibido):", comando)

# Comprobar que es el comando END
if comando == "END":
    print("Cerrando conexion...")
    socket_alice.cerrar()
else:
    print("Comando inesperado:", comando)
    socket_alice.cerrar()
    sys.exit()