package p6_monad
import cats.data.State

object StateMonadBasics {
    //State[S, A], S => (S,A)

    def test : Unit = {
        val step1 = State[Int, String]{ num =>
            val ans = num + 1
            (ans, s"Result of step1: $ans")
        }
        val step2 = State[Int, String] {num => 
            val ans = num * 2
            (ans, s"Result of step2: $ans")
        }

        val both2 = step1
        .flatMap(ans => step2.map(ans2 => (ans, ans2)))

        val both = for {
            a <- step1
            b <- step2
        } yield (a,b)
        
        println(both.run(20).value) 
        //(43, (Result of Stap1: 21, Result of step2: 42))
        println(both2.run(20).value) //9
    }
}

object PostOrderCalculator {
    type CalcState[A] = State[List[Int], A]

    def evalOne(sym: String): CalcState[Int] = 
        sym match {
            case "+" => operator( _ + _)
            case "-" => operator(_ - _)
            case "*" => operator(_ * _)
            case "/" => operator(_ / _)
            case num => operand(num.toInt)
        }

    //push num onto stack
    def operand(num: Int): CalcState[Int] = {
        State[List[Int], Int] { stack => 
            (num :: stack, num)
        }
    }

    def operator(func: (Int, Int) => Int) : CalcState[Int]= {
        State[List[Int], Int] {
            case b :: a :: tail => 
                val ans = func(a,b)
                (ans :: tail, ans)
            case _ => sys.error("Fail!")
        }
    }

    import cats.syntax.applicative._ //for pure

    def evalAll(input: List[String]): CalcState[Int] = 
        input.foldLeft(0.pure[CalcState]) { (a,b) => 
            a.flatMap(_ => evalOne(b))
        }

    def evalAll(input: String): Int =
        evalAll(input.split(" ").toList).runA(Nil).value


    def test = {
        println(evalOne("42").run(Nil).value)
        val program = for {
            _ <- evalOne("1")
            _ <- evalOne("2")
            ans <- evalOne("+")
        } yield ans
        println(program.runA(Nil).value)
        println(evalAll("1 2 + 3 *"))
        val bigProgram = for {
            _ <- evalAll(List("1",  "2",  "+"))
            _ <- evalAll(List("3",  "4",  "+"))
            ans <- evalOne("*")
        } yield ans
        println(bigProgram.runA(Nil).value)
    }

}

@main def StateMonad : Unit = 
    StateMonadBasics.test
    PostOrderCalculator.test
