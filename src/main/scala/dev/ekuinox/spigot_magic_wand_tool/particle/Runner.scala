package dev.ekuinox.spigot_magic_wand_tool.particle

import dev.ekuinox.spigot_magic_wand_tool.SpigotMagicWandTool
import dev.ekuinox.spigot_magic_wand_tool.location.Location
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable

class Runner(plugin: SpigotMagicWandTool, world: World, location: Location) extends BukkitRunnable {
  import Manager._

  override def run(): Unit = {
    world.spawnParticle(PARTICLE_TYPE, location.toBukkitLocation(world).add(0.5, 0.5, 0.5), 0, 0, 0, 0, 0)
  }
}
