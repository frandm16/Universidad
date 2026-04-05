

trait ImmutableSet[T] {
  def add(elem: T): ImmutableSet[T]
  def remove(elem: T): ImmutableSet[T]
  def contains(elem: T): Boolean
  def size: Int
  def isEmpty: Boolean
}

class SimpleSet[T] private(private val elems: List[T]) extends ImmutableSet[T] {
  def this(elts: T*) = this(elts.foldLeft(List[T]())((acc, elem) => if acc.contains(elem) then acc else elem :: acc))
  // def this(elts: T*) = this(elts.toList.distinct)

  def add(elem: T): ImmutableSet[T] =
    SimpleSet(if elems.contains(elem) then elems else elem :: elems)

  def remove(elem: T): ImmutableSet[T] =
    SimpleSet(elems.filter(_ != elem))

  def contains(elem: T): Boolean = elems.contains(elem)

  def size: Int = elems.size

  def isEmpty: Boolean = elems.isEmpty

  def toList: List[T] = elems

  override def equals(obj: Any): Boolean =
    obj match
      case that: SimpleSet[T] =>
        elems.forall(that.elems.contains) && that.elems.forall(elems.contains)
      // elems.foldRight(true)((elem, acc) => acc && that.elems.contains(elem)) &&
      //   that.elems.foldRight(true)((elem, acc) => acc && this.elems.contains(elem))
      case _ => false

  override def hashCode(): Int =
    elems.foldLeft(0)((acc, elem) => acc + elem.hashCode())

  override def toString: String = elems.mkString("Set(", ", ", ")")

  def union(other: SimpleSet[T]): SimpleSet[T] = {
    def aux(other: List[T], acc: List[T]): List[T] =
      other match {
        case Nil => acc
        case h :: t => aux(t, if (acc.contains(h)) acc else h :: acc)
      }
    SimpleSet(aux(other.toList, elems))
  }

  def intersection(other: SimpleSet[T]): SimpleSet[T] =
    SimpleSet(elems.filter(other.elems.contains))

  def difference(other: SimpleSet[T]): SimpleSet[T] =
    SimpleSet(elems.foldLeft(List[T]())((acc, elem) => if (other.contains(elem)) acc else elem :: acc))
}

@main def testSimpleSet(): Unit = {
  val sset = new SimpleSet[Int]()
  val set = sset.add(1).add(2).add(3).add(4)
  assert(set.contains(1), "The set should contain 1")
  assert(!sset.contains(1), "The original set should not contain 1")
  assert(set.size == 4, "The size of the set should be 4")
  assert(set.remove(1).size == 3, "The size of the set after removing 1 should be 3")
  assert(!set.remove(1).contains(1), "The set should not contain 1 after removing it")
  assert(sset.isEmpty, "The original set should be empty")
  assert(!set.isEmpty, "The set should not be empty")
  val set2 = SimpleSet(1, 2, 3, 4)
  assert(set == set2, "The two sets should be equal")
  assert(set.hashCode() == set2.hashCode(), "The hash codes of the two sets should be equal")
  println(set)
  assert(SimpleSet(1, 2, 3, 4, 5).union(SimpleSet(6, 7, 8, 9, 10)) == SimpleSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), "The union of the sets should be Set(1,2,3,4,5,6,7,8,9,10)")
  assert(SimpleSet(1, 2, 3, 4, 5, 6).intersection(SimpleSet(8, 6, 4)) == SimpleSet(4, 6), "The intersection of the set should be Set(4,5,6)")
  assert(SimpleSet(1, 2, 3, 4, 5, 6).difference(SimpleSet(6, 5, 4)) == SimpleSet(1, 2, 3), "The difference of the set should be Set(1,2,3)")
}

// Ejercicio 2

@main def testEjercicio2() = {
  val sentences2 = List(
    "FINAL: Scala is a functional language",
    "DRAFT: The power of functional programming is great",
    "DRAFT: Programming is elegant",
    "FINAL: Functional programming is elegant",
    "FINAL: Object-oriented programming is great"
  )
  val stopWords = Set("a", "the", "is", "of")

  val res1 = sentences2
    .filter(s => s.toUpperCase.startsWith("FINAL"))
    .map(_.toLowerCase.split(":")(1).trim.split(" "))
    .foldLeft(List[String]())(_++_)
    .filter(!stopWords.contains(_))
    .groupBy(identity)
    .map((k, v) => (k, v.size))
  println(res1)

  val res2 = sentences2
    .filter(s => s.toUpperCase.startsWith("FINAL"))
    .map(s => s.split(":")(1).trim.toLowerCase().split(" "))
    .flatten
    .filter(x => !stopWords.contains(x))
    .groupBy(identity)
    .map(kv => kv._1 -> kv._2.size)
  println(res2)

  val res3 = sentences2
    .filter(s => s.toUpperCase.startsWith("FINAL"))
    .flatMap(_.toLowerCase.split(":")(1).trim.split(" "))
    .filter(!stopWords.contains(_))
    .groupBy(identity)
    .map((k, v) => (k, v.size))
  println(res3)
}

