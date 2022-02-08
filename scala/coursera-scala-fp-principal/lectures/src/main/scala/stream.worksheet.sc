object primes {
  /**
   * The Sieve of Eratosthenes
   */
  //2,3,4,5,6,7,8,9,10,11
  //2,3,  5   7   9    11
  //2,3,  5   7        11

  def from(n: Int): Stream[Int] = n #:: from(n+1)
  val nats = from(0)
  val m4s = nats.map(_*4)

  def sieve(s: Stream[Int]): Stream[Int] = 
    s.head #:: sieve(s.tail.filter(_ % s.head != 0))

  val primes = sieve(from(2))
}

object sqrt {

  def isGoodEnough(guess: Double, x: Double): Boolean = 
    (guess*guess - x).abs < 0.0001

  def sqrtStream(x:Double): Stream[Double] = 
    def improve(guess: Double) = (guess + x/guess) / 2
    lazy val guesses: Stream[Double] = 1 #:: {
      //this is only evaluated onces, when we deference the tail in the first cons
      //println("evaluate " + guesses)
      guesses.map(improve)
    }
    guesses

  /** definition of stream.map copied here
  class Stream[+A]
    override final def map[B](f: A => B): Stream[B] =
      if (isEmpty) iterableFactory.empty
      else cons(f(head), tail.map(f))
    */
}

primes.primes.take(100).toList

sqrt.sqrtStream(4).take(10).toList

sqrt.sqrtStream(4).filter(sqrt.isGoodEnough(_, 4)).take(10).toList

  
