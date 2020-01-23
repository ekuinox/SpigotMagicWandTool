package dev.ekuinox.spigot_magic_wand_tool.listener

import dev.ekuinox.spigot_magic_wand_tool.SpigotMagicWandTool
import dev.ekuinox.spigot_magic_wand_tool.location.LocationsManager
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitEventListener(plugin: SpigotMagicWandTool) extends Listener(plugin) {

  @EventHandler
  def onPlayerQuit(event: PlayerQuitEvent): Unit = {
    LocationsManager.get(event.getPlayer).foreach(plugin.particleManager.startParticles(event.getPlayer, _))
  }

}
