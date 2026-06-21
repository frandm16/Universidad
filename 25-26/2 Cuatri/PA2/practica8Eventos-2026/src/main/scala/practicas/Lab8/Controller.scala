package practicas.Lab8

import java.awt.event.{ActionEvent, ActionListener}
import java.beans.PropertyChangeEvent

class Controller(panel: IPanel) extends ActionListener:
  private var worker: Option[Worker] = None

  override def actionPerformed(e: ActionEvent): Unit =
    e.getActionCommand match
      case panel.FIELD | panel.START =>
        if worker.exists(w => !w.isDone && !w.isCancelled) then
          panel.setStatusMessage("Hay una tarea en curso. Cancélala antes de empezar otra.")
          return

        val n = panel.getNumber
        if n > 0 then
          panel.clearTextArea()
          panel.updateProgress(0)
          panel.setStatusMessage(s"Calculando $n MCD...")

          val newWorker = new Worker(n, panel)
          worker = Some(newWorker)

          newWorker.addPropertyChangeListener((evt: PropertyChangeEvent) =>
            if evt.getPropertyName == "progress" then
              panel.updateProgress(evt.getNewValue.asInstanceOf[Int])
          )

          newWorker.execute()

      case panel.CANCEL_BUTTON =>
        worker.foreach(_.cancel(true))
        panel.setStatusMessage("Tarea cancelada")
        panel.updateProgress(0)
