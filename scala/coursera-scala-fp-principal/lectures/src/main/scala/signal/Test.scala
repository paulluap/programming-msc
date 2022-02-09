package signal

class BankAccount(val name: String) { 
  val balance = Var(0)
  println(s"new BankAccount(${name}) [${balance.hashCode}] ")

  def deposite(amount: Int): Unit = 
    if (amount > 0) {
      val b = balance() //Signal.apply ()
      balance() = b + amount //Signa..update
    }

  def withdraw(amount: Int): Unit = 
    if (0 < amount && amount <= balance()) {
      val b = balance()
      balance() = b - amount 
    }else throw new Error("insufficient funds")
}

@main def Test : Unit = 
  println("testing singal =====  ")

  def consolidated(accts: List[BankAccount]): Signal[Int] = 
    Signal(accts.map(_.balance/*.apply*/()).sum)

  val a = new BankAccount("a")
  val b = new BankAccount("b")
  //println("-------------- create derived signal ---------------")
  val c = consolidated(List(a,b))
  //println(s"observing sig : [${c.hashCode}]")

  //c()

  
  //println("------------------- updating a --------------------")
  a deposite 20 
  //println("------------------- updating a -------------------- END ")
  println(c())
  b deposite 30 
  println(c())

  //val xchange = Signal(246.00)
  //val inDollar = Signal(c() * xchange())
  //inDollar()

  //println(inDollar())
  //b withdraw 10
  //println(inDollar())

