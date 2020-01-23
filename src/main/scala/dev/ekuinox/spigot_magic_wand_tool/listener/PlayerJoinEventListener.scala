package dev.ekuinox.spigot_magic_wand_tool.listener

import dev.ekuinox.spigot_magic_wand_tool.location.LocationsManager
import dev.ekuinox.spigot_magic_wand_tool.{MagicWand, SpigotMagicWandTool}
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinEventListener(plugin: SpigotMagicWandTool) extends Listener(plugin) {

  @EventHandler
  def onPlayerJoin(event: PlayerJoinEvent): Unit = {
    for { locations <- LocationsManager.get(event.getPlayer) } {
      if (MagicWand.isMatches(event.getPlayer.getInventory.getItemInMainHand, plugin)) {
        locations.foreach(plugin.particleManager.startParticle(event.getPlayer, event.getPlayer.getWorld,  _))
      }
    }
  }

}
