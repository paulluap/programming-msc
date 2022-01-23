package p9_foldable_traverse
import cats.Monoid
import scala.math.Numeric

object FoldableBasics { 
  def show[A](list: List[A]): String = 
    list.foldLeft("nil")((acc, e) => s"$e then $acc")

  /*
   * foldLeft: binary function is called repeatedly for each item,
   * the result of each call becoming the accumulator for the next.
   *
   */

  def map[A,B](list: List[A])(func: A=>B): List[B] = 
    list.foldRight(List.empty[B])((item, acc) => func(item) :: acc)

  //note, ::: Adds the elements of a given list in front of this list.
  def flatMap[A,B](list: List[A])(func: A=>List[B]): List[B] = 
    list.foldRight(List.empty[B])((item, acc) => func(item) ::: acc)

  def filter[A](list: List[A])(func: A => Boolean): List[A] = 
    list.foldRight(List.empty[A])((item, acc) =>
        if (func(item)) item :: acc 
        else acc
    )

  def sumWithNumeric[A: Numeric](list: List[A]) : A = 
    list.foldRight(Numeric[A].zero)(Numeric[A].plus)

  def sumWithMonoid[A: Monoid](list: List[A]): A = 
    list.foldRight(Monoid[A].empty)(Monoid[A].combine)

  def test: Unit = 
    println(show(Nil))
    println(show(List(1,2,3)))
    println( List(1,2,3).foldLeft(List.empty[Int])((a,i)=>i :: a) )
    println(map(List(1,2,3))(_*2))
    println(flatMap(List(1,2,3))(a => List(a, a*10, a*100)))
    println(filter(List(1,2,3))(_ % 2 == 1))
    println(sumWithNumeric(List(1,2,3)))
    println(sumWithMonoid(List(1,2,3)))
}

@main
def RunFoldableBasics: Unit = {
  FoldableBasics.test
}

