package p6_monad
import cats.Id
import cats.Monad
import cats.instances.option._
import cats.instances.future._
import cats.instances.list._
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import cats.syntax.applicative._
import cats.syntax.functor._
import cats.syntax.flatMap._

def parseInt(str: String): Option[Int] = 
  scala.util.Try(str.toInt).toOption

def divide(a: Int, b: Int): Option[Int] = 
  if (b==0) None else Some(a/b)

//every monad is also a functor
//to express map in terms of flatMap
trait MyMonad[F[_]] {
  def pure[A](a: A): F[A]
  def flatMap[A,B](value: F[A])(func: A=> F[B]): F[B]
  def map[A,B](value: F[A])(func: A=>B): F[B] = {
    flatMap(value)(e=>pure(func(e)))
  }
}

object MyIdMonadExercise {
  def pure[A](value: A): Id[A] = 
    value
  def flatMap[A,B](value: Id[A])(func: A=>Id[B]): Id[B] = 
    func(value)
  def map[A,B](value: Id[A])(func: A=>B): Id[B] = 
    func(value)
}

object CreatingInstances {
  import cats.syntax.either._

  def countPositive(nums: List[Int]) = 
    nums.foldLeft(0.asRight[String])((accumulator, num) => 
        if (num > 0) accumulator.map(_+1)
        else Left("Negative. Stopping!"))

  def test = {
    //either is right biased
    val a: Either[String,Int] = 3.asRight[String] 
    val b = 4.asRight[String]
    val z = for {
      x <- a 
      y <- b
    } yield x*x + y*y
    println(s"value z is ${z}")
    println(countPositive(List(2,2,3)))
    println(countPositive(List(2,-2,3)))
    println(Either.catchOnly[NumberFormatException]("foo".toInt))
    println("Error".asLeft[Int].orElse(2.asRight[String]))
  }
}



def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] = 
  a.flatMap(x => b.map(y => x*x + y*y))
def sumSqaureUsingFor[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] = 
  for {
    x <- a
    y <- b
  } yield x*x + y*y

def stringDivideBy (aStr: String, bStr: String): Option[Int] = {
  parseInt(aStr).flatMap( aNum => 
      parseInt(bStr).flatMap( bNum => 
          divide(aNum, bNum)
      )
  )
}
def stringDivideBy2(aStr: String, bStr: String) : Option[Int] = {
  for{
       aNum <- parseInt(aStr)
       bNum <- parseInt(bStr)
       ans <- divide(aNum, bNum)
  } yield ans
}

@main def MonadTest = {
  val fm = Monad[Future]
  val future = fm.flatMap(fm.pure(1))(x => fm.pure(x+2))
  println(Await.result(future, 1.second))
  println("---- monad test")

  println(stringDivideBy("8", "4"))
  println(stringDivideBy2("8", "4"))
  println(sumSquare(List(1,2,3), List(4,5)))
  println(sumSquare(3: Id[Int], 4: Id[Int]))
  CreatingInstances.test
}
