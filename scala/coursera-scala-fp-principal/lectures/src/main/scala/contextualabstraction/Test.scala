package contextualabstraction

def sort[T: Ordering](list: List[T]) = 
  val ord = summon[Ordering[T]]
  list.sortWith((a,b)=>ord.compare(a,b) < 0)

def printSorted[T: Ordering](as: List[T]) = 
  println(sort(as))

def reduce[T: Monoid](xs: List[T]): T = 
  //reduceLeft does not work for empty list
  xs.foldLeft(Monoid[T].unit)(_.combine(_))

given sumMonoid: Monoid[Int] with
  extension (x: Int) def combine(y: Int) : Int = x + y
  def unit : Int = 0

  
given prodMonoid: Monoid[Int] with
  extension (x: Int) def combine(y: Int): Int = x * y 
  def unit: Int = 1

def sum(xs: List[Int]): Int = reduce(xs)(using sumMonoid)
def product(xs: List[Int]): Int = reduce(xs)(using prodMonoid)

@main def Test : Unit = 
  println("---- test -----")
  val list = List(3,4,2,1)
  val list2 = List(1,3,2,1)
  printSorted(list)
  printSorted(List(list, list2))

  //a given instance for the outer type is constructed first and then its implicit parameters are filled in turn
  printSorted(List( List(List(3)), List(List(1))))

  //semigroup test
  println(sum(List(1,2,3,4)))
  println(product(List(1,2,3,4)))
