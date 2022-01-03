package p1_introduction;

//typeclass
trait Printable[A] {
  def format(value: A): String
}

//type class instances
object PrintableInstances {
  given IntPrintable: Printable[Int] with
    def format(value: Int): String = value.toString
  given StringPrintable: Printable[String] with
    def format(value: String): String = value
}

//object interface
object Printable {
  def format[A: Printable](value: A) : String= {
    summon[Printable[A]].format(value)
  }
  def print[A: Printable](value: A): Unit = {
    println(format(value))
  }
}

//syntax interface scala3
object PrintableSyntax {
  //use context boudn on extension
  extension[T:Printable] (value: T)
    def format: String = summon[Printable[T]].format(value)
  extension[T:Printable] (value: T)
    def print: Unit = println(format(value))
}

//syntax interface scala2
object PrintableSyntaxSc2 {
  implicit class PrintableOps[A](value: A) {
    def format2(implicit printable: Printable[A]) : String = printable.format(value)
    def print2(implicit printable: Printable[A]): Unit = println(format2)
  }
}


object PrintableApplication {
  case class Cat(name: String, age: Int, color: String) 
  object Cat {
    given CatPrintable : Printable[Cat] with
      def format(cat:Cat):String = s"${cat.name} is a ${cat.age} year-old ${cat.color} cat"
  }
}

@main def PrintableMain(args: String*) : Unit = {
  import PrintableInstances.{IntPrintable, StringPrintable}
  import PrintableApplication.{Cat}
  import PrintableSyntax.*
  import PrintableSyntaxSc2.*
  (Printable.print(3))
  (Printable.print("4"))
  val cat = Cat("Meo", 3, "white")
  Printable.print(cat)
  cat.print
  cat.print2
}
