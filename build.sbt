net.virtualvoid.sbt.graph.Plugin.graphSettings

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

name := "http-mock"

version := "0.1"

scalaVersion := "2.11.6"

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

val libVersion = Map(
 "akka-http" → "1.0-RC2",
 "scalaTest" → "2.2.4",
  "dispatch" → "0.11.2"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka"       %% "akka-http-scala-experimental"          % libVersion("akka-http"),
  "com.typesafe.akka"       %% "akka-http-testkit-scala-experimental"  % libVersion("akka-http") % "test",
  "net.databinder.dispatch" %% "dispatch-core"                         % libVersion("dispatch") % "test",
  "org.scalatest"           %% "scalatest"                             % libVersion("scalaTest") % "test"
)

