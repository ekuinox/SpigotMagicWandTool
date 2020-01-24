package dev.ekuinox.spigot_magic_wand_tool.subcommand

import dev.ekuinox.spigot_magic_wand_tool.{SpigotMagicWandTool, permisison}
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player
import scala.util.Try

object Clear extends SubCommand {
  override val name: String = "clear"

  override def run(plugin: SpigotMagicWandTool, sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    Try(sender.asInstanceOf[Player]).toOption match {
      case Some(player) if player.hasPermission(permisison.Write) =>
        for (locations <- plugin.locationsManager.get(player)) {
          locations.foreach(location => plugin.particleManager.stopParticle(player, location))
          plugin.locationsManager.clear(player)
        }
      case _ =>
    }
  }
}
