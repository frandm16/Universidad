.data

tam:  .word 6

VECT: .word 1,2,3,4,5,6

RES:  .word 0,0,0,0,0,0

.text

MAIN:

 la s4, tam

 lw s4, 0(s4)

 la s5, VECT

 la s6, RES

 li s7, 0

loop:

 beq s7, s4, exit

 lw a0, 0(s5)

 addi s5, s5, 4

 jal SUM

 sw a0, 0(s6)

 addi s6, s6, 4

 addi s7, s7, 1

 j loop

exit:

 li a7, 10

 ecall
	



SUM:

 li s4, 0

 li s5, 0

loopsum:

 bgt s5, a0, endsum

 add s4, s4, s5

 addi s5, s5, 1

 j loopsum

endsum:

 mv a0, s4

 ret  