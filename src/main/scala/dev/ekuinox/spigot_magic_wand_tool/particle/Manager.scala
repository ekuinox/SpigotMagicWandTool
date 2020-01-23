package dev.ekuinox.spigot_magic_wand_tool.particle

import dev.ekuinox.spigot_magic_wand_tool.SpigotMagicWandTool
import dev.ekuinox.spigot_magic_wand_tool.location.Location
import org.bukkit.entity.Player
import org.bukkit.{Particle, World}


class Manager(plugin: SpigotMagicWandTool) {
  type Locations = List[Location]

  import Manager._
  import collection.mutable.Map

  private val runners: Map[(Player, Location, World), Runner] = Map[(Player, Location, World), Runner]()

  /**
   * パーティクルを一つ湧かす Runner の開始
   * @param player Player
   * @param world World
   * @param location Location
   */
  def startParticle(player: Player, world: World, location: Location): Unit = {
    val runner = new Runner(plugin, world, location)
    runner.runTaskTimerAsynchronously(plugin, 0, PARTICLE_TICK_SPAN)
    runners += ((player, location, world) -> runner)
  }

  /**
   * Player のいる World を元に startParticle を行う
   * @param player Player
   * @param location Location
   */
  def startParticle(player: Player, location: Location): Unit = startParticle(player, player.getWorld, location)

  /**
   * リストを元に startParticle を行う
   * @param player Player
   * @param world World
   * @param locations Locations
   */
  def startParticles(player: Player, world: World, locations: Locations): Unit = locations.foreach(startParticle(player, world, _))

  /**
   * Player のいる World を元に startParticles を行う
   * @param player Player
   * @param locations Locations
   */
  def startParticles(player: Player, locations: Locations): Unit = startParticles(player, player.getWorld, locations)

  /**
   * Runner の停止
   * @param player Player
   * @param world World
   * @param location Location
   */
  def stopParticle(player: Player, world: World, location: Location): Unit = runners.remove((player, location, world)).foreach(_.cancel())

  /**
   * Player のいる World を元に stopParticle を行う
   * @param player Player
   * @param location Location
   */
  def stopParticle(player: Player, location: Location): Unit = stopParticle(player, player.getWorld, location)

  /**
   * リストを元に stopParticle を行う
   * @param player Player
   * @param world World
   * @param locations Locations
   */
  def stopParticles(player: Player, world: World, locations: Locations): Unit = locations.foreach(stopParticle(player, world, _))

  /**
   * Player のいる World を元に stopParticle を行う
   * @param player Player
   * @param locations Locations
   */
  def stopParticles(player: Player, locations: Locations): Unit = stopParticles(player, player.getWorld, locations)

}

object Manager {
  val PARTICLE_TYPE = Particle.FLAME
  val PARTICLE_TICK_SPAN = 10

}
