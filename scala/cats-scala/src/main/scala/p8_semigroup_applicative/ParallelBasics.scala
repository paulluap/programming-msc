package p8_semigroup_applicative
import cats.Semigroupal
import cats.Parallel
import cats.instances.either._
import scala.util.Either
import cats.syntax.apply._ //for tupled
import cats.syntax.parallel._ //for tupled

@main def ParalleBasics: Unit = { 
  type ErrorOr[A] = Either[Vector[String], A]
  val error1 : ErrorOr[Int] = Left(Vector("error 1"))
  val error2 : ErrorOr[Int] = Left(Vector("error 2"))
  val right1 : ErrorOr[Int] = Right(1)
  val right2 : ErrorOr[Int] = Right(2)

  println( Semigroupal[ErrorOr].product(error1, error2))
  val errorTupled : ErrorOr[(Int, Int)]= (error1, error2).tupled
  println(errorTupled)
  val errorParTupled : ErrorOr[(Int, Int)]= (error1, error2).parTupled
  println(s"error ParTupled: ${errorParTupled}") //Left(Vector(error1, error2))

  val rightTupled : ErrorOr[(Int, Int)] = (right1, right2).tupled 
  println("rightTupled " + rightTupled) //Right(1,2)

  println("rightParTUpled " + (right1, right2).parTupled) //Right(1,2)
  println("right1 error1 tupled: "  + (right1, error1).tupled) //Left(Vector(error 1)
  println("error1 error1 tupled: "  + (error1, right1).tupled) //Left(Vector(error 1)

  println(s"par mapN to right ${(right1, right2).parMapN(_ + _)}")
  println(s"mapN to right ${(right1, right2).mapN(_ + _)}")

  println(s"parMapN to error ${(right1, error1).parMapN(_ + _)}")

}
