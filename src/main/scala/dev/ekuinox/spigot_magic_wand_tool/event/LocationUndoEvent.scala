package dev.ekuinox.spigot_magic_wand_tool.event

import dev.ekuinox.spigot_magic_wand_tool.location.Location
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

class LocationUndoEvent(player: Player, world: World, location: Location) extends PlayerEvent(player: Player) {
  import LocationUndoEvent._

  override def getHandlers: HandlerList = handlers

  def getWorld: World = world

  def getLocation: Location = location

}

object LocationUndoEvent {
  private val handlers = new HandlerList()

  def getHandlerList: HandlerList = handlers

  def apply(player: Player, world: World, location: Location): LocationRegisteredEvent = new LocationRegisteredEvent(player, world, location)

  def apply(player: Player, location: org.bukkit.Location): LocationRegisteredEvent = new LocationRegisteredEvent(player, location.getWorld, Location(location))

}