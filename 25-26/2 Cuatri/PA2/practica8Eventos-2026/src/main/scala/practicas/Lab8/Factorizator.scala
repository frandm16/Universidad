package practicas.Lab8

import scala.collection.mutable.ListBuffer

// Clase que extiende Thread para factorizar un número en un hilo separado
class Factorizator(private val num: Int) extends Thread:

  val factors: ListBuffer[Int] = ListBuffer.empty[Int]

  // Método principal que ejecuta la factorización del número
  override def run(): Unit =
    var aux = num
    var divisor = 2

    // Divide el número por divisores primos hasta que aux sea 1
    while aux > 1 && divisor.toLong * divisor <= aux do
      if aux % divisor == 0 then
        factors += divisor
        aux = aux / divisor
      else
        divisor = Factorizator.nextPrime(divisor)

    // Si aux > 1, entonces aux es un factor primo
    if aux > 1 then
      factors += aux

  // Devuelve el número a factorizar
  def getNum(): Int =
    num


object Factorizator:
  // Verifica si un número es primo
  private def isPrime(n: Int): Boolean =
    n > 1 && (2 to math.sqrt(n).toInt).forall(n % _ != 0)

  // Encuentra el siguiente número primo después del número actual
  private def nextPrime(current: Int): Int =
    var candidate =
      if current == 2 then 3
      else current + 2

    while !isPrime(candidate) do
      candidate += 2

    candidate

  // Calcula el máximo común divisor (MCD) de dos listas de factores
  // Modifica las listas de entrada eliminando los factores comunes
  def getGCD(
              factors0: ListBuffer[Int],
              factors1: ListBuffer[Int]
            ): Int =
    var gcd = 1

    while factors0.nonEmpty do
      val f0 = factors0.remove(0)
      val index = factors1.indexOf(f0)

      if index >= 0 then
        factors1.remove(index)
        gcd *= f0

    gcd
