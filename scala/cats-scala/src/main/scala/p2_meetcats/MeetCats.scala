package p2_meetcats
import cats.Show
import java.util.Date
import cats.syntax.show._
import cats.syntax.eq._
import cats.syntax.option._
import cats.instances.int._
import cats.instances.option._
import cats.Eq

//given Show[Date] with
  //def show(value: Date) : String = s"${value.getTime}ms since the epoch."
given Show[Date] = Show.show(date=>s"${date.getTime}ms since the epoch.")
given Eq[Date] = Eq.instance[Date]{ (date1, date2)=>
  date1.getTime() === date2.getTime() 
}

@main def MeetCats (args: String*) : Unit = {
  val showInt = Show.apply[Int]
  val showString = Show.apply[String]
  println(new Date().show)

  val eqInt = Eq[Int]
  println(eqInt.eqv(123, 123))
  println(123 === 123)
  println(Option(1) === Option.empty[Int])
  println(1.some === none[Int])
  val date = new Date()
  println(new Date() === new Date())
}
