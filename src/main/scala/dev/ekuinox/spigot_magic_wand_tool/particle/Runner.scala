package dev.ekuinox.spigot_magic_wand_tool.particle

import dev.ekuinox.spigot_magic_wand_tool.{Location, SpigotMagicWandTool}
import org.bukkit.{Effect, World}
import org.bukkit.scheduler.BukkitRunnable

class Runner(plugin: SpigotMagicWandTool, world: World, location: Location) extends BukkitRunnable {
  import Manager._

  override def run(): Unit = {
    world.spawnParticle(PARTICLE_TYPE, location.x.toDouble, location.y.toDouble, location.z.toDouble, PARTICLE_COUNT)
  }
}
