net.virtualvoid.sbt.graph.Plugin.graphSettings

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

name := "http-mock"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

val libVer = Map(
 "akka-http" → "1.0-RC2",
 "scalaTest" → "2.2.4",
  "dispatch" → "0.11.2"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka"       %% "akka-http-scala-experimental"          % libVer("akka-http"),
  "com.typesafe.akka"       %% "akka-http-testkit-scala-experimental"  % libVer("akka-http") % "test",
  "net.databinder.dispatch" %% "dispatch-core"                         % libVer("dispatch") % "test",
  "org.scalatest"           %% "scalatest"                             % libVer("scalaTest") % "test"
)

