# Definición de una función en R y su asignación a una variable para poder usarla
ff <- function(x, y){
  x + 2 * y
}

# Lo podemos llamar con números o con vectores
ff(2,3)
ff(1:5, 11:15)

# Definición de función con valor por defecto en uno de sus argumentos
ff2 <- function(x, y=10){
  x + 2 * y
}

ff2(2,3)
ff2(2)

# Diversas formas de llamar a la función
ff(2, 100)
ff(100, 2)
ff(x=2, y=100)
ff(y=100, x=2)
ff(y=100, 2)

# sum admite un número indefinido de argumentos y se puede utilizar con un vector o con múltiples argumentos
sum(1, 3, 4, 23)
sum(c(1, 3, 4, 23))

# Pero ojo, mean no funciona igual. Hay que llamarlo con un vector
mean(1, 3, 4, 23)
mean(c(1, 3, 4, 23))


