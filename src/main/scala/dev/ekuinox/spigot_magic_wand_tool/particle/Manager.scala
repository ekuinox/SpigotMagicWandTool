package dev.ekuinox.spigot_magic_wand_tool.particle

import dev.ekuinox.spigot_magic_wand_tool.SpigotMagicWandTool
import dev.ekuinox.spigot_magic_wand_tool.location.Location
import org.bukkit.entity.Player
import org.bukkit.{Particle, World}


class Manager(plugin: SpigotMagicWandTool) {
  import Manager._
  import collection.mutable.Map

  private val runners: Map[(Player, Location, World), Runner] = Map[(Player, Location, World), Runner]()

  def startParticle(player: Player, world: World, location: Location): Unit = {
    val runner = new Runner(plugin, world, location)
    runner.runTaskTimerAsynchronously(plugin, 0, PARTICLE_TICK_SPAN)
    runners += ((player, location, world) -> runner)
  }

  def startParticle(player: Player, location: Location): Unit = startParticle(player, player.getWorld, location)

  def stopParticle(player: Player, location: Location, world: World): Unit = runners.remove((player, location, world)).foreach(_.cancel())

  def stopParticle(player: Player, location: Location): Unit = stopParticle(player, location, player.getWorld)

}

object Manager {
  val PARTICLE_TYPE = Particle.FLAME
  val PARTICLE_TICK_SPAN = 10

}
