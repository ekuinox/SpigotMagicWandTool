package dev.ekuinox.spigot_magic_wand_tool.subcommand
import dev.ekuinox.spigot_magic_wand_tool.{SpigotMagicWandTool, permisison}
import dev.ekuinox.spigot_magic_wand_tool.location.LocationsManager
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player

object Undo extends SubCommand {
  override val name: String = "undo"

  override def run(plugin: SpigotMagicWandTool, sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]

    if (!player.hasPermission(permisison.Write)) return

    player.sendMessage(LocationsManager.undo(player) match {
      case None => "すべて削除されました"
      case _ => "一つ削除しました"
    })
  }
}
