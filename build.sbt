resolvers ++= Seq("Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")

// add compile dependencies on some dispatch modules
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.8.RC1" % "test",
  "org.slf4j" % "slf4j-simple" % "1.6.4",
  "org.slf4j" % "slf4j-api" % "1.6.4"
)

organization := "org.ryknow"

name := "courier"

version := "1.0"

scalaVersion := "2.10.3"

// Use the project version to determine the repository to publish to.
publishTo <<= version { (v: String) =>
  if(v endsWith "-SNAPSHOT")
    Some(ScalaToolsSnapshots)
  else
    Some(ScalaToolsReleases)
}
