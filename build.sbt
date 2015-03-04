lazy val root = (project in file(".")).
settings(
  name := "scalatest",
  organization := "org.bdgp",
  version := "0.1.0",
  scalaSource in Compile := baseDirectory.value / "src",
  scalaVersion := "2.11.5",
  exportJars := true,
  artifactPath in Compile in packageBin <<= baseDirectory { base => base / "target" / "lib" / "scalatest.jar" },
  libraryDependencies += "com.github.samtools" % "htsjdk" % "1.129"
)

TaskKey[Set[File]]("copy-libs") <<= (fullClasspath in Runtime, target) map { (cp, out) =>
  val entries: Seq[File] = cp.files
  val toDirectory: File = out / "lib"
  IO.copy( entries x flat(toDirectory) )
} triggeredBy(compile in Compile)
