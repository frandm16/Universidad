# Definición de matrices
A <- matrix(c(1, 2, 5, -1, 2, 11, 2, 3, 0), ncol=3)
B <- matrix(c(1, 2, 5, -1, 2, 11, 2, 3, 0), ncol=3, byrow=T)


# acceso a un elemento
A[2,3]

# Ojo: acceso como vector
A[4]

#producto como vectores
A * B

# Producto matricial
A %*% B

# Solución de sistema de ecuaciones lineal
b <- c(1, -1, 2)
solve(A, b)

# Resolver una ecuación matricial
M <- matrix(c(1, 12, 5, -21, 21, 1, -2, 3, 15), ncol=3)
solve(A, M)

# Inversa de una matriz
solve(A)

# Mala idea: resolver un sistema, utilizando la matriz inversa
solve(A)%*%b

