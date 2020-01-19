package dev.ekuinox.spigot_magic_wand_tool

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import scala.jdk.CollectionConverters._

/**
 * 自由選択を行うためのアイテム
 * displayNameにて判定する
 */
class MagicWand extends ItemStack(MagicWand.MATERIAL) {
  import MagicWand._

  this.setItemMeta({
    val meta = this.getItemMeta
    meta.setDisplayName(DISPLAY_NAME)
    meta.setLore(LORE.asJava)
    meta
  })

}

object MagicWand {
  val MATERIAL = Material.STICK
  val DISPLAY_NAME = "MagicWand"
  val LORE = List("MagicWand")

  def isMatches(itemStack: ItemStack): Boolean = {
    if (itemStack.getType != MATERIAL) return false
    if (itemStack.getItemMeta.getDisplayName != DISPLAY_NAME) return false
    if (!itemStack.getItemMeta.hasLore) return false
    itemStack.getItemMeta.getLore == LORE.asJava
  }

  def apply(): MagicWand = new MagicWand()

  implicit def convert(itemStack: ItemStack) = new {
    def isMagicWand: Boolean = isMatches(itemStack)
  }
}
