package practicas.Lab8

import java.awt.{BorderLayout, FlowLayout}
import java.awt.event.ActionListener
import javax.swing.*

class Panel extends JPanel with IPanel:
  private val inputLabel = JLabel("Cantidad de calculos")
  private val inputField = JTextField(8)
  private val startButton = JButton("Iniciar")
  private val cancelButton = JButton("Cancelar")
  private val progressBar = JProgressBar(0, 100)
  private val resultsArea = JTextArea(12, 40)
  private val statusLabel = JLabel("Introduce un numero y pulsa Enter o Iniciar")

  init()

  private def init(): Unit =
    setLayout(BorderLayout(8, 8))

    val controls = JPanel(FlowLayout(FlowLayout.LEFT))
    controls.add(inputLabel)
    controls.add(inputField)
    controls.add(startButton)
    controls.add(cancelButton)

    progressBar.setStringPainted(true)
    resultsArea.setEditable(false)
    resultsArea.setLineWrap(true)
    resultsArea.setWrapStyleWord(true)

    add(progressBar, BorderLayout.NORTH)
    add(controls, BorderLayout.WEST)
    add(JScrollPane(resultsArea), BorderLayout.CENTER)
    add(statusLabel, BorderLayout.SOUTH)

  override def setController(controller: ActionListener): Unit =
    inputField.addActionListener(controller)
    inputField.setActionCommand(FIELD)
    startButton.addActionListener(controller)
    startButton.setActionCommand(START)
    cancelButton.addActionListener(controller)
    cancelButton.setActionCommand(CANCEL_BUTTON)

  override def setStatusMessage(msg: String): Unit =
    statusLabel.setText(msg)

  override def getNumber: Int =
    try
      val value = inputField.getText.trim.toInt
      if value <= 0 then throw NumberFormatException()
      value
    catch
      case _: NumberFormatException =>
        JOptionPane.showMessageDialog(
          this,
          "Introduce un entero positivo.",
          "Entrada no valida",
          JOptionPane.ERROR_MESSAGE
        )
        0

  override def writeMCD(results: scala.List[String]): Unit =
    results.foreach(result => resultsArea.append(result + System.lineSeparator()))

  override def updateProgress(value: Int): Unit =
    progressBar.setValue(value)

  override def clearTextArea(): Unit =
    resultsArea.setText("")
