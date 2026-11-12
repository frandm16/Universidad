x <- 1:10
y <- 2 * x + 3 + runif(10, -1, 1)

# función gráfica plot
plot(x, y, xlim=c(0, 11), ylim=c(0, 25), type="p", xlab="puntos x", ylab="puntos y")


# funciones básicas gráficas
abline(3, 2, col="red")
abline(h=5)
abline(v=4, col="blue")
points(c(6,7), c(10,12), col="green")

#representación de funciones
x <- seq(0, 4*pi, .1)
y <- sin(x)
plot(x,y, type="l")

# Generación directa de un gráfico en disco
jpeg("c:/data/ejemplo.jpg")
plot(x, y, xlim=c(0, 11), ylim=c(0, 25), type="p", xlab="puntos x", ylab="puntos y")
abline(3, 2, col="red")
abline(h=5)
abline(v=4, col="blue")
points(c(6,7), c(10,12), col="green")
dev.off()
