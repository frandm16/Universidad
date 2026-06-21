package practicas.Lab8

import java.awt.event.ActionListener

trait IPanel:
  // comandos de acción
  val FIELD = "FIELD"
  val CANCEL_BUTTON = "CANCEL"
  val START = "START"

  def setController(controller: ActionListener): Unit
  def setStatusMessage(msg: String): Unit
  def getNumber: Int
  def writeMCD(results: scala.List[String]): Unit
  def updateProgress(value: Int): Unit
  def clearTextArea(): Unit

