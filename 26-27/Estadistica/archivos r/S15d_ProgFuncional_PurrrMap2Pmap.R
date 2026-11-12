library(tidyverse)

# Cargamos nuestro Tibble utilizando read_csv, especificando los tipos de las columnas
dfMec <- read_csv('c:/data/dfMec.csv',
                  col_types=cols(
                    .default = col_double(),
                    sexo = col_factor(),
                    emailMovil = col_factor(),
                    academia = col_factor(),
                    nivEstPad = col_factor() ) )

# recordamos el uso básico de map
map_dbl(1:10, cos)

# map2 es similar, pero con dos listas de la misma longitud y una función binaria
x <- runif(10, 0, 10)
y <- runif(10, 0, 10)
map2_dbl(y, x, atan2)
map2_dbl(x, y, max)

# Se puede hacer lo mismo con pmax. Ojo: max es diferente.
pmax(x, y)
max(x,y)

# pmap es similar, pero se recibe una lista de listas o un data frame.
# se ejecuta con una función que tenga tantos argumentos como columnas el data frame
# Para el ejemplo, primero seleccionamos las columnas numéricas utilizando select_if
df1 <- dfMec %>% select_if(is.numeric)
pmap(df1, sum)

# Vamos a utilizar str_c de la librería stringr
str_c("ho", "la")
# Opción con separador
str_c("ho", "la", "xx", "kssl", sep="+")
# Opción paralela con varios vectores de cadenas
str_c(c("hjoh", "fedf", "jkjllk"), c("aaa", "bbb", "cccc"), sep="+")
# Lo mismo, pero colapsando al final todos los vectores en uno usando el separador especificado en collapse
str_c(c("hjoh", "fedf", "jkjllk"), c("aaa", "bbb", "cccc"), sep="+", collapse='*' )


# Hacemos el producto cartesiano de varias opciones con crossing, tambien de tidyverse (tidyr)
crossing(x=1:5, y=11:13, z=21:22)

# Vamos a generar todas las regresiones lineales simples con interaccion de variables
# Para dos variables numéricas son su producto:
lm(notaFinal~notaBach:notaSel, dfMec)

# Si las dos variables son la misma, es lo mismo que usar esa variable de manera simple
lm(notaFinal~notaBach:notaBach, dfMec)

# Para variables cualitativas es el producto cartesiano de sus posibilidades
lm(notaFinal~sexo:nivEstPad, dfMec)

# Generamos en varPred un vector con todas las variables predictoras
varPred <- names(dfMec[-1])
# Y ahora generamos todas las combinaciones de interacciones de variables, usando pmap
varPredInter <- crossing(x=varPred, y=varPred) %>% pmap_chr(str_c, sep=':')

# Hacemos una función que haga un ajuste lineal
linearAdjust <- function(df, y, x) {
  lm(str_c(y, "~", x), df)
}

# Y ahora generamos los 22 modelos de una variable
mods1 <- varPred %>% map(~linearAdjust(dfMec, "notaFinal", .))
# Y los 484 modelos de la interacción de dos variables
mods2 <- varPredInter %>% map(~linearAdjust(dfMec, "notaFinal", .)) 

# Y ahora con map vemos sus coeficientes de determinación
mods1 %>% map(summary) %>% map_dbl("r.squared")
mods2 %>% map(summary) %>% map_dbl("r.squared")

