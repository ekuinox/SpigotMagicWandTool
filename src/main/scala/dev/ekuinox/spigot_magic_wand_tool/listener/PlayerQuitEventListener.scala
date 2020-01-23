package dev.ekuinox.spigot_magic_wand_tool.listener

import dev.ekuinox.spigot_magic_wand_tool.{LocationsManager, SpigotMagicWandTool}
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitEventListener(plugin: SpigotMagicWandTool) extends Listener(plugin) {

  @EventHandler
  def onPlayerQuit(event: PlayerQuitEvent): Unit = {
    for { locations <- LocationsManager.get(event.getPlayer) } {
      locations.foreach(location => plugin.particleManager.stopParticle(event.getPlayer, location))
    }
  }

}
