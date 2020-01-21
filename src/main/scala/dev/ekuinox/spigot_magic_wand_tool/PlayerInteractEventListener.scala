package dev.ekuinox.spigot_magic_wand_tool

import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.scheduler.BukkitRunnable

class PlayerInteractEventListener(plugin: SpigotMagicWandTool) extends Listener {

  /**
   * InteractEventが同時に2回呼ばれるのを防ぐため
   */
  object InteractEventTimer {
    private val KEY = "MAGIC_WAND_INTERACT_EVENT_TIMER"
    private val DELAY = 10
    def isActiveTimer(player: Player): Boolean = try { player.getMetadata(InteractEventTimer.KEY).get(0).asBoolean() } catch { case _: Throwable => false }
    def enableTimer(player: Player): Unit = {
      player.setMetadata(KEY, new FixedMetadataValue(plugin, true))
      new BukkitRunnable {
        override def run(): Unit = player.setMetadata(KEY, new FixedMetadataValue(plugin, false))
      }.runTaskLater(plugin, DELAY)
    }
  }
  import InteractEventTimer._
  import MagicWand._

  /**
   * このListenerをPluginManagerに登録する
   */
  def register(): Unit = {
    plugin.getServer.getPluginManager.registerEvents(this, plugin)
  }

  @EventHandler
  def onPlayerInteract(event: PlayerInteractEvent): Unit = {
    val player = event.getPlayer
    if (isActiveTimer(player)) return

    if (!player.hasPermission(permisisons.Set)) return

    if (event.getAction != Action.RIGHT_CLICK_BLOCK) return

    val item = player.getInventory.getItemInMainHand
    if (!isMatches(item, plugin)) return

    for { clickedBlock <- Option(event.getClickedBlock) } {
      // クリックした面の座標を取得して登録
      val location = clickedBlock.getRelative(event.getBlockFace).getLocation()
      val index = LocationsManager.set(player, location)
      player.sendMessage(s"registered location => {${location.getX}, ${location.getY}, ${location.getZ}, index => $index")
      enableTimer(player)
    }
  }

}
