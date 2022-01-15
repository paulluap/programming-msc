package p6_monad
/*
Reader: sequence operations that depend on some input.

Dependency injection
*/
import cats.data.Reader
import cats.syntax.applicative._


object ReaderMonadBasics {
    case class Cat(name: String, favoriteFood: String)
    //create a Reader[A,B] from a function A=>B using the Reader.apply constructor

    def run : Unit = {
        val catName: Reader[Cat,String] = Reader(cat => cat.name)
        println(catName.run(Cat("Garfield", "lasagne")))
    
        //create a set of readers that accept the same type of confiugration, combine 
        //them with map and flatMap, and then call run to inject the config at the end
        val greetKitty : Reader[Cat,String] = catName.map(name=>s"Hello ${name}")
        println(greetKitty.run(Cat("HealthCliff", "junk food")))
    
        val feedKitty : Reader[Cat,String] = Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")
    
        val greetAndFeed: Reader[Cat,String] = 
            for {
                greet <- greetKitty
                feed <- feedKitty
            } yield s"$greet. $feed"
        val r: String = greetAndFeed(Cat("Garfield", "lasagne"))
        println(r)
    }
}

object ReaderMonadEx {
    //login system
    final case class Db ( 
        usernames: Map[Int, String],
        passwords: Map[String,String]
    )

    type DbReader[A] = Reader[Db, A]
    
    def findUsername(userId: Int) : DbReader[Option[String]] = 
        Reader(db=>db.usernames.get(userId))

    def checkPassword(username: String, password: String): DbReader[Boolean] = 
        Reader((db=>db.passwords.get(username).contains(password)))
    
    def checkLogin(userId: Int, password: String): DbReader[Boolean] = 
        for {
            userName <- findUsername(userId)
            passwordOk <- userName.map{
                username => checkPassword(username, password)
            }.getOrElse{
                false.pure[DbReader]
            }
        } yield passwordOk

    def run : Unit = {
        val users = Map(
            1 -> "dade",
            2 -> "kate",
            3 -> "margo"
        )
        val passwords = Map(
            "dade" -> "zerocool",
            "kate" -> "acidburn",
            "margo" -> "secret"
        )
        val db = Db(users, passwords)
        println(checkLogin(1, "zerocool").run(db))
        println(checkLogin(4, "junk").run(db))
    }
}

@main def ReaderMonad: Unit = {
    ReaderMonadBasics.run
    ReaderMonadEx.run
}