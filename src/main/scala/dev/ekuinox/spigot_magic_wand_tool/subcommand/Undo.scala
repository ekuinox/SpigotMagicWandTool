package dev.ekuinox.spigot_magic_wand_tool.subcommand
import dev.ekuinox.spigot_magic_wand_tool.event.LocationUndoEvent
import dev.ekuinox.spigot_magic_wand_tool.{SpigotMagicWandTool, permisison}
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player
import scala.util.Try

object Undo extends SubCommand {
  override val name: String = "undo"

  override def run(plugin: SpigotMagicWandTool, sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    for {
      player <- Try(sender.asInstanceOf[Player]).toOption.flatMap(player => if (player.hasPermission(permisison.Write)) Some(player) else None)
      (index, location) <- plugin.locationsManager.undo(player)
    } {
      plugin.getServer.getPluginManager.callEvent(LocationUndoEvent(player, player.getWorld, location))
      player.sendMessage(s"${index} => ${location}を削除しました")
      plugin.particleManager.stopParticle(player, location)
    }
  }
}
