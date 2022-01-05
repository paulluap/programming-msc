package p5_functor

trait Printable[A] { self =>
  def format(value: A): String
  def contramap[B](func: B=>A): Printable[B] = 
    new Printable[B] {
      def format(value: B): String = 
        self.format(func(value))
    }
}

given Printable[Int] with 
  def format(i: Int) = s"${i}: Int"
given Printable[String] with 
  def format(s: String) = s"'${s}'"
given Printable[Boolean] with
  def format(b: Boolean) = b match
    case true => "yes"
    case flase => "no"


def format[A: Printable](value: A): String = summon[Printable[A]].format(value)

//now define an instance of Printable for Box
//
final case class Box[A](value: A)
//we already have type class for A, how to convert Printable[A] to Printable[Box[A]]
/* write out everything
implicit def boxPrintable[A: Printable]: Printable[Box[A]] = 
  new Printable[Box[A]] {
    def format(box: Box[A]) : String = 
      summon[Printable[A]].format(box.value)
  }
*/
//use contramap
implicit def boxPrintable[A: Printable]: Printable[Box[A]] = 
  summon[Printable[A]].contramap(box=>box.value)


@main def Contramap(): Unit = 
  println("contramap examples ")
  println(format(true))
  println(format("hello"))
  println(format(Box(3)))
