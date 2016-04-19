name := "reactive-rabbit-example"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.4",
  "com.typesafe.akka" %% "akka-stream" % "2.4.4",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test")

libraryDependencies += "io.scalac" %% "reactive-rabbit" % "1.1.0"

