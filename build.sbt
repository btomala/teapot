net.virtualvoid.sbt.graph.Plugin.graphSettings

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

name := "http-mock"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

val Version = (
 "akka-http"  → "1.0-RC2",
 "scalaTest" → "2.2.4"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka"       %% "akka-http-scala-experimental"          % Version("akka-http"),
  "com.typesafe.akka"       %% "akka-http-testkit-scala-experimental"  % Version("akka-http") % "test",
  "org.scalatest"           %% "scalatest"                             % Version("scalaTest") % "test"
)

