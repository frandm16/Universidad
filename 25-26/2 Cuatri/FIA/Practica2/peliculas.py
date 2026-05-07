import pandas as pd
import matplotlib.pyplot as plt
import random

# a) Cargar el CSV y mostrar la información general del mismo.
df = pd.read_csv("peliculas.csv")

print(df.info())

# a.1) Asignamos a cada película un género aleatorio de una lista predefinida
generos = ["Acción", "Comedia", "Drama", "Terror", "Ciencia Ficción"]

df["genero"] = [random.choice(generos) for _ in range(len(df))]

# b.1) Eliminamos la última columna, la cual no contiene datos
df = df.iloc[:, :-1]

# b.2) Para aquellas películas de las cuales no tengamos nombre del director, le asignamos el valor "Desconocido"
df["director"] = df["director"].fillna("Desconocido")

# b.3) A aquellas películas sin datos de recaudación les asignamos una igual a la mediana
df["recaudacion"] = df["recaudacion"].fillna(df["recaudacion"].median())

# b.4) Eliminar duplicados si existieran
df.drop_duplicates(inplace=True)

# c.1) Calculamos el promedio de puntuación por género.
promedio_genero = df.groupby("genero")["puntuacion"].mean()

# c.2) Agregamos una nueva columna "exito" que clasifique las películas en Alta Recaudación (mayor al percentil 75) o Baja Recaudación (resto).
percentil_75 = df["recaudacion"].quantile(0.75)

df["exito"] = "Baja Recaudación"
df.loc[df["recaudacion"] > percentil_75, "exito"] = "Alta Recaudación"


# d.1) Mostramos un gráfico de barras con la puntuación media por género.
plt.figure()
promedio_genero.plot(kind="bar")
plt.title("Puntuación media por género")
plt.xlabel("Género")
plt.ylabel("Puntuación media")
plt.show()

# d.2) Mostramos un histograma de la distribución de recaudación.
plt.figure()
df["recaudacion"].plot(kind="hist", bins=40)
plt.title("Distribución de recaudación")
plt.xlabel("Recaudación")
plt.ylabel("Frecuencia")
plt.show()