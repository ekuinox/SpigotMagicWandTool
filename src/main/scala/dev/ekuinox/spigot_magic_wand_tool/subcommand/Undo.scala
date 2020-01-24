package dev.ekuinox.spigot_magic_wand_tool.subcommand
import dev.ekuinox.spigot_magic_wand_tool.{SpigotMagicWandTool, permisison}
import dev.ekuinox.spigot_magic_wand_tool.location.LocationsManager
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player

import scala.util.Try

object Undo extends SubCommand {
  override val name: String = "undo"

  override def run(plugin: SpigotMagicWandTool, sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    for {
      player <- Try(sender.asInstanceOf[Player]).toOption.flatMap(player => if (player.hasPermission(permisison.Write)) Some(player) else None)
      (index, location) <- LocationsManager.undo(player)
    } {
      player.sendMessage(s"${index} => ${location}を削除しました")
      plugin.particleManager.stopParticle(player, location)
    }
  }
}
