package dev.ekuinox.spigot_magic_wand_tool
import dev.ekuinox.spigot_magic_wand_tool.permisisons.{Check, Dump, Give}
import org.bukkit.Material
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player

class CommandExecutor(plugin: SpigotMagicWandTool) extends org.bukkit.command.CommandExecutor {
  override def onCommand(sender: CommandSender, command: Command, label: String, args: Array[String]): Boolean = {
    args.toList.headOption.foreach {
      case "give" => CommandExecutor.give(sender, command, label, args, plugin)
      case "check" => CommandExecutor.check(sender, command, label, args, plugin)
      case "dump" => CommandExecutor.dump(sender, command, label, args, plugin)
      case _ => //do nothing
    }

    true
  }
}

object CommandExecutor {
  def give(sender: CommandSender, command: Command, label: String, args: Array[String], plugin: SpigotMagicWandTool): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]

    if (!player.hasPermission(Give)) return

    if (player.getInventory.getItemInMainHand.getType != Material.AIR) return

    player.getInventory.setItemInMainHand(MagicWand(plugin))
    player.updateInventory()
  }

  def check(sender: CommandSender, command: Command, label: String, args: Array[String], plugin: SpigotMagicWandTool): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]

    if (!player.hasPermission(Check)) return

    player.sendMessage(s"your main hand item is ${if (MagicWand.isMatches(player.getInventory.getItemInMainHand, plugin)) "" else "not "}magicwand")
  }

  def dump(sender: CommandSender, command: Command, label: String, args: Array[String], plugin: SpigotMagicWandTool): Unit = {
    if (!sender.isInstanceOf[Player]) {
      for {
        playerName <- Option(args(1))
        player <- Option(plugin.getServer.getPlayer(playerName))
      } {
        println(LocationsManager.get(player))
      }
    } else {
      val player = sender.asInstanceOf[Player]

      if (!player.hasPermission(Dump)) return

      player.sendMessage(LocationsManager.get(player).toString())
    }
  }
}
