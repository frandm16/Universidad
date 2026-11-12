x <- c(1, 2, 5)

# Definiendo un vector con nombres para sus elementos
x <- c(a=1, b=2, c=5)

# Accediendo a los elementos de un vector por su nombre
x[c("c", "b")]

# Recuperando los nombres del vector
names(x)

# Modificando los nombres del vector
names(x)<-c("n1", "n2", "n3")

# Definiendo una lista con nombres para sus elementos
resultado <- list(datos=c(1, 2, 3, 6), total=12, nombre="hijos")

# Accediendo a los elementos de una lista por índice y por nombre
resultado[[1]]
resultado[1]
resultado[["datos"]]
resultado["datos"]
resultado$datos

# Recuperando los nombres de la lista
names(resultado)
