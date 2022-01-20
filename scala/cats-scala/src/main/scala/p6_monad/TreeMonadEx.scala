package p6_monad
import cats.Monad
import cats.syntax.monad._
import cats.syntax.flatMap._
import cats.syntax.functor._ //for map
import p6_monad.TreeMonadEx.Tree

object TreeMonadEx {

    sealed trait Tree[+A]
    final case class Branch[A](
        left: Tree[A],
        right: Tree[A]
    ) extends Tree[A]
    final case class Leaf[A](
        value: A
    ) extends Tree[A]

    def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = 
        Branch(left, right)
    def leaf[A](value: A): Tree[A] = 
        Leaf(value)


    given Monad[Tree] with {
        def flatMap[A,B](tree: Tree[A])(f: A=>Tree[B]): Tree[B] = 
            tree match {
                case Branch(l, r) => 
                    branch(flatMap(l)(f), flatMap(r)(f))
                case Leaf(value) =>
                    f(value)
            }

        def pure[A](v: A): Tree[A] = 
            Leaf(v)
        
        //TODO: the tail rec version is diffcult
        def tailRecM[A,B](a: A)
            (func: A=>Tree[Either[A,B]]): Tree[B] = 
            flatMap(func(a)) {
                case Left(value) => tailRecM(value)(func)
                case Right(value) => Leaf(value)
            }
    }


    /*
           o
          / \
         o   3
        / \
       1   2

    */
    def test: Unit = {
        val tree = branch(
            branch(
                leaf(1), 
                leaf(2)
            ),
            leaf(3)
        )

        println(tree.map(_*2))

        println(tree.flatMap(x => branch(leaf(x-1), leaf(x+1))))

        val t2 = for {
            a <- leaf(100)
            b <- branch(leaf(a-10), leaf(a+10))
            c <- leaf(b+3) //what does this mean ??
        } yield c
        println(t2)
        
    }

}

@main def TreeMonadExRun : Unit  ={
    TreeMonadEx.test
}