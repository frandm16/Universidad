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

# Nuestra funci?n b?sica de ajuste lineal para m?ltiples x
linearAdjust <- function(df, y, x) {
  lm(str_c(y, "~", str_c(x, collapse="+")), df)
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

# Nuestra funci?n estrella que calcula el mejor ajuste lineal
encontrarMejorAjuste <- function(dfTrain, dfTest, varPos) {
  bestVars <- character(0)
  aR2      <- 0
  
  repeat {
    aR2v <- map_dbl(varPos, ~calcModR2(dfTrain, dfTest, "notaFinal", c(bestVars, .)))
    i    <- which.max(aR2v)
    aR2M <- aR2v[i]
    if (aR2M <= aR2) break
    
    cat(sprintf("%1.4f %s\n", aR2M, varPos[i]))
    aR2 <- aR2M
    bestVars <- c(bestVars, varPos[i])
    varPos   <- varPos[-i]
  }
  
  mod <- linearAdjust(dfTrain, "notaFinal", bestVars)
  
  list(vars=bestVars, mod=mod)
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

# Separamos el conjunto de datos en train, test y validaci?n
dat <- separarSets(dfMec, .6, .5)

# Generamos en varPred un vector con todas las variables predictoras
varPos1 <- names(dfMec[-1])
varPos2 <- crossing(var1=varPos1, var2=varPos1) %>% pmap_chr(str_c, sep=":")


# EJEMPLO: Hacemos dos pasos de como ser?a el proceso de calcular nuestro modelo incremental
# Vemos cu?l es la mejor variable para una predicci?n unidimensional
ar2v <- varPos1 %>% map_dbl(calcModR2, dfTrain=dat$train, dfTest=dat$test, y="notaFinal")
i1 <- which.max(ar2v)
bestVar  <- varPos1[i1]
varPos1b <- varPos1[-i1]
ar2v[i1]

ar2b <- varPos1b %>% map_dbl(~calcModR2(dfTrain=dat$train, dfTest=dat$test, y="notaFinal", x=c(bestVar, .)))
i2 <- which.max(ar2b)
bestVar  <- c(bestVar, varPos1b[i2])
varPos1c <- varPos1b[-i2]
ar2b[i2]


# Calculamos el mejor ajuste para variables simples
# Separamos el conjunto de datos en train, test y validaci?n
dat <- separarSets(dfMec, .6, .5)
bestMod1 <- encontrarMejorAjuste(dat$train, dat$test, varPos1)
calcR2(dat$valid, bestMod1$mod, "notaFinal")

# Calculamos el mejor ajuste para pares de interacciones de variables
bestMod2 <- encontrarMejorAjuste(dat$train, dat$test, varPos2)
calcR2(dat$valid, bestMod2$mod, "notaFinal")

