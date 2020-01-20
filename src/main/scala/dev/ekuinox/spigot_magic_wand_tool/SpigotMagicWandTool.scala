package dev.ekuinox.spigot_magic_wand_tool

import org.bukkit.plugin.java.JavaPlugin

class SpigotMagicWandTool extends JavaPlugin {
  val playerInteractEventListener = new PlayerInteractEventListener(this)

  override def onEnable(): Unit = {
    super.onEnable()
    getCommand("magicwand").setExecutor(new CommandExecutor(this))
    playerInteractEventListener.register()
  }

}
