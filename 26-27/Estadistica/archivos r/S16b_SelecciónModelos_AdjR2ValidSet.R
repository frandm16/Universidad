library(tidyverse)

###########################
# Funciones
###########################

# Esto es una funci?n para separar nuestro data frame original en dos: uno de entrenamiento y otro de testeo,
#   en la proporci?n que nos indica p
separarSets <- function(df, p1, p2) {
  rDf    <- 1:nrow(df)
  rTrain <- sample(rDf, p1 * length(rDf))
  rTemp  <- setdiff(rDf, rTrain)
  rTest  <- sample(rTemp, p2 * length(rTemp))
  rValid <- setdiff(rTemp, rTest)

  list(train=df[rTrain,], test=df[rTest,], valid=df[rValid,])  
}


# Nuestra funci?n b?sica de ajuste lineal.
linearAdjust <- function(df, y, x) {
  lm(str_c(y, "~", x), df)
}

# Y esto es una funci?n que nos devuelve el coeficiente de determinaci?n calculado sobre un data frame, 
#  que no tiene porque ser el mismo sobre el que se ha entrenado el modelo que contiene mod
calcR2 <- function(df, mod, y) {
  MSE  <- mean((df[[y]] - predict.lm(mod, df)) ^ 2)
  varY <- mean(df[[y]] ^ 2) - mean(df[[y]]) ^ 2
  R2   <- 1 - MSE / varY
  aR2  <- 1 - (1- R2) * (nrow(df) - 1) / (nrow(df) - mod$rank)
  
  tibble(MSE=MSE, varY=varY, R2=R2, aR2=aR2)
}


# Nuestra funci?n de ajuste lineal, pero devolviendo el coeficiente de determinaci?n directamente, no el modelo.
calcModR2 <- function(dfTrain, dfTest, y, x) {
  mod <- linearAdjust(dfTrain, y, x)
  calcR2(dfTest, mod, y)$aR2
}


###########################
# C?digo principal
###########################

# Cargamos nuestro Tibble utilizando read_csv, especificando los tipos de las columnas
dfMec <- read_csv('c:/data/dfMec.csv',
                  col_types=cols(
                    .default = col_double(),
                    sexo = col_factor(),
                    emailMovil = col_factor(),
                    academia = col_factor(),
                    nivEstPad = col_factor() ) )

# Generamos en varPred un vector con todas las variables predictoras
varPred <- names(dfMec[-1])

# Separamos el conjunto de datos en train, test y validaci?n
dat <- separarSets(dfMec, .6, .5)

# Un ejemplo de c?lculo de modelo sobre el conjunto train y de c?lculo de R2 sobe el conjunto test
mod <- linearAdjust(dat$train, "notaFinal", "notaBach")
calcR2(dat$test, mod, "notaFinal")
# Lo mismo, en un solo paso
calcModR2(dat$train, dat$test, "notaFinal", "notaBach")
# Vemos el rango del modelo (n?mero de par?metros del modelo)
mod$rank


# Y ahora calculamos los 22 coeficientes de determinaci?n de los 22 modelos lineales unidimensionales
# Esto nos permitir?a escoger el mejor modelo lineal unidimensional eliminando fluctuaciones estad?sticas
ar2 <- varPred %>% map_dbl(calcModR2, dfTrain=dat$train, dfTest=dat$test, y="notaFinal")

# Vemos cu?l es la mejor variable para una predicci?n unidimensional
bestVar <- varPred[which.max(ar2)]
# Este es el mejor modelo y su R2 sobre el conjunto de test
bestMod <- linearAdjust(dat$train, "notaFinal", bestVar)
calcR2(dat$test, bestMod, "notaFinal")

# Calculamos su R2 sobre el conjunto de validaci?n
calcR2(dat$valid, bestMod, "notaFinal")

