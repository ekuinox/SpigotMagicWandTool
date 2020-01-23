package dev.ekuinox.spigot_magic_wand_tool.particle

import dev.ekuinox.spigot_magic_wand_tool.{Location, SpigotMagicWandTool}
import org.bukkit.{Effect, World}
import org.bukkit.scheduler.BukkitRunnable

class Runner(plugin: SpigotMagicWandTool, world: World, location: Location) extends BukkitRunnable {
  import Manager._

  override def run(): Unit = {
    world.spawnParticle(PARTICLE_TYPE, location.x.toDouble + 0.5, location.y.toDouble - 0.5f, location.z.toDouble + 0.5, PARTICLE_COUNT)
  }
}
