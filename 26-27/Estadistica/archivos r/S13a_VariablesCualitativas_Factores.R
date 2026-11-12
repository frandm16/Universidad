library(tidyverse)

# Cargamos nuestro Tibble utilizando read_csv y nos fijaremos en el reporte de tipos que nos proporciona,
#   para as? poder modificarlo
dfMec <- read_csv('c:/data/dfMec.csv')

# Cargamos nuestro Tibble utilizando read_csv y especificando los tipos de las columnas
dfMec <- read_csv('c:/data/dfMec.csv',
                  col_types=cols(
                    .default = col_double(),
                    sexo = col_factor(),
                    emailMovil = col_factor(),
                    academia = col_factor(),
                    nivEstPad = col_factor()
                  ))

# NO UTILIZAMOS ESTO: Esta ser?a una manera alternativa de especificar los tipos de las columnas.
dfMec <- read_csv('c:/data/dfMec.csv', col_types="dfiddiiiiiiiiiifiiiffii")

# NO UTILIZAMOS ESTO: Esta ser?a una manera de transformar una columna en un factor, 
#  pero en lugar de eso preferimos leerla directamente como factor.
dfMec %>% mutate(sexo=factor(sexo))

# Aqu? podemos ver los nombres de las columnas y sus tipos
names(dfMec)
spec(dfMec)

# Si queremos un uso avanzado de factores podemos utilizar forcats, librer?a de Tidyverse.
# La librer?a se instala cuando instalamos Tidyverse, pero necesitamos cargarla en memoria separadamente.
library(forcats)
