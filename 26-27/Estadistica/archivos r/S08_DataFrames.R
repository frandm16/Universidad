# Creaci?n de un data frame y visualizaci?n
df <- data.frame(x=c(1,2,3), y=c("a", "xx", "jo"))
df
View(df)

# Descarga de nuestro data frame de referencia
dfMec <- read.csv("dfMEC.csv")
View(dfMec)

# Acceso a filas
dfMec[1,]
dfMec[10:15,]

# Acceso a una columna
dfMec[[1]]
dfMec[,1]
dfMec$notaFinal

# Acceso a varias columnas
dfMec[,3:4]
dfMec[c("notaFinal", "sexo")]

# Nombres de columnas
names(dfMec)

# Filtrado de filas 
dfMec[dfMec$sexo=="V",]
dfMec[dfMec$sexo=="V" & dfMec$edad>21,]

