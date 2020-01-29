package dev.ekuinox.spigot_magic_wand_tool.event

import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

class LocationsClearEvent(player: Player, world: World) extends PlayerEvent(player) {
  import LocationsClearEvent._

  override def getHandlers: HandlerList = handlers

  def getWorld: World = world

}

object LocationsClearEvent {
  private val handlers = new HandlerList()

  def getHandlerList: HandlerList = handlers

  def apply(player: Player, world: World): LocationsClearEvent = new LocationsClearEvent(player, world)

  def apply(player: Player): LocationsClearEvent = apply(player, player.getWorld)

}
