package dev.ekuinox.spigot_magic_wand_tool.subcommand
import dev.ekuinox.spigot_magic_wand_tool.{SpigotMagicWandTool, permisison}
import dev.ekuinox.spigot_magic_wand_tool.location.LocationsManager
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player

import scala.util.Try

object Dump extends SubCommand {
  override val name: String = "dump"

  override def run(plugin: SpigotMagicWandTool, sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    Try(sender.asInstanceOf[Player]).toOption match {
      case Some(player) if player.hasPermission(permisison.Read) => LocationsManager.get(player).foreach(locations => player.sendMessage(locations.toString()))
      case _ =>
    }
  }
}
