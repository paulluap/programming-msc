package p9_foldable_traverse
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import cats.Applicative
import cats.syntax.applicative._ //for pure 
import cats.instances.future._ //for Applicative
import cats.instances.vector._ //for Applicative
import cats.instances.option._ //for Applicative
import cats.instances.list._ //for Monoid {pure, combine}
import cats.syntax.apply._ //for mapN
import cats.data.Validated

@main def TraveseBasicsRun : Unit = {

    val hostnames = List(
        "alpha.example.com",
        "beta.example.com",
        "gamma.demo.com"
    )

    def getUptime(hostname: String) : Future[Int] = 
        Future(hostname.length * 60)


    //use fold
    val allUptimes: Future[List[Int]] = 
        hostnames.foldLeft(Future(List.empty[Int])) {
            (accum, host) => 
                val uptime = getUptime(host)
                for {
                    accum <- accum
                    uptime <- uptime
                } yield accum :+ uptime
        }

    def listTravese[F[_] : Applicative, A, B] 
        (list: List[A])(func: A => F[B]): F[List[B]] = {
            list.foldLeft(List.empty[B].pure[F]) {(accum, item) => 
                (accum, func(item)).mapN(_ :+ _)
            }
        }

    def listSequence[F[_]: Applicative, B]
        (list: List[F[B]]): F[List[B]] = 
            listTravese(list)(identity)

    //travese with option,  based on flatMap, some one None results in None
    def process(inputs: List[Int]) = listTravese(inputs)(n => if(n%2==0) Some(n) else None)

    //travese with validated
    type ErrorsOr[A] = Validated[List[String], A]
    def process2(inputs: List[Int]): ErrorsOr[List[Int]] = 
        listTravese(inputs) {
            n => {
                if (n%2 == 0){
                    Validated.valid(n)
                } else{
                    Validated.invalid(List(s"$n is not even"))
                }
            }
        }


    println(Await.result(allUptimes, 1.second))
    val alluptimes2 : Future[List[Int]] = Future.traverse(hostnames)(getUptime)
    println(Await.result(alluptimes2, 1.second))
    println(Await.result(listTravese(hostnames)(getUptime), 1.second))


    println(listSequence(List(Vector(1,2), Vector(3,4))))
    //List[Fututure[A]] => Future[List[A]]
    println(process(List(2,4,6)))
    println(process(List(1,2,3)))

    println(process2(List(2,4,6)))
    println(process2(List(1,2,3)))
}