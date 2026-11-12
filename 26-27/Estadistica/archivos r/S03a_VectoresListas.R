# Un dato at?mico es lo mismo que un vector de longitud 1
x <- 5

# Definici?n de vectores y composici?n
x <- c(1, 2)
y <- c(3, 6, 7, 11)
z <- c(x, y)

# Vectores l?gicos y de cadenas
a <- c(T, F, T)
b <- c("dsd", "fdf")

# Secuencias
1:10
-10:-1
seq(1, 10, 2)

# Subsetting en sus dos formas
z <- 11:20
z[c(3, 4, 6)]
z[c(T, T, F, F, F, F, T, T, T, T)]

# Cuando pido ?ndices que no existen recibo NA
x[2:5]


# Definici?n de listas
ll <- list(2, 3, "vc", "dfs", T, F)

# Esto es una sublista
ll[1]

# Esto extrae un valor de una lista
ll[[1]]
