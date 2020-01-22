package dev.ekuinox.spigot_magic_wand_tool

import dev.ekuinox.spigot_magic_wand_tool.listener.{PlayerInteractEventListener, PlayerItemHeldEventListener}
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class SpigotMagicWandTool extends JavaPlugin {
  import SpigotMagicWandTool._

  instance = Some(this)
  val playerInteractEventListener = new PlayerInteractEventListener(this)
  val playerItemHeldEventListener = new PlayerItemHeldEventListener(this)
  val particleManager = new particle.Manager(this)
  
  override def onEnable(): Unit = {
    super.onEnable()
    getCommand("magicwand").setExecutor(new CommandExecutor(this))
    playerInteractEventListener.register()
    playerItemHeldEventListener.register()
  }

  def makeNamespacedKey(key: String) = new NamespacedKey(this, key)

}

object SpigotMagicWandTool {
  private var instance: Option[SpigotMagicWandTool] = None
  def getInstance: Option[SpigotMagicWandTool] = instance

}