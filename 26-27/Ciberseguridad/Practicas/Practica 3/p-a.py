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

# Lee clave KAT
KAT = open("KAT.bin", "rb").read()

# Paso 3) A->T: KAT(Alice, Na) en AES-GCM
#########################################

print("Creando conexion con T...")
socket_t = SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socket_t.conectar()

# Crea los campos del mensaje
t_n_origen = get_random_bytes(16)

# Codifica el contenido y construye el mensaje JSON
msg_AT = []
msg_AT.append("Alice")
msg_AT.append(t_n_origen.hex())
json_AT = json.dumps(msg_AT)
print("A -> T (descifrado): " + json_AT)

# Cifra los datos con AES GCM
aes_engine = funciones_aes.iniciarAES_GCM(KAT)
cifrado, mac, nonce = funciones_aes.cifrarAES_GCM(aes_engine, json_AT.encode("utf-8"))

# Envia los datos
socket_t.enviar(cifrado)
socket_t.enviar(mac)
socket_t.enviar(nonce)

# Paso 4) T->A: KAT(K1, K2, Na) en AES-GCM
##########################################

cifrado = socket_t.recibir()
mac = socket_t.recibir()
nonce = socket_t.recibir()

datos_descifrados = funciones_aes.descifrarAES_GCM(KAT, nonce, cifrado, mac)
if not datos_descifrados:
    print("Error al descifrar mensaje de TTP")
    socket_t.cerrar()
    sys.exit()

json_TA = datos_descifrados.decode("utf-8", "ignore")
print("T -> A (descifrado): " + json_TA)
msg_TA = json.loads(json_TA)

k1_hex, k2_hex, na_hex = msg_TA
K1 = bytes.fromhex(k1_hex)
K2 = bytes.fromhex(k2_hex)
na_recibido = bytes.fromhex(na_hex)

# Comprobar nonce
if na_recibido != t_n_origen:
    print("Error: nonce de Alice no coincide")
    socket_t.cerrar()
    sys.exit()

socket_t.cerrar()

# Paso 5) A->B: KAB(Nombre) en AES-CTR con HMAC
###############################################

print("Creando conexion con Bob...")
socket_b = SOCKET_SIMPLE_TCP('127.0.0.1', 5553)
socket_b.conectar()

nombre = "Francisco"

# Cifrar nombre con AES-CTR
aes_enc, nonce_a = funciones_aes.iniciarAES_CTR_cifrado(K1)
cifrado_nombre = funciones_aes.cifrarAES_CTR(aes_enc, nombre.encode("utf-8"))

# Calcular HMAC sobre el texto cifrado
h = HMAC.new(K2, msg=cifrado_nombre, digestmod=SHA256)
mac_nombre = h.digest()

# Enviar paquete a Bob
paquete = json.dumps([cifrado_nombre.hex(), nonce_a.hex(), mac_nombre.hex()])
socket_b.enviar(paquete.encode("utf-8"))

# Paso 6) B->A: KAB(Apellido) en AES-CTR con HMAC
#################################################

paquete_b = socket_b.recibir()
cifrado_hex, nonce_hex, mac_hex = json.loads(paquete_b.decode("utf-8"))
cifrado = bytes.fromhex(cifrado_hex)
nonce = bytes.fromhex(nonce_hex)

# Comprobar HMAC
h = HMAC.new(K2, msg=cifrado, digestmod=SHA256)
try:
    h.hexverify(mac_hex)
except ValueError:
    print("Error: HMAC incorrecto")
    socket_b.cerrar()
    sys.exit()

# Descifrar apellido
aes_dec = funciones_aes.iniciarAES_CTR_descifrado(K1, nonce)
apellido = funciones_aes.descifrarAES_CTR(aes_dec, cifrado).decode("utf-8")
print("B -> A (apellido recibido):", apellido)

# Paso 7) A->B: KAB(END) en AES-CTR con HMAC
############################################

comando = "END"

# Cifrar comando END
aes_enc, nonce_end = funciones_aes.iniciarAES_CTR_cifrado(K1)
cifrado_end = funciones_aes.cifrarAES_CTR(aes_enc, comando.encode("utf-8"))

# Calcular HMAC
h = HMAC.new(K2, msg=cifrado_end, digestmod=SHA256)
mac_end = h.digest()

# Enviar a Bob y cerrar
paquete_end = json.dumps([cifrado_end.hex(), nonce_end.hex(), mac_end.hex()])
socket_b.enviar(paquete_end.encode("utf-8"))

print("Cerrando conexion...")
socket_b.cerrar()