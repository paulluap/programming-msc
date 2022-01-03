package p2_meetcats
import cats.Show
import cats.syntax.show._
import cats.syntax.eq._
import cats.Eq


case class Cat(name: String, age: Int, color: String) 
given Show[Cat] with
  def show(cat:Cat):String = s"${cat.name} is a ${cat.age} year-old ${cat.color} cat"

given Eq[Cat] with
  def eqv(cat1: Cat, cat2: Cat): Boolean = {
    cat1.name === cat2.name &&
    cat1.age === cat2.age &&
    cat1.color === cat2.color
  }


@main def CatShow() : Unit = {
  val cat = Cat("meo", 3, "white")
  val cat1 = Cat("meo", 3, "white")
  val cat2 = Cat("meo", 3, "white")
  val cat3 = Cat("meo3", 3, "white")
  println(cat.show)
  println(cat1 === cat2)
  println(cat1 === cat3)
  println(Option(cat1) === Option(cat2))
}
