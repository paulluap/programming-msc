package p4_monoid
import cats.Monoid     //type class
import cats.instances.string._ //instances
import cats.instances.int._
import cats.instances.option._ 
import cats.syntax.semigroup._ //syntax
//unless we have a good reason to import individula instances, we can just import everythign
/*
 * import cats._
 * import cats.implicits._
 */


@main def CatsMonoid() : Unit = { 
  println(Monoid[String].combine("Hi ", "there"))
  println(Monoid[Int].combine(32, 10))
  println(Monoid[Option[Int]].combine(Option(22), Option(2)))
  println("Hi, " |+| "There !")
}
