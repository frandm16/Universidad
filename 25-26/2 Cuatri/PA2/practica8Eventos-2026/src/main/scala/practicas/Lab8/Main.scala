package practicas.Lab8

import javax.swing.*

object Main :
  def createGUI(ventana: JFrame): Unit =
    val panel = new Panel
    val ctr = new Controller(panel)
    panel.setController(ctr)
    ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    ventana.setContentPane(panel)
    ventana.pack()
    ventana.setVisible(true)

  def main(args: Array[String]): Unit =
    val ventana = new JFrame("Máximo Común Divisor")
    ventana.setBounds(100, 100, 450, 300)
    SwingUtilities.invokeLater(() =>
        try
          createGUI(ventana)
        catch
          case e: Exception =>
            System.out.println("Tarea cancelada")
    )

