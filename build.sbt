organization := "io.github.btomala"

name := "teapot"

scalaVersion := "2.11.12"

crossScalaVersions := Seq("2.11.12", "2.12.4")

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil
unmanagedSourceDirectories in Test := (scalaSource in Test).value :: Nil

lazy val teapot = (project in file(".")).enablePlugins(GitVersioning)

val libVersion = Map(
  "akka-http" → "10.0.11",
  "scalatest" → "3.0.5"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka"       %% "akka-http-core"           % libVersion("akka-http")
) ++ Seq(
  "com.typesafe.akka"       %% "akka-http-testkit"        % libVersion("akka-http") % "test",
  "org.scalatest"           %% "scalatest"                % libVersion("scalatest") % "test"
)

