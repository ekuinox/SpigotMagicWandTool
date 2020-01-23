package dev.ekuinox.spigot_magic_wand_tool.subcommand
import dev.ekuinox.spigot_magic_wand_tool.{MagicWand, SpigotMagicWandTool, permisison}
import org.bukkit.Material
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.entity.Player

object Give extends SubCommand {
  override val name: String = "give"

  override def run(plugin: SpigotMagicWandTool, sender: CommandSender, command: Command, label: String, args: Array[String]): Unit = {
    if (!sender.isInstanceOf[Player]) return

    val player = sender.asInstanceOf[Player]

    if (!player.hasPermission(permisison.Give)) return

    if (player.getInventory.getItemInMainHand.getType != Material.AIR) return

    player.getInventory.setItemInMainHand(MagicWand(plugin))
    player.updateInventory()
  }
}
