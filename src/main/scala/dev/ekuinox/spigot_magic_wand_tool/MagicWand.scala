package dev.ekuinox.spigot_magic_wand_tool

import org.bukkit.{Material, NamespacedKey}
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin

import scala.jdk.CollectionConverters._

/**
 * 自由選択を行うためのアイテム
 * displayNameにて判定する
 */
class MagicWand(plugin: SpigotMagicWandTool) extends ItemStack(MagicWand.MATERIAL) {
  import MagicWand._

  this.setItemMeta({
    val meta = this.getItemMeta
    val container = meta.getPersistentDataContainer
    container.set(plugin.makeNamespacedKey(KEY), PersistentDataType.STRING, "true")
    meta.setDisplayName(DISPLAY_NAME)
    meta.setLore(LORE.asJava)
    meta
  })

}

object MagicWand {
  val MATERIAL = Material.STICK
  val DISPLAY_NAME = "MagicWand"
  val LORE = List("MagicWand")
  val KEY = "MAGIC_WAND"

  def isMatches(itemStack: ItemStack, plugin: SpigotMagicWandTool): Boolean = {
    if (itemStack.getType != MATERIAL) return false
    if (itemStack.getItemMeta.getDisplayName != DISPLAY_NAME) return false
    if (!itemStack.getItemMeta.hasLore) return false
    if (itemStack.getItemMeta.getLore != LORE.asJava) return false
    itemStack.getItemMeta.getPersistentDataContainer.has(plugin.makeNamespacedKey(KEY), PersistentDataType.STRING)
  }

  def apply(plugin: SpigotMagicWandTool): MagicWand = new MagicWand(plugin)
  
}
