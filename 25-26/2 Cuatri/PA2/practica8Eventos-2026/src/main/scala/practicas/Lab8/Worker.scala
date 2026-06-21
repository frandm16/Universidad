package practicas.Lab8

import java.util.concurrent.CancellationException
import javax.swing.SwingWorker
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters.*
import scala.util.Random

class Worker(n: Int, panel: IPanel) extends SwingWorker[Unit, String]:
  private val random = new Random()

  override def doInBackground(): Unit =
    for i <- 1 to n if !isCancelled do
      val a = random.between(2, 100000)
      val b = random.between(2, 100000)

      val left = new Factorizator(a)
      val right = new Factorizator(b)

      left.start()
      right.start()
      left.join()
      right.join()

      val gcd = Factorizator.getGCD(
        ListBuffer.from(left.factors),
        ListBuffer.from(right.factors)
      )

      publish(s"$i. mcd($a, $b) = $gcd")
      setProgress((i * 100) / n)

  override def process(chunks: java.util.List[String]): Unit =
    panel.writeMCD(chunks.asScala.toList)

  override def done(): Unit =
    try
      get()
      if !isCancelled then
        panel.setStatusMessage(s"Calculados $n MCD.")
        panel.updateProgress(100)
    catch
      case _: CancellationException =>
        panel.setStatusMessage("Tarea cancelada")
        panel.updateProgress(0)
