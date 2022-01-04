package p4_monoid
import cats.Monoid     //type class
import cats.instances.int._ //instances
import cats.syntax.semigroup._ //syntax

def add[A](items: Seq[A])(implicit monoid: Monoid[A]): A = { 
  items.foldLeft(monoid.empty)(_ |+| _)
}
//scals 3 using
def add2[A](items: Seq[A])(using Monoid[A]): A = { 
  items.foldLeft(summon[Monoid[A]].empty)(_ |+| _)
}

//context bound
def add3[A: Monoid](items: Seq[A]): A = { 
  items.foldLeft(summon[Monoid[A]].empty)(_ |+| _)
}

case class Order(totalCost: Double, quantity: Double)
object Order {
  given Monoid[Order] with {
    def empty = Order(0,0)
    def combine(a: Order, b: Order) = Order(a.totalCost + b.totalCost, a.quantity + b.quantity)
  }
}


@main def SuperAddrTest() : Unit = {
  println(add(1 to 5))
  println(add2(1 to 5))
  println(add3(1 to 5))
  println(add3(1 to 5)(using summon[Monoid[Int]]))
  println(add3((1 to 2).map(i=>Order(i,i))))
}
