net.virtualvoid.sbt.graph.Plugin.graphSettings

organization := "btomala"

name := "teapot"

scalaVersion := "2.11.6"

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil
unmanagedSourceDirectories in Test := (scalaSource in Test).value :: Nil

lazy val teapot = (project in file(".")).enablePlugins(GitVersioning)

val libVersion = Map(
 "akka-http" → "1.0-RC3",
 "scalaTest" → "2.2.5"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka"       %% "akka-http-core-experimental"           % libVersion("akka-http")
) ++ Seq(
  "com.typesafe.akka"       %% "akka-http-testkit-experimental"        % libVersion("akka-http") % "test",
  "org.scalatest"           %% "scalatest"                             % libVersion("scalaTest") % "test"
)

