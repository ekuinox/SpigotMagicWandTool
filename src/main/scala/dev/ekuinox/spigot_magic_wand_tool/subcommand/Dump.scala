package dev.ekuinox.spigot_magic_wand_tool.subcommand
import dev.ekuinox.spigot_magic_wand_tool.{SpigotMagicWandTool, permisison}
import dev.ekuinox.spigot_magic_wand_tool.location.LocationsManager
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player

object Dump extends SubCommand {
  override val name: String = "dump"

  override def run(plugin: SpigotMagicWandTool, sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    if (!sender.isInstanceOf[Player]) {
      for {
        playerName <- Option(args(9))
        player <- Option(plugin.getServer.getPlayer(playerName))
      } {
        println(LocationsManager.get(player))
      }
    } else {
      val player = sender.asInstanceOf[Player]

      if (!player.hasPermission(permisison.Read)) return

      LocationsManager.get(player).foreach(locations => player.sendMessage(locations.toString()))
    }
  }
}
