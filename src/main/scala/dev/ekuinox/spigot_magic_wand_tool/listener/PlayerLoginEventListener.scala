package dev.ekuinox.spigot_magic_wand_tool.listener

import dev.ekuinox.spigot_magic_wand_tool.SpigotMagicWandTool
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerLoginEvent

class PlayerLoginEventListener(plugin: SpigotMagicWandTool) extends Listener(plugin) {

  @EventHandler
  def onPlayerLogin(event: PlayerLoginEvent): Unit = {
    
  }

}
