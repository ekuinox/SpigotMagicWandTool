package dev.ekuinox.spigot_magic_wand_tool
import dev.ekuinox.spigot_magic_wand_tool.subcommand.{Check, Clear, Dump, Give, SubCommand, Undo}
import org.bukkit.command.{Command, CommandSender}

class CommandExecutor(plugin: SpigotMagicWandTool) extends org.bukkit.command.CommandExecutor {
  val subCommands: Map[String, SubCommand] = Set(
    Check,
    Clear,
    Dump,
    Give,
    Undo
  ).map(subCommand => subCommand.name -> subCommand).toMap

  override def onCommand(sender: CommandSender, command: Command, label: String, args: Array[String]): Boolean = {
    subCommands.get(args.headOption.getOrElse("")) match {
      case Some(subCommand) => subCommand.run(plugin, sender, command, label, args.tail)
      case _ => // do nothing
    }

    true
  }
}
