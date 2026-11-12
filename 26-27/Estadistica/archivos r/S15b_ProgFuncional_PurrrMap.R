library(tidyverse)

# Cargamos nuestro Tibble utilizando read_csv, especificando los tipos de las columnas
dfMec <- read_csv('c:/data/dfMec.csv',
                  col_types=cols(
                    .default = col_double(),
                    sexo = col_factor(),
                    emailMovil = col_factor(),
                    academia = col_factor(),
                    nivEstPad = col_factor() ) )

# Aplicaciones básicas de map, pero que realmente no son necesarias
map(1:10, cos)
cos(1:10)

map_dbl(1:10, `+`, 23)
(1:10) + 23

# Aplicamos map a una lista de vectores. Aquí sí que no podemos hacerlo de otra manera
list(1:10, 11:20, 21:30) %>% map(mean)

# Nos aprovechamos de que un data frame es una lista de vectores columna
# Aplicamos mean, pero en las columnas no numéricas me da warnings
dfMec %>% map(mean)

# Función is.numeric: me dice si un vector es numérico o no
is.numeric(dfMec$notaFinal)
is.numeric(dfMec$sexo)

# Lo aprovecho con la función keep, para quedarme sólo con las columnas que son numéricas
dfMec %>% keep(is.numeric) %>% map_dbl(mean)


# Defino una función que me hace un ajuste lineal
linearAdj <- function(df, y, x) {
  lm(str_c(y, "~", x), df)
}

# Cuatro maneras equivalentes de llamar a map para que me prueba a hacer ajustes lineales simples con todas las 
#  variables del Data Frame
# Lo que estoy haciendo es generar una lista de 22 modelos de regresión lineal simple
mods <- names(dfMec)[-1] %>% map(linearAdj, df=dfMec, y="notaFinal")
mods <- map(names(dfMec)[-1], linearAdj, df=dfMec, y="notaFinal")
mods <- names(dfMec)[-1] %>% map( ~linearAdj(df=dfMec, y="notaFinal", .) )
mods <- names(dfMec)[-1] %>% map( function(x) linearAdj(df=dfMec, y="notaFinal", x) )

# Y otra manera equivalente que no usa la definición previa de función, sino que directamente la transcribe
mods <- names(dfMec)[-1] %>% map( ~lm(str_c("notaFinal", "~", .), dfMec) )

# lm permite recibir la expresión de regresión como formula o como string
lm(notaFinal ~ notaBach, dfMec)
lm("notaFinal ~ notaBach", dfMec)

# Pero si queremos, lo podemos pasar a fórmula con as.formula
lm(as.formula("notaFinal ~ notaBach"), dfMec)


# Otro uso de map, cuando le paso una lista de listas.
# Le digo que me extraiga un elemento de cada elemento de la lista, especificando su índice o su nombre
list(1:10, 11:20, 21:30) %>% map(2)


# Extraigo de los 22 modelos de regresión cuál es el coeficiente de determinación de cada uno
mods %>% map(summary) %>% map_dbl("r.squared")

