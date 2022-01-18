package p6_monad
import scala.concurrent.Future
import cats.data.{ OptionT, EitherT }
import cats.instances.list._     //for Monad
import cats.syntax.applicative._ //for pure
import cats.instances.either._   //for Monad

@main def MonadTransformer : Unit = {
  //list option example
  type ListOption[A] = OptionT[List,A]

  //transforms List[Option[A]] into a single monad
  val result1: ListOption[Int] = OptionT(List(Option(10)))
  //result1 is a light wrapper around List[Option[T]]
  assert(result1.value.head.get == 10)
  val result2: ListOption[Int] = 32.pure[ListOption]
  assert(result2.value.head.get == 32)

  println(result1.flatMap(x=>result2.map(_ + x)))

  type ErrorOr[A] = Either[String,A]
  type ErrorOrOption[A] = OptionT[ErrorOr, A]

  val a = 10.pure[ErrorOrOption]
  val b = 32.pure[ErrorOrOption]

  println(a.flatMap(x=>b.map(_+x)))
  val z = for {
    x <- a
    y <- b
  } yield x + y
  println(z)
  println(a.value) //Right(Some(10))
  println(a.value.map(_.getOrElse(-1))) //Right(10)



  //lets creat a Future of an Either of Option, Future [ Either [ Option ....
  //Either[F[_], E, A]
  type FutureEither[A] = EitherT[Future, String, A]
  type FutureEitherOption[A] = OptionT[FutureEither, A]


}
