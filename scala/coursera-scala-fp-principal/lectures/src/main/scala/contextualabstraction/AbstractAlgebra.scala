package contextualabstraction

trait SemiGroup[T]:
  extension (x: T) def combine(y: T): T
  //combine should satisfy associative, using ScalaCheck to test, or using formal/informal proof

trait Monoid[T] extends SemiGroup[T]:
  def unit: T

object Monoid:
  def apply[T](using m: Monoid[T]): Monoid[T] = m

