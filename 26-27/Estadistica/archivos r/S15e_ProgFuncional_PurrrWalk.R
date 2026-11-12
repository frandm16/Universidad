library(tidyverse)

# Cargamos nuestro Tibble utilizando read_csv, especificando los tipos de las columnas
dfMec <- read_csv('c:/data/dfMec.csv',
                  col_types=cols(
                    .default = col_double(),
                    sexo = col_factor(),
                    emailMovil = col_factor(),
                    academia = col_factor(),
                    nivEstPad = col_factor() ) )

# Esto sería un ejemplo de usar map para leer una serie de data frames de disco y meterlos todos en una lista
dfs <- list("data1.csv", "data2.csv") %>% map(read_csv)

# Y esto sería un uso típico de walk para grabar unos data frames a disco
# Aquí usamos walk en lugar de map, porque estamos haciendo un proceso de salida a disco, sin devolver ningún resultado
list(list(df1, "data1.csv"), list(df2, "data2.csv")) %>% walk2(write_csv)


# Hacemos una función que haga un ajuste lineal
# La hemos preparado para que devuelva los dos nombres de variable y el modelo
linearAdjust <- function(df, y, x) {
  list(x=x, y=y, mod=lm(str_c(y, "~", x), df))
}

# Y esto es una función que recibe una lista con tres argumentos:
#   Nombre de las dos variables y modelo lineal
# Y graba en disco un gráfico de dispersión con la recta de regresión sobreimpresa
dibujarModelos <- function(mod) {
  jpeg(str_c("c:/data/plots/grafico_", mod$x, ".jpeg"))
  plot(dfMec[[mod$x]], dfMec[[mod$y]])
  abline(mod$mod)
  dev.off()
}


# Generamos los 22 modelos de una variable
varPred <- names(dfMec[-1])
mods <- varPred %>% map(~linearAdjust(dfMec, "notaFinal", .))
# Y generamos los 22 gráficos
mods %>% walk(dibujarModelos)

# Recibimos un warning para la variable nivEstPad, ya que es una variable cualitativa de 5 niveles, y por tanto lo
#   que se genera no es una recta.
# Lo que vemos dibujado por abline en ese caso no es correcto.
# En los demás casos sí vemos un gráfico correcto.
# Dejamos como ejercicio modificar la llamada a la función plot para que indique correctamente los nombres de las 
#  variables en los ejes x e y.





