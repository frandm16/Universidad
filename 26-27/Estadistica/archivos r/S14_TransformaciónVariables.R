library(tidyverse)

# Cargamos nuestro Tibble utilizando read_csv, especificando los tipos de las columnas
dfMec <- read_csv('c:/data/dfMec.csv',
                  col_types=cols(
                    .default = col_double(),
                    sexo = col_factor(),
                    emailMovil = col_factor(),
                    academia = col_factor(),
                    nivEstPad = col_factor() ) )

# Creamos una nueva columna
df1 <- dfMec %>% transmute(notaFinal, notaBach, logNotaBach=log(notaBach))

# Modelo de la variable original
mod1 <- lm(notaFinal ~ notaBach, df1)
summary(mod1)$r.squared

# Modelo de la variable transformada
mod2 <- lm(notaFinal ~ logNotaBach, df1)
summary(mod2)$r.squared

# R hace lo mismo por nosotros sin necesidad de que creemos la nueva columna
mod3 <- lm(notaFinal ~ log(notaBach), dfMec)
summary(mod3)$r.squared

# Podemos tb hacer una transformaci?n en la variable explicada
mod4 <- lm(sqrt(notaFinal) ~ log(notaBach), dfMec)
summary(mod4)$r.squared

# Aqu? vemos que esto es equivalente a hacer un ajuste polin?mico de segundo grado
#sqrt(Y) = a + bX
#Y = (a + bx)^2
#Y = a1 + b1x + b2x^2

# Podemos definir nuevas variables a partir de varias variables originales
mod5 <- lm(notaFinal ~ log(notaBach + notaSel), dfMec)
summary(mod5)$r.squared

# x1:x2 significa la interacci?n entre dos variables y en el caso de variables cuantitativas significa 
#   usar una variable predictora que sea su producto
mod6 <- lm(notaFinal ~ notaBach:notaSel, dfMec)
mod6
summary(mod6)$r.squared

# x1 * x2 es equivalente a x1 + x2 + x1:x2
mod7 <- lm(notaFinal ~ notaBach * notaSel, dfMec)
summary(mod7)$r.squared

# Como + y * tienen significados especiales, si queremos construir variables con estos operadores, 
#   tenemos que utilizar la funci?n I() para indicar expresiones literales.
mod8 <- lm(notaFinal ~ I(notaBach + notaSel), dfMec)
mod8
summary(mod8)$r.squared

# Esto es equivalente a mod6
mod9 <- lm(notaFinal ~ I(notaBach * notaSel), dfMec)
mod9
summary(mod8)$r.squared

# Aqu? vemos la interacci?n entre una variable cualitativa y una cuantitativa
# De esta manera le asignamos una pendiente diferente a cada subconjunto de individuos con un nivEstPad diferente
# Es decir, 5 rectas con pendientes diferentes, aunque con el mismo Intercept.
mod10 <- lm(notaFinal ~ notaBach:nivEstPad, dfMec)
mod10

# Esto otro, sin embargo, creaba 5 rectas paralelas. 
# Es decir, con la misma pendiente, pero con intercepts diferentes.
mod11 <- lm(notaFinal ~ notaBach + nivEstPad, dfMec)
mod11

# Si hacemos esto otro, tendremos 5 rectas totalmente diferentes. 
# Cada una con su propio intercept y pendiente.
lm(notaFinal ~ notaBach * nivEstPad, dfMec)

# Intentamos ahora el modelo polin?mico de segundo grado.
# Es fundamental el uso de I(), sino lo interpretar?a como las interacciones entre una variable y ella misma
mod12 <- lm(notaFinal ~ I(notaBach ^ 2) + notaBach, dfMec)
summary(mod12)$r.squared

# Vemos que el R2 sale diferente al de mod4.
# Esto es algo importante a tener en cuenta cuando hagamos transformaciones en la variable explicada. 
# En este caso, cambia la escala en la que se miden los errores, por tanto no podemos comparar directamente
#  los modelos con el R2 que nos proporciona lm.
# Tendr?amos que hacerlo con un R2 calculado manualmente midiendo directamente el error con la variable y original, 
#   no la transformada.
