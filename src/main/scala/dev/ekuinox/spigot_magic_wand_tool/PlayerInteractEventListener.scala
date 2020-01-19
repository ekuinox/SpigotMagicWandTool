package dev.ekuinox.spigot_magic_wand_tool

import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.{EventHandler, Listener}

class PlayerInteractEventListener(plugin: SpigotMagicWandTool) extends Listener {

  /**
   * このListenerをPluginManagerに登録する
   */
  def register(): Unit = {
    plugin.getServer.getPluginManager.registerEvents(this, plugin)
  }

  @EventHandler
  def onPlayerInteract(event: PlayerInteractEvent): Unit = {

  }

}
