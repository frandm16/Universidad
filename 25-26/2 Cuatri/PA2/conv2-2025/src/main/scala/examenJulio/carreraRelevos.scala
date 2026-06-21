package examenJulio


import examenJulio.esqueletos.*
import scala.util.Random


class CarreraRelevos(numEquipos: Int, corredoresPorEquipo: Int) {
  var turnoEquipo = Array.fill(numEquipos)(true)
  var contadorEquipo = Array.fill(numEquipos)(0)
  var posicion = 0
  // Parte 2
  var posicionEquipos = Array.fill(numEquipos)(0)
  var otorgaMedalla = Array.fill(numEquipos)(false)
  var agradeceMedalla = Array.fill(numEquipos)(false)
  var terminado = false


  def inicioCarrera(idEquipo: Int, idCorredor: Int): Unit = synchronized {
    while(!turnoEquipo(idEquipo)) wait()
    log(s"${'\t'.toString * idEquipo}Equipo $idEquipo - Corredor $idCorredor empieza a correr")
    turnoEquipo(idEquipo) = false
  }

  def finCarrera(idEquipo:Int, idCorredor:Int):Unit = synchronized {

    log(s"${'\t'.toString * idEquipo}Equipo $idEquipo - Corredor $idCorredor termina su vuelta")
    contadorEquipo(idEquipo) += 1

    if (contadorEquipo(idEquipo) == corredoresPorEquipo)
      posicionEquipos(posicion) = idEquipo
      posicion += 1
      if (posicion == numEquipos) {
        terminado = true
        notifyAll()
      }
      while(!otorgaMedalla(idEquipo)) wait()
      agradeceMedalla(idEquipo) = true
      log(s"${'\t'.toString * idEquipo}Equipo $idEquipo agradece su medalla")
      notifyAll()


     // if (posicion == 1)
     //   log(s"${'\t'.toString * idEquipo}Equipo $idEquipo es el ganador!")
     // else
     //   log(s"${'\t'.toString * idEquipo}El equipo $idEquipo ha terminado la carrera, ha quedado en la posicion $posicion")

    turnoEquipo(idEquipo) = true
    notifyAll()
  }

  //Segunda parte
  def supervisa(): Unit = synchronized {
    while(!terminado) wait()

    for(x <- 0 until 3) {

      log(s"${'\t'.toString }Juez otorga la medalla al equipo $x")
      otorgaMedalla(x) = true
      notifyAll()

      while(!agradeceMedalla(x)) wait()
      log(s"Juez recibio agradecimientos del equipo $x")
    }
    log(s"Podio final: ${posicionEquipos.take(3).mkString(", ") }")

  }

}

object Main {
  def main(args: Array[String]): Unit = {
    val numEquipos = 10
    val corredoresPorEquipo = 4
    val carrera = new CarreraRelevos(numEquipos, corredoresPorEquipo)

    for (equipo <- 0 until numEquipos; corredor <- 0 until corredoresPorEquipo) {
      thread {
        carrera.inicioCarrera(equipo, corredor)
        Thread.sleep(200+Random.nextInt(500))
        carrera.finCarrera(equipo,corredor)
      }
    }

    // Segunda parte: el juez entrega medallas con handshake
   val juez= thread {
      carrera.supervisa()
    }

  }
}
