package dev.ekuinox.spigot_magic_wand_tool.listener

import dev.ekuinox.spigot_magic_wand_tool.location.LocationsManager
import dev.ekuinox.spigot_magic_wand_tool.{MagicWand, SpigotMagicWandTool, permisisons}
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.EventHandler
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.scheduler.BukkitRunnable

class PlayerInteractEventListener(plugin: SpigotMagicWandTool) extends Listener(plugin)  {

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

  /**
   * PlayerInteractEventが対象かチェック
   */
  def isMatches(event: PlayerInteractEvent): Boolean = {
    event.getAction == Action.RIGHT_CLICK_BLOCK &&
      !isActiveTimer(event.getPlayer) &&
      MagicWand.isMatches(event.getPlayer.getPlayer.getInventory.getItemInMainHand, plugin)
  }

  @EventHandler
  def onPlayerInteract(event: PlayerInteractEvent): Unit = {
    if (!isMatches(event)) return

    if (!event.getPlayer.hasPermission(permisisons.Write)) return

    val player = event.getPlayer
    for {
      clickedBlock <- Option(event.getClickedBlock)
      (index, location) <- LocationsManager.set(player, clickedBlock.getRelative(event.getBlockFace).getLocation())
    } {
      player.sendMessage(s"registered location => {${location.x}, ${location.y}, ${location.z}, index => $index")
      enableTimer(player)
      plugin.particleManager.startParticle(player, location)
    }
  }

}
