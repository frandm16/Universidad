package PAII_IS_A1

import  PAII_IS_A1.log
import scala.util.Random
class Salon(cap:Int){

  /*
   * Condiciones sincronización del ejercicio 1
   * CS-Dec1: El decano no entra en la salon hasta que lo avisan de que se ha excedido el aforo
   * CS-Dec2: El decano espera a que se vacíe el salón para volver a dormir
   * CS-Est1: Un estudiante no puede entrar si el decano está en ella
   */

     /*
     * Condiciones sincronización del ejercicio 2
     * CS-Est2: Un estudiante que está en la fiesta no puede salir si el decano ha sido avisado
     */

  def llegoAFiesta(id:Int)={
    //El estudiante llama a este método cuando quiere entrar en la fiesta

    log(s"Estudiante $id llega a la fiesta. ....")

    //el estudiante que detecta que se ha superado el aforo avisa al decano
  }

  def salgoFiesta(id:Int)={
    //estudiante id llama a este método cuando quiere abandonar la fiesta

    log(s"Estudiante $id sale de la fiesta....")

  }

  def meDuermo()= {
    //El decano llama a este método cuando quiere dormir
    //se despierta cuando le avisan de que se ha superado el aforo
    //...
    log(s"Decano: me despierto")
    //...
    log(s"Decano: entro en la sala")
  }
  def esperoTodosFuera()={
    //El decano llama a este método para esperar a que salgan del solón todos los estudiantes
    log(s"Decano: espero que salgan todos")
    //....
    log(s"Decano: me voy otra vez a dormir")

  }
}
object ejemResidencia {

  def main(args:Array[String]):Unit={
    val R = 20
    val Cap = 5
    val F = 1
    val salon = new Salon(Cap)
    val estudiante = new Array[Thread](R)
    for (i<-estudiante.indices)
      estudiante(i) = thread{
        for (j<-0 until F)
          Thread.sleep(Random.nextInt(700))
          salon.llegoAFiesta(i)
          Thread.sleep(100)
          salon.salgoFiesta(i)
          Thread.sleep(700)
      }
    val decano = thread{
      var fin =false
      while (!fin && !Thread.interrupted()){
        try{
          Thread.sleep(Random.nextInt(200))
          salon.meDuermo()
          salon.esperoTodosFuera()
        } catch {
          case e:InterruptedException => fin = true
        }
      }
    }
    estudiante.foreach(_.join())
    decano.interrupt()
    decano.join()
    log(s"Todos los estudiantes y el decano se han ido a dormir")
  }

}
