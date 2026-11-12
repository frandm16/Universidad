library(tidyverse)

# Cargamos nuestro Tibble utilizando read_csv de Tidyverse
dfMec <- read_csv('c:/data/dfMec.csv')

# Creamos las matrices para las ecuaciones normales
M <- dfMec %>% transmute(x0=1, x1=notaBach, x2=notaSel) %>% as.matrix
Y <- matrix(dfMec$notaFinal)
R <- t(M) %*% M
S <- t(M) %*% Y

# Resolvemos el sistema para obtener los coeficientes de la regesi?n lineal
solve(R, S)

# Hacemos lo mismo con la funci?n lm
mod <- lm(notaFinal ~ notaBach + notaSel, dfMec)
summary(mod)

# Estas son las predicciones del modelo
predict.lm(mod)

# C?lculo del coeficiente de determinaci?n
dfMec %>% 
  summarise(MSE = mean((notaFinal - predict.lm(mod)) ^ 2), varY = mean(notaFinal ^ 2) - mean(notaFinal) ^ 2, R2 = 1 - MSE / varY)

# Un modelo con m?s variables predictoras
mod2 <- lm(notaFinal ~ notaBach + notaSel + minutTeor + minutPrac, dfMec)
summary(mod2)

# Un modelo sin Intercept o t?rmino independiente
mod0 <- lm(notaFinal ~ 0 + notaBach + notaSel, dfMec)
mod0
