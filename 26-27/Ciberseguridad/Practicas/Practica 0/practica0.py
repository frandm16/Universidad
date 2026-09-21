# PRACTICA 0

'''
a) Implementar la función de descifrado César para alfabeto inglés en mayúsculas, que descifre los textos
cifrados creados por el código anterior.
'''

def descifradoCesarAlfabetoInglesMAY(cadena):
    """Devuelve un descifrado Cesar tradicional (+3)"""
    # Definir la nueva cadena resultado
    resultado = ''
    # Realizar el "cifrado", sabiendo que A = 65, Z = 90, a = 97, z = 122
    i = 0
    while i < len(cadena):
        # Recoge el caracter a cifrar
        ordenClaro = ord(cadena[i])
        ordenCifrado = 0
        # Cambia el caracter a cifrar
        if (ordenClaro >= 65 and ordenClaro <= 90):
            ordenCifrado = (((ordenClaro - 65) - 3) % 26) + 65
        # Añade el caracter cifrado al resultado
        resultado = resultado + chr(ordenCifrado)
        i = i + 1
    # devuelve el resultado
    return resultado

'''
b) Modificar las funciones de cifrado y descifrado para que soporten tanto letras en mayúsculas ( A..Z ) como
en minúsculas ( a..z ) en el alfabeto inglés.
'''

def cifradoCesarAlfabetoInglesMINyMAY(cadena):
    """Devuelve un cifrado Cesar tradicional (+3)"""
    # Definir la nueva cadena resultado
    resultado = ''
    # Realizar el "cifrado", sabiendo que A = 65, Z = 90, a = 97, z = 122
    i = 0
    while i < len(cadena):
        # Recoge el caracter a cifrar
        ordenClaro = ord(cadena[i])
        ordenCifrado = 0
        # Cambia el caracter a cifrar
        if (ordenClaro >= 65 and ordenClaro <= 90):
            ordenCifrado = (((ordenClaro - 65) + 3) % 26) + 65
        elif(ordenClaro >= 97 and ordenClaro <= 122):
            ordenCifrado = (((ordenClaro - 97) + 3) % 26) + 97
        # Añade el caracter cifrado al resultado
        resultado = resultado + chr(ordenCifrado)
        i = i + 1
    # devuelve el resultado
    return resultado

def descifradoCesarAlfabetoInglesMINyMAY(cadena):
    """Devuelve un descifrado Cesar tradicional (+3)"""
    # Definir la nueva cadena resultado
    resultado = ''
    # Realizar el "cifrado", sabiendo que A = 65, Z = 90, a = 97, z = 122
    i = 0
    while i < len(cadena):
        # Recoge el caracter a cifrar
        ordenClaro = ord(cadena[i])
        ordenCifrado = 0
        # Cambia el caracter a cifrar
        if (ordenClaro >= 65 and ordenClaro <= 90):
            ordenCifrado = (((ordenClaro - 65) - 3) % 26) + 65
        elif(ordenClaro >= 97 and ordenClaro <= 122):
            ordenCifrado = (((ordenClaro - 97) - 3) % 26) + 97
        # Añade el caracter cifrado al resultado
        resultado = resultado + chr(ordenCifrado)
        i = i + 1
    # devuelve el resultado
    return resultado

'''
c) Modificar las funciones de cifrado y descifrado para que soporten el cifrado César generalizado — C: M →
M + i (mod 26) .
'''

def cifradoCesarAlfabetoInglesGeneralizadoMINyMAY(cadena, n):
    """Devuelve un cifrado Cesar Generalizado"""
    # Definir la nueva cadena resultado
    resultado = ''
    # Realizar el "cifrado", sabiendo que A = 65, Z = 90, a = 97, z = 122
    i = 0
    while i < len(cadena):
        # Recoge el caracter a cifrar
        ordenClaro = ord(cadena[i])
        ordenCifrado = 0
        # Cambia el caracter a cifrar
        if (ordenClaro >= 65 and ordenClaro <= 90):
            ordenCifrado = (((ordenClaro - 65) + n) % 26) + 65
        elif(ordenClaro >= 97 and ordenClaro <= 122):
            ordenCifrado = (((ordenClaro - 97) + n) % 26) + 97
        # Añade el caracter cifrado al resultado
        resultado = resultado + chr(ordenCifrado)
        i = i + 1
    # devuelve el resultado
    return resultado

def descifradoCesarAlfabetoInglesGeneralizadoMINyMAY(cadena, n):
    """Devuelve un descifrado Cesar Generalizado"""
    # Definir la nueva cadena resultado
    resultado = ''
    # Realizar el "cifrado", sabiendo que A = 65, Z = 90, a = 97, z = 122
    i = 0
    while i < len(cadena):
        # Recoge el caracter a cifrar
        ordenClaro = ord(cadena[i])
        ordenCifrado = 0
        # Cambia el caracter a cifrar
        if (ordenClaro >= 65 and ordenClaro <= 90):
            ordenCifrado = (((ordenClaro - 65) - n) % 26) + 65
        elif(ordenClaro >= 97 and ordenClaro <= 122):
            ordenCifrado = (((ordenClaro - 97) - n) % 26) + 97
        # Añade el caracter cifrado al resultado
        resultado = resultado + chr(ordenCifrado)
        i = i + 1
    # devuelve el resultado
    return resultado


cadena = "KROD"
print("a) Descifrado Cesar para Mayúsculas: " , cadena , " -> " , descifradoCesarAlfabetoInglesMAY(cadena))

cadena='Dorado'
print("b) Cifrado Cesar para Mayúsculas y Minúsculas: " , cadena , " -> " , cifradoCesarAlfabetoInglesMINyMAY(cadena))

cadena='Grudgr'
print("b) Descrifrado Cesar para Mayúsculas y Minúsculas: " , cadena , " -> " , descifradoCesarAlfabetoInglesMINyMAY(cadena))

cadena='Dorado'
print("c) Cifrado cesar generalizado para N=10: " , cadena , " -> " , cifradoCesarAlfabetoInglesGeneralizadoMINyMAY(cadena, 10))

cadena='Nybkny'
print("c) Descifrado cesar generalizado para N=10: " , cadena , " -> " , descifradoCesarAlfabetoInglesGeneralizadoMINyMAY(cadena, 10))