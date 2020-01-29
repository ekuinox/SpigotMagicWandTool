package dev.ekuinox.spigot_magic_wand_tool.event

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

class LocationRegisteredEvent(player: Player) extends PlayerEvent(player: Player) {
  import LocationRegisteredEvent._

  override def getHandlers: HandlerList = handlers
}

object LocationRegisteredEvent {
  val handlers = new HandlerList()

}