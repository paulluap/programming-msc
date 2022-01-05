package p3_variance
import p1_introduction.{Json, JsonWriter, JsString, JsObject, JsNumber}


sealed trait Shape
case class Circle(radius: Double) extends Shape

//covariance: for outputs: 
//data that we can laer get out of a container type
val circles: List[Circle] = List()
val shapes: List[Shape] = circles

//contravariance: for inputs:
//trait JsonWriter[-A] {
//  def write(value: A): Json
//}

val shape: Shape = Circle(5)
val circle: Circle = Circle(3)

val shapeWriter: JsonWriter[Shape] = new JsonWriter[Shape] {
  def write(value: Shape): Json = JsString("shape")
}
val circleWriter: JsonWriter[Circle] = new JsonWriter[Circle] { 
  def write(value: Circle): Json = JsObject(Map(
    "radius" -> JsNumber(value.radius)
    ))
}
def format[A](value: A, writer: JsonWriter[A]): Json = 
  writer.write(value)
//JsonWriter[Shape] can be used when JsonWriter[Circle] is expected
//JsonWriter[Shape] <:(is subtype of) JsonWriter[Circle], Circle is subtype of Shape

@main def Variance() : Unit = {
  println("variance example: ")
  println(format(circle, circleWriter))
  println(format(shape, shapeWriter))
  //TODO why this is automatically contravariant ?
  println(format(circle, shapeWriter))
}
