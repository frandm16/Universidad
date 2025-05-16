.data

res: .word 0

.text

main:

  li t1, 2

  li t2, 3

 jal ra, maximo

  mv t1, t0

 

  li t2, 4

 jal ra, maximo

  la t1, res

  sw t0, 0(t1)

 li a7, 10

  ecall 
	



maximo:

  mv t0, t1

  blt t2, t0, endmax

 mv t0, t2

endmax:ret