/*
En nuestro grupo (nos llamaremos jóvenes) hemos preparado un maratón para ver todas las series
de nuestra plataforma de televisión favorita, que presume de tener un número ilimitado de horas
disponibles. Obviamente, no podemos ver nada en televisión sin comer palomitas sin parar, así que
hemos preparado un sistema en el que dispondremos de un bowl para las palomitas del que comeremos
todos. Cuando un joven quiere una palomita y el bowl no está vacío coge una palomita, pero si se
encuentra el bowl vacío llamará al encargado de preparar las palomitas (el palomitero), y esperará
a que rellene el bowl. Cuando el palomitero rellena el bowl, el joven que le había avisado coge
su palomita. Al palomitero le encanta hacer palomitas, y siempre está esperando un nuevo pedido,
cuando lo llaman prepara las palomitas, rellena el bowl y se queda a la espera de que le vuelvan
a llamar.
*/
import control_2.{log, thread}
import java.util.concurrent._
import scala.util.Random

class Bowl(R: Int) {
  private var bowl = R // inicialmente lleno
  private val mutex = new Semaphore(1)
  private val bowlVacio = new Semaphore(0)
  private val esperaLleno = new Semaphore(0)

  def coge(i: Int) = {
    mutex.acquire()
    if (bowl == 0){
      bowlVacio.release()
      esperaLleno.acquire()
    }
    bowl -= 1
    log(s"Joven $i coge una palomita del bowl. Quedan $bowl palomitas.")
    mutex.release()
  }
  def dormir = {
    // palomitero espera a que la bowl esté vacío
    bowlVacio.acquire()
  }
  def llenarBowl = {
    bowl = R
    log(s"El palomitero llena el bowl. Quedan $bowl palomitas.")
    esperaLleno.release()
  }
}

object marathon {
  def main(args: Array[String]): Unit =
    val NJovenes = 20 // número de jóvenes en la maratón
    val bowl = new Bowl(100) // el bowl se inicializa lleno, indicando en el constructor su capacidad
    val joven = new Array[Thread](NJovenes)
    for (i <- joven.indices)
      joven(i) = thread {
        while (true)
          Thread.sleep(Random.nextInt(100)) // espera entre palomita y palomita, no se vaya a atragantar
          bowl.coge(i)
      }
      val palomitero = thread {
        while (true)
          bowl.dormir
          Thread.sleep(500) // preparando las palomitas
          bowl.llenarBowl
      }
}
