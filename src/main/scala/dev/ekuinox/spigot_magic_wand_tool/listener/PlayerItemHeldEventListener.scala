package dev.ekuinox.spigot_magic_wand_tool.listener

import dev.ekuinox.spigot_magic_wand_tool.{LocationsManager, MagicWand, SpigotMagicWandTool}
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.event.EventHandler

class PlayerItemHeldEventListener(plugin: SpigotMagicWandTool) extends Listener(plugin) {

  @EventHandler
  def onPlayerItemHeld(event: PlayerItemHeldEvent): Unit = {
    val player = event.getPlayer
    val inventory = player.getPlayer.getInventory

    val stopParticles = () => {
      for {
        locations <- LocationsManager.get(player)
      } {
        locations.foreach(plugin.particleManager.stopParticle(player, _))
      }
    }

    val startParticles = () => {
      for {
        locations <- LocationsManager.get(player)
      } {
        locations.foreach(plugin.particleManager.startParticle(player, _))
      }
    }

    (Option(inventory.getItem(event.getPreviousSlot)), Option(inventory.getItem(event.getNewSlot))) match {
      case (Some(prevItem), Some(currentItem)) => {
        (MagicWand.isMatches(prevItem, plugin), MagicWand.isMatches(currentItem, plugin)) match {
          case (true, false) => stopParticles()
          case (false, true) => startParticles()
        }
      }
      case (Some(prevItem), None) if MagicWand.isMatches(prevItem, plugin) => stopParticles()
      case (None, Some(currentItem)) if MagicWand.isMatches(currentItem, plugin) => startParticles()
    }
  }

}
