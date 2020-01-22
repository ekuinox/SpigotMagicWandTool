package dev.ekuinox.spigot_magic_wand_tool
import org.bukkit.Material
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player

class CommandExecutor(plugin: SpigotMagicWandTool) extends org.bukkit.command.CommandExecutor {
  override def onCommand(sender: CommandSender, command: Command, label: String, args: Array[String]): Boolean = {
    args.toList.headOption.foreach {
      case "give" => CommandExecutor.give(sender, command, label, args, plugin)
      case "check" => CommandExecutor.check(sender, command, label, args, plugin)
      case "clear" => CommandExecutor.clear(sender, command, label, args, plugin)
      case "undo" => CommandExecutor.undo(sender, command, label, args, plugin)
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

    if (!player.hasPermission(permisisons.Give)) return

    if (player.getInventory.getItemInMainHand.getType != Material.AIR) return

    player.getInventory.setItemInMainHand(MagicWand(plugin))
    player.updateInventory()
  }

  def check(sender: CommandSender, command: Command, label: String, args: Array[String], plugin: SpigotMagicWandTool): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]

    if (!player.hasPermission(permisisons.Check)) return

    player.sendMessage(s"your main hand item is ${if (MagicWand.isMatches(player.getInventory.getItemInMainHand, plugin)) "" else "not "}magicwand")
  }

  def clear(sender: CommandSender, command: Command, label: String, args: Array[String], plugin: SpigotMagicWandTool): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]

    if (!player.hasPermission(permisisons.Write)) return

    for {
      locations <- LocationsManager.get(player)
    } {
      locations.foreach(location => plugin.particleManager.stopParticle(player, location))
      LocationsManager.clear(player)
    }
  }

  def undo(sender: CommandSender, command: Command, label: String, args: Array[String], plugin: SpigotMagicWandTool): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]

    if (!player.hasPermission(permisisons.Write)) return

    player.sendMessage(LocationsManager.undo(player) match {
      case None => "すべて削除されました"
      case _ => "一つ削除しました"
    })
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

      if (!player.hasPermission(permisisons.Read)) return

      LocationsManager.get(player).foreach(locations => player.sendMessage(locations.toString()))
    }
  }
}
