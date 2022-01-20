package p7_monadtransformer

import cats.data.{Writer, EitherT}
import scala.concurrent.Future
import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object GetPowerLeveEx { 
    //type Response[A] = Future[Either[String,A]]
    type Response[A] = EitherT[Future, String, A]

    val powerLeves = Map(
        "Jazz" -> 6,
        "Bumblebee" -> 8, 
        "Hot Rod" -> 10
    )

    def getPowerLevel(autobot: String): Response[Int] = 
        powerLeves.get(autobot) match {
            case Some(avg) => EitherT.right(Future(avg))
            case None => EitherT.left(Future(s"$autobot unreachable"))
        }

    def canSpeicialMove(ally1: String, ally2: String): Response[Boolean] = 
        for {
            p1 <- getPowerLevel(ally1)
            p2 <- getPowerLevel(ally2)
        } yield (p1 + p2) > 15

    def tacticalReport(ally1: String, ally2: String) : String = 
        Await.result(canSpeicialMove(ally1, ally2).value, 1.second) match {
            case Left(msg) => s"Comms error: $msg"
            case Right(true) => s"$ally1 and $ally2 are ready to roll out !"
            case Right(false) => s"$ally1 and $ally2 need a recharge"
        }

    def test : Unit = {
        println(tacticalReport("Jazz", "Bumblebee")) //need recharge
        println(tacticalReport("Bumblebee", "Hot Rod")) //roll out
        println(tacticalReport("Jazz", "Ironhide")) //Comms error
    }
}

object MonadTransformerPattern {

    type Logged[A] = Writer[List[String], A]

    def parseNumber(str: String): Logged[Option[Int]] = 
        util.Try(str.toInt).toOption match {
            case Some(num) => Writer(List(s"Read $str"), Some(num))
            case None      => Writer(List(s"Failed on $str"), None)
        }

    def addAll(a: String, b: String, c: String): Logged[Option[Int]] = {
        //local use of monad transformer
        import cats.data.OptionT
        val result = for {
            a <- OptionT(parseNumber(a))
            b <- OptionT(parseNumber(b))
            c <- OptionT(parseNumber(c))
        } yield a + b + c
        result.value
    }

    def test: Unit = {
        val result = addAll("1", "2", "3")
        println(result)
    }
}


@main
def monadTransformerPatternTest: Unit = {
    MonadTransformerPattern.test
    GetPowerLeveEx.test

}