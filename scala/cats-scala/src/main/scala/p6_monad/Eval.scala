package p6_monad
import cats.Eval


//factorial, why this work ?
def factorial(n: BigInt): Eval[BigInt] = {
  if (n == 1) Eval.now(n)
  else Eval.defer(factorial(n - 1).map(_ * n))
}

//safer folding using Eval 
def foldRightEval[A, B](list: List[A], acc: Eval[B])(fn: (A, Eval[B])=>Eval[B]): Eval[B] = 
  list match {
    case head :: tail => {
      Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
    }
    case Nil => acc
  }

def foldRight[A,B](list: List[A], acc: B)(fn: (A, B)=>B): B = 
  foldRightEval(list, Eval.now(acc)){(head, acc)=>acc.map(fn(head, _))}.value

@main def EvalDemo() : Unit = {
  val v = foldRight((1 to 100000).toList, 0)(_ + _)
  println(v)
}
