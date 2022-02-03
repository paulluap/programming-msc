package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()
    println(balance("(if (zero? x) max (/ 1 x))".toList))
    println(balance("())(".toList))
    println(balance("()()".toList))

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = 
    if r < 0 || c < 0 || c > r then
      0
    else if (r == 0) then
      1
    else 
      pascal(c-1, r-1) + pascal(c, r-1) 

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = 
    def balance2(open: Int, chars: List[Char]) : Boolean = 
      if open < 0 then
        false
      else if chars.isEmpty then
        open == 0
      else if chars.head == '(' then
        balance2(open+1, chars.tail)
      else if chars.head == ')' then
        balance2(open-1, chars.tail)
      else 
        balance2(open, chars.tail)
    balance2(0, chars)

     

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =  
    if money == 0 then //alright, we have changed all the money, so count 1
      1
    else if coins.isEmpty || money < 0 then
      0
    else 
      //either use current one or skip current one and use the rest, sum the two possibliites
      countChange(money, coins.tail) + countChange(money - coins.head, coins)

    


