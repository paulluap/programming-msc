
package contextualabstraction

//retroactive extension, without changing then class Rational
trait Ordering[A]:
  def compare(x: A, y:A): Int
  //if we have an Ordering[T] instance in scope, that's where the extension method comes from 

//given instances will be searched in the companion object of the query type, Ordering
object Ordering {
  given Ordering[Rational] with
    def compare(x: Rational, y: Rational) = 
      val xn = x.number * y.denom
      val yn = y.number * y.denom
      if xn < yn then -1 else if xn > yn then 1 else 0
      //xn - yn overflow


  /* conditional
   *
   * given function converts ordering of [A] to ordering of [List[A]] (in scala2, it's implicit function)
   *
   * an ordering for list with element of type T exists only if there is an ordering for T
   *
   * This sort of conditional behavior is best implemented with type classes
   * Normal subtyping and inheritance cannot express this: a class either inherits a trait or doesn't
   *
   */
  //given listOrdering[A](using ord: Ordering[A]) : Ordering[List[A]] with 
  given [A: Ordering] : Ordering[List[A]] with
    def compare(xs: List[A], ys: List[A]) = (xs, ys) match
      case (Nil, Nil) => 0
      case (Nil, _) => -1
      case (_ , Nil) => 1
      case (x :: xs1, y :: ys1) =>
        val c = summon[Ordering[A]].compare(x,y)
        if c != 0 then c else compare(xs1, ys1)

  given Ordering[Int] with 
    def compare(x: Int, y: Int) : Int = x - y
}
