
package contextualabstraction
/*
 *
 * the papers have already been given a score by reviewers.
 * to discuess, reviewers need to see various pieces of information about the papers
 * some reviewers are also author of papers
 * an author of a paper should never see at this phase the score the paper received from other reviewers
 *
 *
 * Consequences: Eevery query of the conference needs to know who is seeing the results
 * of the operation and this needs to be progated
 *
 * for a given toplevel query the set of persons seeing its results will largely stay the same
 *
 * but it can change, for instance when a reviewer delegates part of the task to another person
 *
 */
case class Person(name: String)
case class Paper(title: String, authors: List[Person], body: String)

object ConfManagement:

  //only here we know viewers is Set[Person]
  opaque type Viewers = Set[Person]
  def viewers(using vs: Viewers) = vs

  type Viewed[T] = Viewers ?=> T

  class Conference(ratings: (Paper, Int)*): 

    private val realScore: Map[Paper, Int] = ratings.toMap

    def papers: List[Paper] = ratings.map((p,_)=>p).toList

    //if one of the viewers is also an author of the paper, the score is masked, returning -100
    def score(paper: Paper): Viewed[Int] = 
      if paper.authors.exists(viewers.contains) then -100
      else realScore(paper)

    def rankings: Viewed[List[Paper]] = 
      val res : List[Paper]= papers.sortBy(score(_)).reverse
      res

    def ask[T](p: Person, query: Viewed[T]) = 
      query(using Set(p))

    def delegateTo[T](p: Person, query: Viewed[T]): Viewed[T]=
      query(using viewers + p)


  end Conference
end ConfManagement

@main def TestConfManagement: Unit = 
  import ConfManagement._
  val Smith = Person("Smith")
  val Peters = Person("Peters")
  val Abel = Person("Abel")
  val Black = Person("Black")
  val Ed = Person("Ed")
  val conf = Conference(
    Paper("How to grow beans", List(Smith, Peters), "...") -> 92, 
    Paper("Organic gardening", List(Abel, Peters), "...") -> 83,
    Paper("Composing done right", List(Black, Smith), "...") -> 99,
    Paper("The secret life of snails", List(Ed), "...") -> 77)

  //which authors have at leat two papers with a score over 80
    def highlyRankedProlificAuthors(asking: Person): Set[Person] = 
      def query: Viewed[Set[Person]] = 
        val highlyRanked =  
          conf.rankings.takeWhile(conf.score(_) > 80).toSet
        //if some cheat, conf.rankings(Set())..., can be fixed by making viewers type opache
        //then we are forced to use the only viewers here, since we cannot create one
        for
          p1: Paper <- highlyRanked
          p2: Paper <- highlyRanked
          author <- p1.authors
          if p1 != p2 && p2.authors.contains(author)
        yield author
      conf.ask(asking, query)

    def testAs(person: Person) = 
      highlyRankedProlificAuthors(asking=person)
         .map(_.name).mkString(", ")

  println(testAs(Black))  //Peters
  println(testAs(Smith))  //
  println(testAs(Abel))   //Smith
  println(testAs(Ed))   //Smith, Peters


