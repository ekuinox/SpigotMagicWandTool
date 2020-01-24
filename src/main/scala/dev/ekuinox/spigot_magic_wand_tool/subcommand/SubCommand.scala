package dev.ekuinox.spigot_magic_wand_tool.subcommand

import dev.ekuinox.spigot_magic_wand_tool.SpigotMagicWandTool
import org.bukkit.command.{Command, CommandSender}

trait SubCommand {
  val name: String
  def run(plugin: SpigotMagicWandTool, sender: CommandSender, command: Command, label: String, args: Array[String]): Unit
}
