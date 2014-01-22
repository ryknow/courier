resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

name := "Courier"

version := "1.0"

//Add Repository Path

// add compile dependencies on some dispatch modules
libraryDependencies ++= Seq(
  "org.jsoup" % "jsoup" % "1.6.1"
)

// Use the project version to determine the repository to publish to.
publishTo <<= version { (v: String) =>
  if(v endsWith "-SNAPSHOT")
    Some(ScalaToolsSnapshots)
  else
    Some(ScalaToolsReleases)
}