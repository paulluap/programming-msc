package p8_semigroup_applicative
import cats.Semigroupal
import cats.instances.option._ ; //for semigroupal
import cats.syntax.apply._
import cats.Monoid

object FancyFucnctorsAndApplySyntax { 
    import cats.instances.int._ //monoid
    import cats.instances.string._ //monoid
    import cats.instances.list._ //monoid
    import cats.instances.invariant._ //for semigroupal
    import cats.syntax.apply._ //for imapN
    import cats.syntax.semigroup._ //for |+| 

    final case class Cat (
        name: String,
        yearOfBirth: Int,
        favoriteFoods: List[String]
    )
    
    val tupleToCat: (String, Int, List[String]) => Cat = 
        Cat.apply _

    val catToTuple: Cat => (String, Int, List[String]) = 
        cat => (cat.name, cat.yearOfBirth, cat.favoriteFoods)

    implicit val catMonoid: Monoid[Cat] = (
        Monoid[String],
        Monoid[Int],
        Monoid[List[String]]
    ).imapN(tupleToCat)(catToTuple)

    def test : Unit = {
        val garfield = Cat("Garfield", 1978, List("Lasagne"))
        val heathcliff = Cat("Heathcliff", 1988, List("Junk Food"))

        println(s"combined cat: ${garfield |+| heathcliff}")
    }
}

@main
def SemigroupalBasics: Unit = {
    val r = Semigroupal[Option].product(Some(123), Some("abc"))
    println(r)

    val r2 = Semigroupal[Option].product(Some(123), None)
    println(r2)

    println(
    Semigroupal.map3(Option(1), Option(2), Option(3))(_ + _ + _)
    )

    //tupled implicitly added the tuple of options
    (Option(123), Option("abc")).tupled.foreach(println(_))

    final case class Cat(name: String, born: Int, color: String)

    println((Option("Garfield"), Option(1978), Option("Orange & black")).mapN(Cat.apply)
    )

    val add: (Int, Int, Int) => Int = (a, b, c) => a + b + c
    val r3 = (Option(1), Option(2), Option(3)).mapN(add)

    FancyFucnctorsAndApplySyntax.test
}