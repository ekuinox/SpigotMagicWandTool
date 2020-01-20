package dev.ekuinox.spigot_magic_wand_tool

import org.bukkit.plugin.java.JavaPlugin

class SpigotMagicWandTool extends JavaPlugin {
  import SpigotMagicWandTool._

  instance = Some(this)
  val playerInteractEventListener = new PlayerInteractEventListener(this)
  
  override def onEnable(): Unit = {
    super.onEnable()
    getCommand("magicwand").setExecutor(new CommandExecutor(this))
    playerInteractEventListener.register()
  }

}

object SpigotMagicWandTool {
  private var instance: Option[SpigotMagicWandTool] = None
  def getInstance: Option[SpigotMagicWandTool] = instance

}