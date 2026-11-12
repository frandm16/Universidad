library(tidyverse)

# Cargamos nuestro Tibble utilizando read_csv, especificando los tipos de las columnas
dfMec <- read_csv('c:/data/dfMec.csv',
                  col_types=cols(
                    .default = col_double(),
                    sexo = col_factor(),
                    emailMovil = col_factor(),
                    academia = col_factor(),
                    nivEstPad = col_factor() ) )

# Uso simple de reduce: Sumar todos los elementos de un vector. 
# Aquí reduce no es necesario, podríamos hacerlo con sum
reduce(1:10, `+`)
sum(1:10)


# Recordamos que tenemos dos maneras de calcular la varianza de un vector
x <- 100:140
# Como el momento central del orden 2:
mean((x - mean(x)) ^ 2)
# LA BUENA: Usando momentos ordinarios
mean(x ^ 2) - mean(x) ^ 2


# Vamos a suponer que tenemos cuatro ordenadores con datos en cada uno de ellos y queremos calcular la varianza
#   de manera distribuida
datos <- list(1:20, 21:50, 41:80, 61:120)


# Esto es una función que en cada uno de los ordenadores va a calcular estos acumulados 
#   que son los momentos ordinarios de orden 0, 1 y 2 multiplicados por N
sumas <- function(x) {
  c(length(x), sum(x), sum(x^2))
}

# Esto es una función que a partir de los tres acumulados calcula la varianza
sums2var <- function(sums) {
  sums[3] / sums[1] - (sums[2] / sums[1]) ^ 2
}

# Y esto es un ejemplo del paradigma MAP-REDUCE que se utiliza en el mundo Big Data para el procesado masivo de datos.
# Usamos MAP para calcular en paralelo cosas sobre cada uno de los conjuntos de datos.
# La operación MAP es una operación naturalmente paralelizable.
# Si la función llamada está libre de efectos colaterales, se puede ejecutar simultáneamente para cada elemento.
# Nos traemos el resultado a un ordenador central y hacemos REDUCE para "mezclar" los datos y obtener el resultado final.
# REDUCE no es paralelizable, pero es algo que hacemos al final con una cantidad muy reducida de información.
map(datos, sumas) %>% reduce(`+`) %>% sums2var

# Esto es lo mismo, pero implementando sums2var directamente en la línea
map(datos, sumas) %>% reduce(`+`) %>% { .[3] / .[1] - (.[2] / .[1]) ^ 2 }


# Por supuesto, como en este caso los datos están todos en nuestro ordenador, podríamos haber hecho esto otro.
# Aquí vemos otro ejemplo de uso de reduce.
x <- reduce(datos, c)
mean(x ^ 2) - mean(x) ^ 2


