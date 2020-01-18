name := "SpigotMagicWandTool"

version := "0.1"

scalaVersion := "2.13.1"

resolvers += "spigot-repo" at "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"
resolvers += "bungeecord-repo" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += Resolver.jcenterRepo
libraryDependencies += "org.spigotmc" % "spigot-api" % "1.15.1-R0.1-SNAPSHOT"