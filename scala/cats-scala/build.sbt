val scala3Version = "3.1.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "cats-scala",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
    "com.novocode" % "junit-interface" % "0.11" % "test",
    "org.typelevel" % "cats-core_3" % "2.7.0" % "compile"
    )
    //libraryDependencies += "org.typelevel" %% "cats-core" % "2.1.0"
    //libraryDependencies += 
  )
