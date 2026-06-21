package examenJulio

package object esqueletos {

  def thread(body: =>Unit):Thread = {
    val t = new Thread {
      override def run = body
    }
    t.start()
    t
  }
  def parallel[A,B](a: =>A,b: =>B):(A,B) =
    var va = null.asInstanceOf[A]
    var vb = null.asInstanceOf[B]
    val ha = thread{
      va = a
    }
    val hb = thread{
      vb = b
    }
    ha.join()
    hb.join()
    (va,vb)

  def log(str:String) =
    println(s"${Thread.currentThread().getName}:$str")
}

