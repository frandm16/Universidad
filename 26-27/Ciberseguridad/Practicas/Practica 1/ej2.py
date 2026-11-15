from Crypto.Random import get_random_bytes
from Crypto.Cipher import DES, AES
from Crypto.Util.Padding import pad,unpad
from Crypto.Util import Counter

# Datos necesarios
key = get_random_bytes(16) # Clave aleatoria de 128 bits
IV = get_random_bytes(16)  # IV aleatorio de 128 bits
BLOCK_SIZE_AES = 16 # Bloque de 128 bits
nonce = get_random_bytes(8) # nonce es BLOCK_SIZE_AES/2
mac_size=16
data = "Hola amigos de la seguridad".encode("utf-8")
print(data)

# CIFRADO ##########################################################################

# Creamos un mecanismo de cifrado DES en modo CBC con un vector de inicialización IV
cipher = AES.new(key, AES.MODE_GCM, nonce=nonce, mac_len=mac_size)

# Ciframos, haciendo que la variable “data” sea múltiplo del tamaño de bloque
ciphertext, mac_cifrado = cipher.encrypt_and_digest(pad(data,BLOCK_SIZE_AES))

# Mostramos el cifrado por pantalla en modo binario
print(ciphertext)

# DESCIFRADO #######################################################################

# Creamos un mecanismo de (des)cifrado DES en modo CBC con una inicializacion IV
# Ambos, cifrado y descifrado, se crean de la misma forma
decipher_des = AES.new(key, AES.MODE_GCM, nonce=nonce, mac_len=mac_size)

# Desciframos, eliminamos el padding, y recuperamos la cadena
new_data = unpad(decipher_des.decrypt_and_verify(ciphertext, mac_cifrado), BLOCK_SIZE_AES).decode("utf-8", "ignore")

# Imprimimos los datos descifrados
print(new_data)
