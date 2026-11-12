# Cuantiles en R
quantile(ap)
quantile(ap, .1)

# Vemos que la interpolaci?n de cuantiles es ligeramente diferente de unos lenguajes de progamaci?n a otros
quantile(1:100, .1)

# Funciones sample y table
xx <- sample(1:10, 100, replace=T)
table(xx)

# Funciones min y max
min(ap)
max(ap)

# Funci?n findInterval para agrupar en intervalos
seq(0, 700, 50)
findInterval(ap, seq(0, 700, 50))

# Tabla de frecuencias de intervalos
50*(findInterval(ap, seq(0, 700, 50))-1)
table(50*(findInterval(ap, seq(0, 700, 50))-1))

# Uso de NA
sum(xx)
sum(xx, na.rm=T)
mean(xx, na.rm=T)

# Indentificaci?n y eliminaci?n de NAs
is.na(xx)
xx[!is.na(xx)]

