import pandas as pd
import matplotlib.pyplot as plt
import random

# a) Cargar CSV
df = pd.read_csv("peliculas.csv")

print(df.info())
print(df.head())

# a.1) Género aleatorio
generos = ["Acción", "Comedia", "Drama", "Terror", "Sci-Fi", "Romance"]
df["genero"] = [random.choice(generos) for _ in range(len(df))]

# b.1) Eliminar última columna
df = df.iloc[:, :-1]

# b.2) Director desconocido
df["director"].fillna("Desconocido", inplace=True)

# b.3) Recaudación = mediana
df["recaudacion"] = pd.to_numeric(df["recaudacion"], errors="coerce")
df["recaudacion"].fillna(df["recaudacion"].median(), inplace=True)

# b.4) Eliminar duplicados
df.drop_duplicates(inplace=True)

# c.1) Media puntuación por género
media = df.groupby("genero")["puntuacion"].mean()
print(media)

# c.2) Columna "exito"
p75 = df["recaudacion"].quantile(0.75)
df["exito"] = df["recaudacion"].apply(
    lambda x: "Alta Recaudación" if x > p75 else "Baja Recaudación"
)

# d.1) Barras
media.plot(kind="bar")
plt.show()

# d.2) Histograma
df["recaudacion"].plot(kind="hist", bins=20)
plt.show()