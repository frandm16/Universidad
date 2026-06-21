package parte_2025_4

import java.math.BigInteger
import javax.swing.SwingWorker
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters.*

class Worker(n: Int, tipo: String, panel: IPanel)
  extends SwingWorker[java.math.BigInteger, PrimesPair] {

  //Comprueba si el numero es primo (como se puede observar)
  private def esPrimo(num: Long): Boolean =
    if (num < 2) false
    else !(2L to math.sqrt(num).toLong).exists(num % _ == 0)

  override def doInBackground(): BigInteger = {
    var count = 0
    var current = 2L
    var lastPrimes = ListBuffer[Long]()
    var suma = BigInteger.ZERO

    while (count < n && !isCancelled) {
      if (esPrimo(current)) {
        for (prev <- lastPrimes) {
          val diff = (current - prev).abs //Comprobamos si forman pareja
          val isMatch = (tipo match { //Decidimos la condicion
            case "Gemelos" => diff == 2
            case "Primos"  => diff == 4
            case "Sexies"  => diff == 6
            case _ => false
          })
            //Si se cumple la condición, publicamos el par y actualizamos suma y progreso
          if (isMatch) {
            val pair = PrimesPair(prev, current, count + 1)
            publish(pair)
            suma = suma.add(BigInteger.valueOf(prev + current))
            count += 1
            if (count >= n) return suma
          }
        }
        lastPrimes += current
        if (lastPrimes.size > 10) lastPrimes.remove(0)
      }
      //Actualizamos el progreso de la barra
      if (n > 0) setProgress((count * 100) / n)
      current += 1
    }
    suma //devolvemos la suma de todas ellas
  }

  //La enviamos a un panel para que se muestre
  override def process(chunks: java.util.List[PrimesPair]): Unit = {
    panel.writePrimes(chunks.asScala.toList)
  }

  //Cuando finaliza la tarea 
  override def done(): Unit = {
    try {
      if (!isCancelled) {
        //Si no se cancela, mostramos toda la suma total
        val total = get()
        panel.setStatusMessage(s"Suma de todos los primos: $total")
        panel.updateProgress(100)
      } else {
        //Si intentamos hacer otra tarea cuando esta en curso una
        //Se cancela
        panel.setStatusMessage("Tarea interrumpida")
        panel.updateProgress(0) // 🔧 Añadir esto
      }
    } catch {
      case _: java.util.concurrent.CancellationException =>
        panel.setStatusMessage("Tarea interrumpida")
        panel.updateProgress(0) // 🔧 También aquí, por si se lanza la excepción
    }
  }

}
