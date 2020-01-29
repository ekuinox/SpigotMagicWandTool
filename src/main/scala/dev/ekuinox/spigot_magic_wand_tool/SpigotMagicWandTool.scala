package dev.ekuinox.spigot_magic_wand_tool

import dev.ekuinox.spigot_magic_wand_tool.listener.{PlayerChangedWorldEventListener, PlayerInteractEventListener, PlayerItemHeldEventListener, PlayerJoinEventListener, PlayerQuitEventListener}
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class SpigotMagicWandTool extends JavaPlugin {
  import SpigotMagicWandTool._

  instance = Some(this)

  val listeners = Seq(
    new PlayerInteractEventListener(this),
    new PlayerItemHeldEventListener(this),
    new PlayerJoinEventListener(this),
    new PlayerQuitEventListener(this),
    new PlayerChangedWorldEventListener(this)
  )

  val particleManager = new particle.Manager(this)
  val locationsManager = new location.Manager(this)
  
  override def onEnable(): Unit = {
    super.onEnable()
    getCommand("magicwand").setExecutor(new CommandExecutor(this))
    listeners.foreach(_.register())
  }

  def makeNamespacedKey(key: String) = new NamespacedKey(this, key)

}

object SpigotMagicWandTool {
  private var instance: Option[SpigotMagicWandTool] = None
  def getInstance: Option[SpigotMagicWandTool] = instance

}