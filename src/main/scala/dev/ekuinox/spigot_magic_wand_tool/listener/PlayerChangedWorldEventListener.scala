package dev.ekuinox.spigot_magic_wand_tool.listener

import dev.ekuinox.spigot_magic_wand_tool.SpigotMagicWandTool
import dev.ekuinox.spigot_magic_wand_tool.location.LocationsManager
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerChangedWorldEvent

class PlayerChangedWorldEventListener(plugin: SpigotMagicWandTool) extends Listener(plugin) {

  @EventHandler
  def onPlayerChangedWorld(event: PlayerChangedWorldEvent): Unit = {
    println(s"from ${event.getFrom.getName} to ${event.getPlayer.getWorld.getName}")

    // 前のワールドのパーティクルを消す
    LocationsManager.get(event.getPlayer, event.getFrom).foreach(plugin.particleManager.stopParticles(event.getPlayer, event.getFrom, _))

    // 新しいワールドでのパーティクルをつける
    LocationsManager.get(event.getPlayer).foreach(plugin.particleManager.stopParticles(event.getPlayer, _))

  }

}
