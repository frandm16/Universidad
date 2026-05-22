# Miniproyecto 01 - AEMET
# Tratamiento y visualización de datos climatológicos comparados

import requests
import pandas as pd
import matplotlib.pyplot as plt


# a) URLs de datos de AEMET

URL_CIUDAD_1 = "https://opendata.aemet.es/opendata/sh/818c4f4a"
URL_CIUDAD_2 = "https://opendata.aemet.es/opendata/sh/3e5f839a"

NOMBRE_CIUDAD_1 = "Ciudad 1"
NOMBRE_CIUDAD_2 = "Ciudad 2"


# b) Carga y preprocesamiento de datos

def cargar_datos_aemet(url):
    respuesta = requests.get(url)

    if respuesta.status_code != 200:
        raise Exception(f"Error al cargar datos: {respuesta.status_code}")

    datos = respuesta.json()
    df = pd.DataFrame(datos)

    # b.1) Convertir fecha a datetime
    df["fecha"] = pd.to_datetime(df["fecha"])

    # b.2) Convertir columnas numéricas
    columnas = ["tmax", "tmin", "tmed", "prec", "velmedia", "racha"]

    for col in columnas:
        df[col] = (
            df[col]
            .astype(str)
            .str.replace(",", ".", regex=False)
            .str.replace("Ip", "0", regex=False)
        )
        df[col] = pd.to_numeric(df[col], errors="coerce")

    df = df.sort_values("fecha")

    return df


df1 = cargar_datos_aemet(URL_CIUDAD_1)
df2 = cargar_datos_aemet(URL_CIUDAD_2)


# c) Gráfica de temperaturas

plt.figure(figsize=(15, 7))

for df, nombre in [(df1, NOMBRE_CIUDAD_1), (df2, NOMBRE_CIUDAD_2)]:
    plt.plot(df["fecha"], df["tmax"], label=f"Tmax {nombre}")
    plt.plot(df["fecha"], df["tmed"], label=f"Tmed {nombre}")
    plt.plot(df["fecha"], df["tmin"], label=f"Tmin {nombre}")

    temp_max = df.loc[df["tmax"].idxmax()]
    temp_min = df.loc[df["tmin"].idxmin()]

    plt.scatter(temp_max["fecha"], temp_max["tmax"], s=100)
    plt.scatter(temp_min["fecha"], temp_min["tmin"], s=100)

    plt.annotate(
        f'{temp_max["tmax"]} ºC',
        xy=(temp_max["fecha"], temp_max["tmax"]),
        xytext=(10, 10),
        textcoords="offset points"
    )

    plt.annotate(
        f'{temp_min["tmin"]} ºC',
        xy=(temp_min["fecha"], temp_min["tmin"]),
        xytext=(10, -15),
        textcoords="offset points"
    )

plt.title("Temperaturas máximas, medias y mínimas")
plt.xlabel("Fecha")
plt.ylabel("Temperatura ºC")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()


# d) Gráfica de barras de precipitaciones

plt.figure(figsize=(15, 6))

plt.bar(
    df1["fecha"] - pd.Timedelta(hours=8),
    df1["prec"],
    width=0.35,
    label=NOMBRE_CIUDAD_1
)

plt.bar(
    df2["fecha"] + pd.Timedelta(hours=8),
    df2["prec"],
    width=0.35,
    label=NOMBRE_CIUDAD_2
)

plt.title("Precipitaciones diarias")
plt.xlabel("Fecha")
plt.ylabel("Precipitación mm")
plt.legend()
plt.grid(axis="y")
plt.tight_layout()
plt.show()


# e) Gráfica de viento y rachas

plt.figure(figsize=(15, 7))

for df, nombre in [(df1, NOMBRE_CIUDAD_1), (df2, NOMBRE_CIUDAD_2)]:
    plt.plot(df["fecha"], df["velmedia"], label=f"Velocidad media {nombre}")
    plt.scatter(df["fecha"], df["racha"], s=25, label=f"Racha diaria {nombre}")

    racha_max = df.loc[df["racha"].idxmax()]

    plt.scatter(
        racha_max["fecha"],
        racha_max["racha"],
        s=160,
        marker="*"
    )

    plt.annotate(
        f'{racha_max["racha"]} km/h',
        xy=(racha_max["fecha"], racha_max["racha"]),
        xytext=(10, 10),
        textcoords="offset points"
    )

plt.title("Velocidad media del viento y rachas máximas")
plt.xlabel("Fecha")
plt.ylabel("Velocidad km/h")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()