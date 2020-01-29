package dev.ekuinox.spigot_magic_wand_tool.subcommand

import dev.ekuinox.spigot_magic_wand_tool.{MagicWand, SpigotMagicWandTool, permisison}
import org.bukkit.Material
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player
import scala.util.Try

object Give extends SubCommand {
  override val name: String = "give"

  override def run(plugin: SpigotMagicWandTool, sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    Try(sender.asInstanceOf[Player]).toOption match {
      case Some(player) if player.hasPermission(permisison.Give) && player.getInventory.getItemInMainHand.getType == Material.AIR =>
        player.getInventory.setItemInMainHand(MagicWand(plugin))
        player.updateInventory()
      case _ =>
    }
  }
}
