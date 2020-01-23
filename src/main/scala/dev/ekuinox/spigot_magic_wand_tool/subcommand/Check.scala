package dev.ekuinox.spigot_magic_wand_tool.subcommand
import dev.ekuinox.spigot_magic_wand_tool.{MagicWand, SpigotMagicWandTool, permisison}
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player

object Check extends SubCommand {
  override val name: String = "check"

  override def run(plugin: SpigotMagicWandTool, sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]

    if (!player.hasPermission(permisison.Check)) return

    player.sendMessage(s"your main hand item is ${if (MagicWand.isMatches(player.getInventory.getItemInMainHand, plugin)) "" else "not "}magicwand")
  }
}
