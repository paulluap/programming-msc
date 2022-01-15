package p6_monad

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._ //for tell
import cats.Applicative
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

type Logged[A] = Writer[Vector[String], A]

object ShowYourWorking {
  def slowly[A](body: =>A) : A= 
    try body finally Thread.sleep(100)

  def factorial(n: Int) : Logged[Int] = {

      val ans = 
          if (n == 0) 1.pure[Logged]
          else slowly( factorial(n - 1).map(_ * n))

      ans.flatMap(v => Vector(s"fact $n $v").tell.map(_=>v))
  }

  def factorial2(n: Int) : Logged[Int] = {
      for {
          ans <- if (n==0) {
              1.pure[Logged]
          } else {
              slowly(factorial2(n - 1).map(_ * n))
          }
          _ <- Vector(s"fact $n $ans").tell
      } yield ans
  }



  def test : Unit = { 
      val r = Await.result(Future.sequence(Vector(
        Future(factorial2(5)),
        Future(factorial2(5))
      )), 5.seconds)
      r.foreach(r=>println(r.run))
  }

}

@main def WriterDemo(): Unit = {
    val w = Writer(Vector(
        "It was the best of times",
        "It was the worst of times"
    ), 1859)
    //println(w)
    //pure -> has only result 
    //println(123.pure[Logged]) //WriterT(Vector(), 123)

    //tell -> only log
    //println(Vector("msg1", "msg2", "msg3").tell) //prints 

    // val writer1 = for {
    //     a <- 10.pure[Logged]
    //     _ <- Vector("a", "b", "c").tell
    //     b <- 32.writer(Vector("x", "y", "z"))
    // } yeild a

    // println(writer1)
    ShowYourWorking.test

    val writer1 = for {
        a <- 10.pure[Logged]
        _ <- Vector("a", "b", "c").tell
        b <- 32.writer(Vector("x", "y", "z"))
    } yield a + b
    println(writer1.run)

    //lets use flatMap and map syntax
    val r = 10.pure[Logged]
    // .flatMap(i=>Vector("a", "b", "c").tell)
    // .map(x=>x.getClass)

}
