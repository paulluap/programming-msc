package p4_monoid

trait Semigroup[A] {
  def combine(x: A, y: A): A

  //scala 3 extension 
  extension(me: A) {
    def <&&> (other: A) : A = combine(me, other)
  }
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

//object interface 
object Monoid {
  def apply[A](implicit monoid: Monoid[A]) = monoid
}
//object syntax scala2 
implicit class MonoidSyntax[A](value: A) {
  def boolOp(other: A)(implicit monoid: Monoid[A]) = monoid.combine(value, other)
}


/*
 * scala2: 
 * implicit val booleanAndMonoid: Monoid[Boolean] = 
 *   new Monoid[Boolean] {
 *     def empty: Boolean = true
 *     def combine(x: Boolean, y: Boolean): Boolean = x && y
 *   }
 */
given booleanAndMonoid: Monoid[Boolean] with
  def empty: Boolean = true
  def combine(x: Boolean, y: Boolean): Boolean = x && y

//given booleanOrMonoid: Monoid[Boolean] with
  //def empty: Boolean = false
  //def combine(x: Boolean, y: Boolean): Boolean = x || y
  
@main def TestMonoid() : Unit = { 
  val m = Monoid[Boolean].combine(true, true)
  assert (m == true)
  //new scala 3 extesion methods (or syntax)
  assert (false == true <&&> false) //TODO how extension methods in trait are resolved
  assert (false == (true boolOp false))
}
