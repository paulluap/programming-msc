package p7_monadtransformer
import cats.Monad
import cats.syntax.applicative._
import cats.syntax.flatMap._
import cats.data.{OptionT, EitherT}
import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

@main def test : Unit = {
    val m = 5.pure[Option]
    val m2 = m.fold(2.pure[Option])(a=>(a+1).pure[Option])
    println(m2)

    //builds List[Option[A]]
    type ListOption[A] = OptionT[List,A]
    val result1: ListOption[Int]= OptionT(List(Option(10)))
    val result2: ListOption[Int] =32.pure[ListOption]
    println(result1)
    println(result2)

    val r = result1.flatMap { x =>
        result2.map {y => {
            x + y
        }}
    }
    println(r)

    val z = for {
        x <- result1
        y <- result2
    } yield x + y
    println(z)


    //Either[Option[..]], 
    type ErrorOr[A] = Either[String,A]
    type ErrorOrOption[A] = OptionT[ErrorOr, A]
    val a = 10.pure[ErrorOrOption]
    val b = 32.pure[ErrorOrOption]
    val c = a.flatMap(x => b.map(y => x + y))
    val c1 = for {
        x <- a  
        y <- b
    } yield  x+y
    println(c.value) //Right(Some(42))
    println(c1.value)

    //Future[Either[Option[..]]]

    type FutureEither[A] = EitherT[Future, String, A]
    type FutureEitherOption[A] = OptionT[FutureEither, A]

    val futureEitherOr: FutureEitherOption[Int] = 
        for {
            x <- 10.pure[FutureEitherOption]
            y <- 32.pure[FutureEitherOption]
        } yield x + y

    println(Await.result(futureEitherOr.value.value, 1.second))

}