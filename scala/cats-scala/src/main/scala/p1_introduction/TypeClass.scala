package p1_introduction

//Define a very simple JSON AST
sealed trait Json
case class JsObject(get: Map[String, Json]) extends Json
case class JsString(get: String) extends Json
case class JsNumber(get: Double) extends Json
case object JsNull extends Json

//type class
trait JsonWriter[A] {
  def write(valeu: A): Json
}

//type class instances

final case class Person(name: String, email: String)

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] = 
    new JsonWriter[String] {
      def write(value : String): Json = JsString(value)
    }
  implicit val personWriter: JsonWriter[Person] = 
    new JsonWriter[Person] {
      def write(value: Person) : Json = 
        JsObject(Map(
          "name" -> JsString(value.name),
          "email" -> JsString(value.email)
        ))
    }
   implicit def optionWriter[A]
     (implicit writer: JsonWriter[A]): JsonWriter[Option[A]] = 
       new JsonWriter[Option[A]] {
         def write(option: Option[A]): Json = 
           option match {
             case Some(aValue) => writer.write(aValue)
             case None         => JsNull
           }
       }
}

//type class use - 1. interface object
object Json {
  /**
   *  usage: 
   *  import JsonWriterInstances._
   *  Json.toJson(Person("Dave", "dave@example.com")
   */
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json = w.write(value)
}
//type class use - 2. interface syntax
object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]) : Json = w.write(value)
  }
}

@main def TypeClass(args: String*): Unit = {
  import JsonWriterInstances.*
  import JsonSyntax.*
  val person = new Person("dave", "dave@example.com")
  println(Json.toJson(person))
  println(person.toJson)
  println(Option("A String").toJson)
  println(Json.toJson(Option("A String")))
  println(Option(person).toJson)
  println(Json.toJson(Option(person)))
}

//TODO refator this with scala3
