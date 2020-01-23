package dev.ekuinox.spigot_magic_wand_tool.listener

import dev.ekuinox.spigot_magic_wand_tool.SpigotMagicWandTool
import org.bukkit.event.{Listener => BukkitListener}

class Listener(plugin: SpigotMagicWandTool) extends BukkitListener {

  /**
   * 自身をPluginManagerに登録する
   */
  def register(): Unit = {
    plugin.getServer.getPluginManager.registerEvents(this, plugin)
  }

}
