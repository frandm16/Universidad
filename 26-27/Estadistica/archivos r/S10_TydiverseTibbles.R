library(tidyverse)

# Leemos en formato data frame
dfMec <- read.csv('c:/data/dfMec.csv')

# Usamos la alternativa mejorada de la libreria readr (tidyverse) que lee en formato tibble
dfMec2 <- read_csv('c:/data/dfMec.csv')

# Transformando un Data frame en Tibble
tibble(dfMec)
as.tibble(dfMec)

# Transformando un Tibble en Data frame
data.frame(dfMec2)
as.data.frame(dfMec2)

# Funciones dplyr que si entra un data frame devuelven un Data frame
dfMec %>% select(edad, sexo)

# Funciones dplyr que devuelven un Tibble, aunque la entrada sea un Data Frame
dfMec %>% group_by(edad)


# definiendo un Data frame o Tibble sobre la marcha
data.frame(x=1:5, y=11:15)
tibble(x=1:5, y=11:15)


# Definiendo tibbles de una manera más cool con tribble
tibble(x=c("a", "b"), y=c(3, 6))

tribble(
  ~x,  ~y,
  "a", 3,
  "b", 6
)

