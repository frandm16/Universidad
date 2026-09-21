package examenJulio

import scala.annotation.tailrec

//Ejercicio 1
class SortedList[T <: Ordered[T]] private (private val elements: List[T]) {
  // Constructor sin argumentos
  def this() = this(Nil)

  def add(item: T): SortedList[T] =
    def addTo(list: List[T]): List[T] =
      list match
        case Nil => List(item)
        case x :: xs if x < item => x :: addTo(xs)
        case other => item :: other

    new SortedList(addTo(elements))

  def merge(that: SortedList[T]): SortedList[T] =
    def mergeTail(l1: List[T], l2: List[T], acc: List[T]): List[T] =
      l1 match
        case Nil => acc.reverse ++ l2
        case x1 :: x1s =>
          l2 match
            case Nil => acc.reverse ++ l1
            case x2 :: x2s if x1 < x2 => mergeTail(x1s, l2, x1 :: acc)
            case x2 :: x2s => mergeTail(l1, x2s, x2 :: acc)

    new SortedList(mergeTail(elements, that.elements, List.empty[T]))

  override def toString: String = elements.toString

  override def equals(other: Any): Boolean =
    other match
      case that: SortedList[_] => elements == that.elements
      case _ => false

  override def hashCode: Int = elements.hashCode
}

//Ejercicio 2
def wordLengthHistogram(input: String): Map[Int, Int] =
  input.split(" ")
    .map(_.length)
    .groupBy(identity)
    .map((k, v) => k -> v.length)

wordLengthHistogram("Scala is fun I love functional programming Scala is good and cool")

//Ejercicio 3
def invertedIndex(docs: List[(Int, String)]):Unit =


invertedIndex(List(
  (1, "Scala is great"),
  (2, "Scala is scalable and cool"),
  (3, "Functional programming is powerful")
))

//Ejercicio 4
def isBalanced(s: String): Boolean =

isBalanced("({[]})")       // true
isBalanced("({[]})()")     // true
isBalanced("({[]})(]")     // false
isBalanced("({[)]}")       // false
isBalanced("(((())))")     // true
isBalanced("[(])")         // false