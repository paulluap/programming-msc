package p6_monad
import cats.syntax.either._

object wrapper {
  sealed trait LoginError extends Product with Serializable
  final case class UserNotFound(username: String) extends LoginError
  final case class PasswordIncorrect(username: String) extends LoginError
  case object UnexpectedError extends LoginError
}; 
import wrapper._

case class User(username: String, password:String)

type LoginResult = Either[LoginError, User]

def handleError(error: LoginError) : Unit = {
  error match{
    case UserNotFound(u) => println(s"user not found: $u")
    case PasswordIncorrect(u) => println(s"password incorrect: $u")
    case UnexpectedError => println(s"unexpected error")
  }
}


@main def ErrorHandling = {
  val result1 : LoginResult = User("dave", "passw0rd").asRight
  val result2 : LoginResult = UserNotFound("dave").asLeft

  println(result1)
  println(result1.fold(handleError, println))
  println(result2.fold(handleError, println))
  
}
