package dev.ekuinox.spigot_magic_wand_tool

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * 自由選択を行うためのアイテム
 * displayNameにて判定する
 */
class MagicWand extends ItemStack(MagicWand.MATERIAL) {

  this.setItemMeta({
    val meta = this.getItemMeta
    meta.setDisplayName(MagicWand.DISPLAY_NAME)
    meta
  })

}

object MagicWand {
  private val MATERIAL = Material.STICK
  private val DISPLAY_NAME = "MagicWand"

  def isMatches(itemStack: ItemStack): Boolean = itemStack.getType == MATERIAL && itemStack.getItemMeta.getDisplayName == DISPLAY_NAME

  def apply(): MagicWand = new MagicWand()

}