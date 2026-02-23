object primerCodigo {
  def main(args: Array[String]): Unit = {
      println(args(0) + " " + args(1) + " " + args(2))
      println("Aura")
      println(factorial(5))
  }

  def factorial(x : BigInt) : BigInt = {
    if(x == 0) 1 else x * factorial(x - 1)
  }


}

