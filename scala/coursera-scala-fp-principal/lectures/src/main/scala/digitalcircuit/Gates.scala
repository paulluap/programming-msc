package digitalcircuit

trait Gates extends Simulation {
  def InverterDelay: Int
  def AndGateDelay: Int 
  def OrGateDelay: Int

  //state and assigments make our mental model of computation more complicated
  //In particular, we lose referential transparency, (substition model now work) ,(lose tools to reason about program)
  //On the oather hand, assignment allow us to formulate certain program in an elegant way 

  //Moral: use purely functional model whenever you can, use state reponsibly when you must
  class Wire {
    private var sigVal = false
    //a system is represented by a mutable list of actions
    //the effect of actions, when they're called
    //changes the state of object, and can also
    //install other actions to be executed in the future
    private var actions: List[Action] = List()
    def getSignal: Boolean = sigVal 

    def setSignal(s: Boolean): Unit = 
      if (s != sigVal) {
        sigVal = s
        actions foreach (_())
      }

    def addAction(a: Action) : Unit =//a exected at each change of the transported signal 
      actions = a :: actions
      a()
  }

  //inverter install an action on its input wire 
  //the action produces the inverse of the input on the output wire
  def inverter(input: Wire, output: Wire): Unit  = 
    def inverterAction(): Unit = {
      val inputSig = input.getSignal
      afterDelay(InverterDelay) { 
        output setSignal !inputSig
      }
    }
    input addAction inverterAction
  
  def andGate(in1: Wire, in2: Wire, output: Wire): Unit =
    def addAction() : Unit = {
      val in1Sig = in1.getSignal
      val in2Sig = in2.getSignal
      afterDelay(AndGateDelay) { 
        output setSignal (in1Sig & in2Sig)
      }
    }
    in1 addAction addAction
    in2 addAction addAction
  
  def orGate(in1: Wire, in2: Wire, output: Wire): Unit  = 
    def orAction() : Unit = {
      val in1Sig = in1.getSignal
      val in2Sig = in2.getSignal
      afterDelay(OrGateDelay) { 
        output setSignal (in1Sig | in2Sig)
      }
    }
    in1 addAction orAction
    in2 addAction orAction
  
  //prob to examin the wire
  def prob(name: String, wire: Wire) : Unit = 
    def probAction(): Unit = 
      println(s"${name} ${currentTime} value = ${wire.getSignal}")

    wire addAction probAction
}
