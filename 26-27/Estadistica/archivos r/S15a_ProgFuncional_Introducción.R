# Una de las reglas de oro más básicas en programación es:
#      NO REPETIR NUNCA CÓDIGO

# Si tengo un código como éste:
a <- x * 2 + 5
b <- y * 2 + 5

# Puedo crear una función y llamarla cada vez con un argumento diferente:
ff <- function(x) {
  x * 2 + 5
}

a <- ff(x)
b <- ff(y)


# Todo en R es una función
# incluidos los operadores:
2 + 3
`+`(2, 3)


# y los comandos también:
if (2 == 2) print("hola")
`if`(2 == 2, print("hola"))

for (i in 1:4) print (i)
`for`(i, 1:4, print(i))


# Ejemplo clásico de programación recursiva:
gcd <- function(a, b) {
  if (b) gcd(b, a %% b) else a
}

gcd(30, 66)


# A veces tengo código similar, pero no exactamente igual. 
# En este caso, tampoco quiero repetir el código.
# Esto se puede solucionar con programación orientada a objetos (OOP) o con programación funcional (FP)
x <- 1:10

y <- x + 2
m <- mean(y)
median(x[x > m])

y <- x + 2
m <- exp(mean(log(y)))
median(x[x > m])

# La solución con programación funcional es pasar como argumento la función que varia:
oper <- function(x, f) {
  y <- x + 2
  m <- f(y)
  median(x[x > m])
}

oper(x, mean)
oper(x, function(x) exp(mean(log(x))))


