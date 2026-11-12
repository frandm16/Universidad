library(tidyverse)

# Cargamos nuestro Tibble utilizando read_csv de Tidyverse
dfMec <- read_csv('c:/data/dfMec.csv')

# Extraemos un nuevo tibble con las variables que vamos a trabajar. 
# NO es necesario y normalmente no se har?a, pero aqu? lo hacemos por claridad.
df1 <- dfMec %>% select(notaFinal, notaBach)

# Calculamos covarianza y varianzas manualmente
covXY <- mean(df1$notaFinal * df1$notaBach) - mean(df1$notaFinal) * mean(df1$notaBach)
varX  <- mean(df1$notaBach ^ 2) - mean(df1$notaBach) ^ 2
varY  <- mean(df1$notaFinal ^ 2) - mean(df1$notaFinal) ^ 2

# Esta es la pendiente de la recta calculada directamente
covXY / varX

# Y este es el centro de gravedad de la distribuci?n, por la que pasa la recta
mean(df1$notaFinal)
mean(df1$notaBach)

# Por tanto, ?sta es la recta:
# y - 4.30 = 0.21 * (x - 7.08)

# Aqu? calculamos el termino independiente o Intercept
mean(df1$notaFinal) - covXY / varX * mean(df1$notaBach)


# coef corr lineal de Pearson r
covXY / sqrt(varX * varY)

# coef corr lineal de Pearson r^2
covXY ^ 2 / varX / varY

# Ahora lo calculamos con lm
mod <- lm(notaFinal ~ notaBach, dfMec)

# Extraemos los coeficientes y el coeficiente de determinaci?n
mod$coefficients
summary(mod)$r.squared

# Vemos que efectivamente los datos se parecen poco a una recta
plot(df1$notaBach, df1$notaFinal)
abline(mod, col="red")

# Aqu? podemos ver como nuestro modelo lineal predice los datos
df1$notaPredicha <- predict.lm(mod)

# Esto es un tibble con los datos predichos y el error cometido
df2 <- df1 %>% 
  mutate(notaPredicha=predict.lm(mod), err=notaFinal - notaPredicha)

# Aqu? sumarizamos el MSE, la varianza de la variable predicha y el coeficiente de determinaci?n R2:
df1 %>% 
  mutate(notaPredicha=predict.lm(mod), err=notaFinal - notaPredicha) %>% 
  summarise(MSE=mean(err ^ 2), varY=mean(notaFinal ^ 2) - mean(notaFinal) ^ 2, R2=1 - MSE / varY ) %>% 
  mutate(RMSE=sqrt(MSE), stdY=sqrt(varY))

# Vemos que el ajuste ordinario por minimos cuadrados el coeficiente de determinaci?n (R^2) coincide con 
#   el coeficiente de regresi?n lineal de Pearson al cuadrado (r^2)
