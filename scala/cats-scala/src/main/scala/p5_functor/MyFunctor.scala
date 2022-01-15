package p5_functor


object MyFunctorDef {
  trait MyFunctor[F[_]] { 
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }
  
  object MyFunctor {
      def apply[F[_]: MyFunctor] = summon[MyFunctor[F]]
  }
  
  extension [F[_]: MyFunctor, A](target: F[A])/*(using MyFuctor[F])*/ {
    def map[B](f: A=>B): F[B] = summon[MyFunctor[F]].map(target)(f)
  }
}

//custom type and given type class instance 
case class Wrapper[T](value: T)
import MyFunctorDef._
given MyFunctor[Wrapper] with {
    def map[A,B](box: Wrapper[A])(func: A=>B) : Wrapper[B] = {
        Wrapper(func(box.value))
    }
}

@main def MyFunctorTest = {
    println("what")
    val list1 = List(1,2,3)
    println(summon[MyFunctor[Wrapper]].map(Wrapper(3))(_*2))
    println(MyFunctor[Wrapper].map(Wrapper(3))(_*2))
    println(Wrapper(3).map(_*3))
}
