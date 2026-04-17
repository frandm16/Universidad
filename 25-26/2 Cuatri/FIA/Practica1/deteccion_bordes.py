import numpy as np
import matplotlib.pyplot as plt
from skimage import data, color, filters
from PIL import Image

# Cada #### se corresponde con una acción que se puede realizar con una única línea de código

imagen_path = "Plano.jpg"
imagen = Image.open(imagen_path)

# Convertir la imagen a escala de grises
imagenEscalaGrises = color.rgb2gray(np.array(imagen))

# Umbral para determinar si es un borde o no (ajustar según necesidad)
threshold = 0.2

# Calcular las diferencias de píxeles (gradientes) en direcciones horizontal y vertical
# diff genera arrays con
dx = np.diff(imagenEscalaGrises, axis=1)
dy = np.diff(imagenEscalaGrises, axis=0)

# Completar la matriz para que tenga el mismo tamaño que la imagen original
# Si tenemos una matriz de tamaño nxn, diff devuelve matrices de tamaño n*n-1 o n-1*n en función del eje sobre el cual se calcula
dx = np.pad(dx, ((0, 0), (0, 1)), mode="constant")
dy = np.pad(dy, ((0, 1), (0, 0)), mode="constant")

# Calcular la magnitud del gradiente (por ejemplo, usando el teorema de Pitágoras)
# Intensidad del cambio en ambas direcciones
gradiente = np.sqrt(dx**2 + dy**2)

# Aplicar un umbral para marcar los bordes
# En edges se marcan a True los valores que cumplen la condición
bordes = gradiente > threshold

# Mostrar la imagen original y los bordes detectados
plt.figure(figsize=(10, 5))

plt.subplot(3, 3, 1)
plt.title("Imagen Original")
plt.imshow(imagenEscalaGrises, cmap="gray")
plt.axis("off")

plt.subplot(3, 3, 2)
plt.title("Diferencias eje X")
plt.imshow(dx, cmap="gray")
plt.axis("off")

plt.subplot(3, 3, 3)
plt.title("Diferencias eje Y")
plt.imshow(dy, cmap="gray")
plt.axis("off")

plt.subplot(3, 3, 4)
plt.title("Magnitud del gradiente")
plt.imshow(gradiente, cmap="gray")
plt.axis("off")

plt.subplot(3, 3, 5)
plt.title("Detección de Bordes por Diferencias")
plt.imshow(bordes, cmap="gray")
plt.axis("off")

plt.show()
