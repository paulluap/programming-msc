package p5_functor
import cats.Functor
import cats.instances.list._
import cats.syntax.functor._
/*
 * functor laws:
 * * Identity : fa.map(a => a) == fa
 * * Composition: fa.map(g(f(_))) == fa.map(f).map(g)
 *
 */
//trait Functor[F[_]] {
 // def map[A,B](fa: F[A])(f: A=>B) : F[B]
//}

given Functor[Option] with
  def map[A,B](value: Option[A])(func: A=>B): Option[B] = {
    println("option functor called")
    value.map(func)
  }

  //TODO: can we use context bound?
def doMath[F[_]](start: F[Int])
  (using Functor[F]): F[Int] = start.map(n => n + 1 * 2)
//define a fucntor for option
//implicit val optionFu


//exercise:
sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

object Tree{
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)
}

@main def FunctorTest(): Unit = {
  println(doMath(Option(20)))
  println(doMath(List(1,2,3)))

  val tree = Tree.branch(
    Tree.branch(
      Tree.branch(
        Tree.leaf(1), Tree.leaf(2)), Tree.leaf(3)), Tree.leaf(4)
  )

  given Functor[Tree] with 
    def map[A,B](tree: Tree[A])(func: A=>B): Tree[B] = {
      tree match {
        case Leaf(v) => Leaf(func(v))
        case Branch(left, right) => Branch(map(left)(func), map(right)(func))
      }
    }

  println(tree)
  println(doMath(tree))
  println(tree.map(_*2))

}
