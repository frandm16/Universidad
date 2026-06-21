package parte_2025_4

import java.awt.event.{ActionEvent, ActionListener}
import java.beans.PropertyChangeEvent
import javax.swing.SwingUtilities

class Controller(panel: IPanel) extends ActionListener {
  private var worker: Option[Worker] = None

  override def actionPerformed(e: ActionEvent): Unit = {
    e.getActionCommand match {
      case panel.FIELD =>
        panel.clearTextArea()

        //Evita ejecutar si ya hay una tarea en curso
        if worker.exists(w => !w.isDone && !w.isCancelled) then
          panel.setStatusMessage("Espera a que termine esta o dale al botón de cancelar si quieres ejecutar otra")
          return

        val n = panel.getNumber
        if (n > 0) {
          panel.setStatusMessage(s"Opción seleccionada: ${panel.getSelectedOption}") //Elegimos cual queremos introducir
          val newWorker = new Worker(n, panel.getSelectedOption, panel)
          worker = Some(newWorker)

          //Actualizamos la barra de progreso
          newWorker.addPropertyChangeListener((evt: PropertyChangeEvent) => {
            if evt.getPropertyName == "progress" then
              val progress = evt.getNewValue.asInstanceOf[Int]
              panel.updateProgress(progress)
          })

          newWorker.execute() //Ejecutamos el worker
        }

      case panel.COMBO => //JcomboBox cambio de seleccion desplegable
        panel.setStatusMessage(s"Opción seleccionada: ${panel.getSelectedOption}")

      case panel.CANCEL_BUTTON => //Boton de cancela para pausar la ejecucion
        worker.foreach(_.cancel(true))
        panel.setStatusMessage("Tarea interrumpida")
    }
  }
}
