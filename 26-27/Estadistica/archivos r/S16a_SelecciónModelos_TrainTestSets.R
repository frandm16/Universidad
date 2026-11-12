library(tidyverse)

# Cargamos nuestro Tibble utilizando read_csv, especificando los tipos de las columnas
dfMec <- read.csv('dfMEC.csv',
                  col_types=cols(
                    .default = col_double(),
                    sexo = col_factor(),
                    emailMovil = col_factor(),
                    academia = col_factor(),
                    nivEstPad = col_factor() ) )


# Generamos en varPred un vector con todas las variables predictoras
varPred <- names(dfMec[-1])

# NO LO VAMOS A HACER AS?: Podr?amos generar as? los conjunto de train y set, 
#  pero si el data frame es muy grande ser?a computacionalmente muy costoso.
df1 <- sample_n(dfMec, 150)
df2 <- setdiff(dfMec, df1)

# Esto es una funci?n para separar nuestro data frame original en dos: uno de entrenamiento y otro de testeo,
#   en la proporci?n que nos indica p
separarSets <- function(df, p) {
  rDf    <- 1:nrow(df)
  rTrain <- sample(rDf, p * length(rDf))
  rTest  <- setdiff(rDf, rTrain)

  list(train=df[rTrain,], test=df[rTest,])  
}


# Y esto es una funci?n que nos devuelve el coeficiente de determinaci?n calculado sobre un data frame, 
#  que no tiene porque ser el mismo sobre el que se ha entrenado el modelo que contiene mod
calcR2 <- function(df, mod, y) {
  MSE  <- mean((df[[y]] - predict.lm(mod, df)) ^ 2)
  varY <- mean(df[[y]] ^ 2) - mean(df[[y]]) ^ 2
  R2   <- 1 - MSE / varY
  
  R2
}


# Nuestra funci?n de ajuste lineal, pero devolviendo el coeficiente de determinaci?n directamente, no el modelo.
linearAdjust <- function(dfTrain, dfTest, y, x) {
  mod <- lm(str_c(y, "~", x), dfTrain)
  calcR2(dfTest, mod, "notaFinal")
}

# Separamos el conjunto de datos en train y test
dfs <- separarSets(dfMec, .7)

# Un ejemplo de c?lculo de modelo sobre el conjunto train y de c?lculo de R2 sobe el conjunto test
mod <- lm("notaFinal~notaBach", dfs$train)
calcR2(dfs$test, mod, "notaFinal")


# Y ahora calculamos los 22 coeficientes de determinaci?n de los 22 modelos lineales unidimensionales
# Esto nos permitir?a escoger el mejor modelo lineal unidimensional eliminando fluctuaciones estad?sticas
varPred %>% map_dbl(linearAdjust, dfTrain=dfs$train, dfTest=dfs$test, y="notaFinal")

