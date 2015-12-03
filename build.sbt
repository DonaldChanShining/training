
name := "Trraining"

version := "1.0"

scalaVersion := "2.11.7"


libraryDependencies ++= {
  val akkaVersion = "2.3.11"
  val sprayVersion = "1.3.3"

  Seq(

    /* "com.typesafe.akka" %% "akka-actor" % akkaVersion,
     "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
     "com.typesafe.akka" %% "akka-contrib" % akkaVersion,*/
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    //"io.spray" %% "spray-client" % sprayVersion,
    //"com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.0-M1",

    /*  "com.typesafe.play" %% "play-slick" % "1.1.0",
      "com.typesafe.play" %% "play-slick-evolutions" % "1.1.0",
      "mysql" % "mysql-connector-java" % "5.1.35",

      "com.typesafe.slick" %% "slick" % "3.1.0",*/
    "ch.qos.logback" % "logback-classic" % "1.1.3"

  )
}

libraryDependencies ++= Seq(
  specs2,
  "org.specs2" %% "specs2-core" % "3.6.2",
  "org.specs2" %% "specs2-scalacheck" % "3.6.2",

  "org.scalatest" %% "scalatest" % "2.2.1",
  "org.scalatestplus" %% "play" % "1.4.0-M3",

  //"io.spray" %% "spray-testkit" % "1.3.3",

  "com.typesafe.akka" %% "akka-http-testkit-experimental" % "2.0-M1",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.0"



).map(_ % "test")



lazy val root = (project in file(".")).enablePlugins(PlayScala)
    