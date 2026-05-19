/*
En un cónclave de cardenales, se debe realizar una votación para elegir a un nuevo Papa.
Hay N cardenales (en este caso, 9), que votan por cualquier candidato. En cada ronda:
(i) Los cardenales votan de manera concurrente.
(ii) Si ningún candidato obtiene más de la mitad de los votos (N/2), se realiza una nueva votación.
(iii) Si un candidato obtiene más de la mitad de los votos, se declara ganador y se enciende la
"fumata blanca" (fumata_blanca = true), indicando que el cónclave ha finalizado.
El programa debe garantizar la sincronización entre los cardenales para evitar condiciones de
carrera y asegurar que los votos se procesen correctamente. Los cardenales deben esperar a que
todos terminen de votar antes de iniciar una nueva ronda.
*/
import control_2.{log, thread}
import java.util.concurrent._
import scala.util.Random

object conclave {
  val numCardenales = 9
  var fumata_blanca = false
  var ganador: Option[Int] = None
  private val votos = scala.collection.mutable.Map[Int,Int]()
  private var votacion = 0

  private val esperaVotacion = new Semaphore(1)
  private val esperaFinVotacion = new Semaphore(0)
  // ...

  def vota(i: Int) = {
    // ...
    esperaVotacion.acquire()
    votos(i) = votos.getOrElse(i, 0)+1

    if (votos.values.sum < numCardenales)
      esperaVotacion.release()
      esperaFinVotacion.acquire()
    else
      ganador = Some(votos.maxBy(_._2)._1)
      log(s"Votación ${votacion}, gana el candidato ${ganador.get} con ${votos(ganador.get)} votos")
      if (votos(ganador.get) > numCardenales / 2)
        fumata_blanca = true
      else
        votos.clear()
        votacion += 1
      esperaFinVotacion.release(numCardenales-1)
      esperaVotacion.release()
  }
  // ...
}

@main def main =
  val N = conclave.numCardenales
  val cardenal = new Array[Thread](N)
  for (i <- 0 until N)
    cardenal(i) = thread {
      while (!conclave.fumata_blanca)
        conclave.vota(Random.nextInt(N))
        Thread.sleep(Random.nextInt(200))
    }
  for (i <- 0 until N)
    cardenal(i).join()
  log(s"El nuevo papa es el cardenal ${conclave.ganador.get}")
