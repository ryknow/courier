resolvers ++= Seq("Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")

// add compile dependencies on some dispatch modules
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.0" % "test",
  "io.netty" % "netty-all" % "4.0.15.Final",
  "com.typesafe" %% "scalalogging-slf4j" % "1.0.1"
)

organization := "org.ryknow"

name := "courier"

version := "1.0"

scalaVersion := "2.10.3"