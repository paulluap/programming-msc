package contextualabstraction

enum Expr: 
  case Number(num: Int)
  case Sum(x: Expr, y: Expr)
  case Prod(x: Expr, y: Expr)
  case Var(name: String)
  /**
   * Let("x", e1, e2)
   * -> { val x = e1; e2; }
   */
  case Let(name: String, rhs: Expr, body: Expr)

import Expr._

def eval(e: Expr): Int = 
  def recur(e: Expr)(using env: Map[String,Int]): Int =  e match
    case Number(num: Int) => num
    case Sum(x: Expr, y: Expr)   => recur(x) + recur(y)
    case Prod(x: Expr, y: Expr)  => recur(x) * recur(y)
    case Var(name: String)       => env(name)
    case Let(name, rhs, body)    => recur(body)(using env + (name -> recur(rhs)))
  recur(e)(using Map())

