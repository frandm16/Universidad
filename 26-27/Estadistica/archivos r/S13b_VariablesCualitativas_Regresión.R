library(tidyverse)

# Cargamos nuestro Tibble utilizando read_csv, especificando los tipos de las columnas
dfMec <- read_csv('c:/data/dfMec.csv',
                  col_types=cols(
                    .default = col_double(),
                    sexo = col_factor(),
                    emailMovil = col_factor(),
                    academia = col_factor(),
                    nivEstPad = col_factor()
                  ))

# Una regresi?n lineal de una variable cualitativa dicot?mica
mod <- lm(notaFinal ~ sexo, dfMec)
# Vemos que la predicci?n que obtenemos es asignar un valor constante a cada uno de los individuos del mismo sexo
mod
# Vemos que obtenemos un R2 muy bajo
dfMec %>% summarise(MSE  = mean((notaFinal - predict.lm(mod)) ^ 2), 
                    varY = mean(notaFinal ^ 2) - mean(notaFinal) ^ 2,
                    R2   = 1 - MSE / varY)

# Una regresi?n lineal de una variable cualitativa que toma 5 valores
mod <- lm(notaFinal ~ nivEstPad, dfMec)
dfMec$nivEstPad

# Vemos que se han creado 4 variables dummy
mod

dfMec %>% summarise(MSE=mean((notaFinal - predict.lm(mod))^2), 
                    varY=mean(notaFinal ^ 2)- mean(notaFinal) ^ 2,
                    R2=1 - MSE / varY)

# Una regresi?n con una variable cuantitativa y una cualitativa
mod <- lm(notaFinal~notaBach + nivEstPad, dfMec)
# Algo mejor
dfMec %>% summarise(MSE=mean((notaFinal - predict.lm(mod))^2), 
                    varY=mean(notaFinal ^ 2)- mean(notaFinal) ^ 2,
                    R2=1 - MSE / varY)

# Podemos ver la nube de puntos con la variable cuantitativa
plot(dfMec$notaBach, dfMec$notaFinal, col=2)

# Y ahora vemos la nube de puntos con la variable cuantitativa y la variable cualitativa con color
plot(dfMec$notaBach, dfMec$notaFinal, col=(dfMec$nivEstPad))
coefs <- mod$coefficients
walk2(coefs[1] + c(0, coefs[3:6]), 1:5, ~abline(.x, coefs[2], col=.y))

