import scala.collection.mutable.ListBuffer

class recursivo:

  def search[A](l: List[A], x: A): Option[Int] =
    def aux(l: List[A], y: Int): Option[Int] =
      l match {
        case Nil => None
        case h :: t if (h == x) => Some(y)
        case _ :: t => aux(t, y+1)
      }
    aux(l, 0)


  def factorial(x : Int) : Int =
    if (x< 0) then sys.error("Numero negativo")
    else if (x == 0) then 1
    else x * factorial(x-1)

//iterativa
  def map1[A, B](l: List[A], f: A => B): List[B] =
    val lista = ListBuffer[B]()

    for e <- l do
      lista.append(f(e))

    lista.toList

//recursiva
  def map2[A, B](l: List[A], f: A => B): List[B] =
    def aux(lista: List[A], l2: List[B]): List[B] =
      lista match
        case Nil => l2
        case h :: t => aux(t, l2 :+ f(h))
    aux(l, List())

//en el foldLeft, el acumulador se pone al valor entre paréntesis, y x, toma el valor de cada posición
//multiplica todos los elementos de la lista, si se pone foldLeft(0), da 0
  val productoElementos = List(1,2,3,4).foldLeft(1)((acc,x) => acc * x)

  //devuelve el número de elementos de la lista
  val numeroDeElementos = List(1,2,3,4).foldLeft(0)((acc,_) => acc +1)

  val invertirLista = List(1,2,3,4).foldLeft(Nil)((acc,x) => x::acc)