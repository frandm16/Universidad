.data
tam: .word 3
res: .word 0
datos: .word 2, -3, 6

.text
.global main

main: la t0, tam	# cartamos en t0 dirección de tam
      lw t1, 0(t0)	# cargamos en t1 valor de tam (contador de iteraciones)
      la t2, datos	# cargamos en t2 dirección de datos (apunta al primer elemento)
      li t3, 0		# inicializamos t3 a 0 (suma parcial)
loop: beq t1, zero, sal	# si t1 ha llegado a 0, terminar bucle
      lw t4, 0(t2)	# cargamos en t4 valor del vector
      addi t2, t2, 4	# hacemos que t2 apunte a la siguiente posición del vector
      add t3, t3, t4	# acumulamos valor leido (t4) en la suma parcial (t3)
      addi t1, t1, -1	# disminuimos en 1 el contador de iteraciones
      j loop		# volvemos a la cabecera del bucle (iteramos)
sal:  la t0, res	# cargamos en t0 la dirección de la variable res
      sw t3, 0(t0)	# almacenamos suma parcial (t3) en posición de memoria res (t0)

      li a7, 10		# cargamos en a7 código de fin de programa
      ecall		# llamada al sistema operativo 
      
