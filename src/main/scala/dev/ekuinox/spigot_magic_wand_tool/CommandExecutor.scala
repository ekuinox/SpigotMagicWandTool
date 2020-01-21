package dev.ekuinox.spigot_magic_wand_tool
import org.bukkit.Material
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player

class CommandExecutor(plugin: SpigotMagicWandTool) extends org.bukkit.command.CommandExecutor {
  override def onCommand(sender: CommandSender, command: Command, label: String, args: Array[String]): Boolean = {
    args.toList.headOption.foreach {
      case "give" => CommandExecutor.give(sender, command, label, args, plugin)
      case "check" => CommandExecutor.check(sender, command, label, args, plugin)
      case _ => //do nothing
    }

    true
  }
}

object CommandExecutor {
  def give(sender: CommandSender, command: Command, label: String, args: Array[String], plugin: SpigotMagicWandTool): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]
    if (player.getInventory.getItemInMainHand.getType != Material.AIR) return

    player.getInventory.setItemInMainHand(MagicWand(plugin))
    player.updateInventory()
  }

  def check(sender: CommandSender, command: Command, label: String, args: Array[String], plugin: SpigotMagicWandTool): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]

    player.sendMessage(s"your main hand item is ${if (MagicWand.isMatches(player.getInventory.getItemInMainHand, plugin)) "" else "not "}magicwand")
  }
}
