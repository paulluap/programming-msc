package signal

class StackableVariable[T](init: T) {
  private var values: List[T] = List(init)
  def value: T = {
    values.head
  }
  def withValue[R](newValue: T)(op: =>R): R = 
    //push
    values = newValue :: values
    //run op and pop
    try op finally {
      values = values.tail
    }
}

class Signal[T] (expr: => T)  {
  private var myExpr: () => T = _ 
  private var myValue: T = _
  private var observers: Set[Signal[_]] = Set()
  update(expr)
  //TODO currentValue
  //expression
  //observers : the other signals that depend on the value
  //if the signal changes, all the observers re-evaluate
  import Signal._
  protected def update(expr: => T): Unit = 
    myExpr = () => expr
    computeValue()

  protected def computeValue(): Unit = 
    //println(s"[$hashCode] - computeValue ")
    val newValue: T = caller.withValue(this)(myExpr())
    //println(s"[$hashCode] - computeValue END ${myValue} : ${newValue}")
    if (myValue != newValue) {
      myValue = newValue
      val obs = observers
      //println(s"[${this.hashCode}] notifying: --> ${observers.map(_.hashCode)}")
      observers = Set()
      obs.foreach(_.computeValue())
    }


  def apply(): T = 
    observers += caller.value
    //println("[" + this.hashCode + "] " + "apply, add observer, " + caller.value.hashCode)
    assert(!caller.value.observers.contains(this), "cyclic singal definition")
    myValue
}

//sential object
object NoSignal extends Signal[Nothing](???) { 
  override def computeValue() : Unit = ()
  override def update(expr: => Nothing) : Unit = ()
}

object Signal {
  //private val caller2 = util.DynamicVariable[Signal[_]](NoSignal)
  private val caller = new StackableVariable[Signal[_]](NoSignal)
  def apply [T](expr: => T) = new Signal(expr)
}


class Var[T](expr: => T) extends Signal[T](expr) {
  //exposes to public
  override def update(expr: => T): Unit = super.update(expr)
}

object Var {
  def apply[T](expr: => T) = new Var(expr)
}


//maybe same thing in vue! https://deepsource.io/blog/reactivity-in-vue/
