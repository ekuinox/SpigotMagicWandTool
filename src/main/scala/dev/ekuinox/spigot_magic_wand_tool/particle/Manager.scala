package dev.ekuinox.spigot_magic_wand_tool.particle

import dev.ekuinox.spigot_magic_wand_tool.{Location, SpigotMagicWandTool}
import org.bukkit.entity.Player
import org.bukkit.{Particle, World}


class Manager(plugin: SpigotMagicWandTool) {
  import Manager._
  import collection.mutable.Map

  private val runners: Map[(Player, Location), Runner] = Map[(Player, Location), Runner]()

  def startParticle(player: Player, world: World, location: Location): Unit = {
    val runner = new Runner(plugin, world, location)
    runner.runTaskTimerAsynchronously(plugin, 0, PARTICLE_TICK_SPAN)
    runners += ((player, location) -> runner)
  }

  def stopParticle(player: Player, location: Location): Unit = runners.get((player, location)).foreach(runner => runner.cancel())

}

object Manager {
  val PARTICLE_TYPE = Particle.SPELL_WITCH
  val PARTICLE_TICK_SPAN = 10

}
