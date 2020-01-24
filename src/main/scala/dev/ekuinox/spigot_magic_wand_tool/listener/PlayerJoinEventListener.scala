package dev.ekuinox.spigot_magic_wand_tool.listener

import dev.ekuinox.spigot_magic_wand_tool.{MagicWand, SpigotMagicWandTool}
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class PlayerJoinEventListener(plugin: SpigotMagicWandTool) extends Listener(plugin) {

  @EventHandler
  def onPlayerJoin(event: PlayerJoinEvent): Unit = {
    if (((itemStack: ItemStack) => itemStack == null || !MagicWand.isMatches(itemStack, plugin))(event.getPlayer.getInventory.getItemInMainHand)) return
    plugin.locationsManager.get(event.getPlayer).foreach(plugin.particleManager.startParticles(event.getPlayer, _))
  }

}
