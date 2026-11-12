# Sólo una vez:
install.packages("tidyverse")

# Cargamos tidyverse
library(tidyverse)

# Seleccionar columnas
select(dfMec, notaFinal, sexo, edad)

# Filtrar filas en estilo básico y con Dplyr
dfMec[dfMec$edad>20,]
filter(dfMec, edad>20)

# NO HACER: Primera forma de encadenar funciones dplyr
df1 <- filter(dfMec, edad>20)
select(df1, notaFinal, sexo, edad)

# NO HACER: Segunda forma de encadenar funciones dplyr
select(filter(dfMec, edad>20), notaFinal, sexo, edad)

# Funcionamiento del operador Pipe (shift + ctrl + M)
# x %>% f(y) %>% g(z)
# g(f(x, y), z)

# filter, mutate, rename, select
dfMec %>% 
  filter(sexo == "M") %>% 
  mutate(mayorEdad = (edad >= 21) ) %>% 
  rename(ed=edad) %>% 
  select(notaFinal, s=sexo, ed, mayorEdad)

# transmute = mutate + select
dfMec %>% 
  filter(sexo == "M") %>% 
  transmute(notaFinal, s=sexo, ed=edad, mayorEdad = (edad >= 21) )

# Extraer una columna
dfMec$edad
dfMec %>% pull(edad)

