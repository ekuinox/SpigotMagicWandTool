package dev.ekuinox.spigot_magic_wand_tool
import org.bukkit.Material
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player

class CommandExecutor extends org.bukkit.command.CommandExecutor {
  override def onCommand(sender: CommandSender, command: Command, label: String, args: Array[String]): Boolean = {
    if (args == null) return true
    if (args.isEmpty) {
      return true
    }

    args(0) match {
      case "give" => CommandExecutor.give(sender, command, label, args)
      case "check" => CommandExecutor.check(sender, command, label, args)
    }

    true
  }
}

object CommandExecutor {
  def give(sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]
    if (player.getInventory.getItemInMainHand.getType != Material.AIR) return

    player.getInventory.setItemInMainHand(MagicWand())
    player.updateInventory()
  }

  def check(sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]

    player.sendMessage(s"your main hand item is ${if (MagicWand.isMatches(player.getInventory.getItemInMainHand)) "" else "not "}magicwand")
  }
}
