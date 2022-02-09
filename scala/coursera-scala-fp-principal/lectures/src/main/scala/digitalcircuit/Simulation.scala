package digitalcircuit

trait Simulation {
  type Action  = () => Unit 
  case class Event(time: Int, action: Action)
  private type Agenda = List[Event]
  private var agenda: Agenda = List()
  private var curtime: Int = 0

  def currentTime: Int = curtime
  def afterDelay(delay: Int)(block: => Unit): Unit =
    //println(s"$curtime + $delay ")
    val item = Event(curtime + delay, () => block)
    agenda = insert(agenda, item)
    //println(agenda.map(_.time))

  def insert(agenda: Agenda, item: Event) : Agenda = 
    agenda match
      case first :: rest if first.time <= item.time => 
        first :: insert(rest, item)
      case _ =>  //Nil or item.time < first.time
        item :: agenda

  def loop : Unit = 
    agenda match 
        case first :: rest =>
          agenda = rest 
          curtime = first.time
          first.action()
          loop 
        case Nil => ()

  def run(): Unit = 
    afterDelay(0) {
      println("*** simulation started time = " + currentTime + " ***")
    }
    loop

}
