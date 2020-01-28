lazy val commonSettings = Seq(
  name := "SpigotMagicWandTool",
  version := "0.1",
  scalaVersion := "2.13.1",
  resolvers ++= Seq(
    "spigot-repo" at "https://hub.spigotmc.org/nexus/content/repositories/snapshots/",
    "bungeecord-repo" at "https://oss.sonatype.org/content/repositories/snapshots",
    Resolver.jcenterRepo,
    Resolver.sonatypeRepo("releases"),
    Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")
  ),
  libraryDependencies ++= Seq(
    "org.spigotmc" % "spigot-api" % "1.15.1-R0.1-SNAPSHOT",
    "org.scalactic" %% "scalactic" % "3.1.0",
    "org.scalatest" %% "scalatest" % "3.1.0" % "test",
    "org.specs2" %% "specs2-mock" % "4.8.1" % "test",
    "org.mockito" %% "mockito-scala-cats" % "1.11.0" % "test"
  )
)

lazy val main = (project in file("."))
  .settings(commonSettings: _*)

lazy val api = (project in file("api"))
  .dependsOn(main)
  .settings(commonSettings: _*)
  .settings(
    excludeFilter in unmanagedResources := "plugin.yml"
  )