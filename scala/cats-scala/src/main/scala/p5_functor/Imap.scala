package p5_functor

trait Codec[A] { self =>
  def encode(value: A): String
  def decode(value: String): A
  def imap[B](dec: A=>B, enc: B=>A): Codec[B] = {
    new Codec[B] {
      def encode(value: B): String = self.encode(enc(value))
      def decode(value: String): B = dec(self.decode(value))
    }
  }
}

given Codec[String] with
  def encode(value: String): String = value
  def decode(value: String): String = value

given Codec[Int] = 
  summon[Codec[String]].imap(_.toInt, _.toString)

given Codec[Boolean] = 
  summon[Codec[String]].imap(_.toBoolean, _.toString)

implicit def boxCodec[A](using Codec[A]) : Codec[Box[A]] = 
  summon[Codec[A]].imap(Box(_), _.value)

def encode[A: Codec](value: A): String = summon[Codec[A]].encode(value)
def decode[A: Codec](value: String): A = summon[Codec[A]].decode(value)


@main def IMapTest() : Unit = 
  println("---- imap test ----")
  println(encode(1234))
  println(Box(1234))
  println(decode[Box[Int]]("123"))


