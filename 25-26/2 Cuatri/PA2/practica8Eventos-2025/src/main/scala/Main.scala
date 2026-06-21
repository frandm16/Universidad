package parte_2025_4

import javax.swing.*

object Main {
  def main(args: Array[String]): Unit = {
    SwingUtilities.invokeLater(() => {
      val frame = new JFrame("Pares de Primos") //Ventana principal
      val panel = new Panel()
      val controller = new Controller(panel)
      panel.setController(controller)
      frame.setContentPane(panel)
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
      frame.pack() //Se ajusta automaticamente
      frame.setVisible(true)
      panel.setStatusMessage("GUI creada")
    })
  }
}
