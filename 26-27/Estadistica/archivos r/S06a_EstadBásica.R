data("AirPassengers")
ap <- as.vector(AirPassengers)

# operaciones b?sicas
length(ap)
sum(ap)
mean(ap)
sort(ap)

# Mediana
median(ap)
mean(sort(ap)[72:73])

# Moemento ordinario de orden 2
mean(ap^2)

# Momento central de orden 1 = 0
mean(ap-mean(ap))

# Varianza
mean((ap - mean(ap))^2)
mean(ap^2) - mean(ap)^2
sum((ap - mean(ap))^2) / length(ap)
var(ap) * (length(ap)-1) / length(ap)

# cuasivarianza
sum((ap - mean(ap))^2) / (length(ap)-1)
var(ap)

# Desviaci?n t?pica
sqrt(mean(ap^2) - mean(ap)^2)
sd(ap) * sqrt((length(ap)-1) / length(ap))

# Funciones para calcular momentos
Mord   <- function(data, n) mean(data^n)
Mcentr <- function(data, n) mean((data - mean(data))^n)

# Histograma
hist(ap)

# Coeficiente de asimetr?a
Mcentr(ap, 3) / Mcentr(ap, 2) ^ (3/2)

# Coeficiente de APuntamiento
Mcentr(ap, 4) / Mcentr(ap, 2) ^ 2 - 3


m = mean(ap)
s = sqrt(Mcentr(ap, 2))

sum((ap > m - s) & (ap < m + s)) / length(ap)
sum((ap > m-2*s) & (ap < m+2*s)) / length(ap)
sum((ap > m - 3*s) & (ap < m + 3*s)) / length(ap)


# Covarianza
x <- ap
y <- 1:144
mean(x * y) - mean(x) * mean(y)
cov(x, y) * (length(ap)-1) / length(ap)

