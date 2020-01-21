import java.util

import dev.ekuinox.spigot_magic_wand_tool.MagicWand.KEY
import dev.ekuinox.spigot_magic_wand_tool.{MagicWand, SpigotMagicWandTool}
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.{PersistentDataContainer, PersistentDataType}
import org.scalatest._
import org.specs2.mock.Mockito

import scala.jdk.CollectionConverters._

class MagicWandSpecs extends FunSuite with Mockito {

  test("is collect magic wand") {
    val pluginMock = mock[SpigotMagicWandTool]
    val containerMock = mock[PersistentDataContainer]
    val magicWandItemMetaMock = mock[ItemMeta]
    val magicWandMock = mock[MagicWand]

    containerMock.has(pluginMock.makeNamespacedKey(KEY), PersistentDataType.STRING) returns true

    magicWandItemMetaMock.getDisplayName returns MagicWand.DISPLAY_NAME
    magicWandItemMetaMock.getLore returns MagicWand.LORE.asJava
    magicWandItemMetaMock.hasLore returns true
    magicWandItemMetaMock.getPersistentDataContainer returns containerMock

    magicWandMock.getItemMeta returns magicWandItemMetaMock
    magicWandMock.getType returns MagicWand.MATERIAL

    assert(MagicWand.isMatches(magicWandMock, pluginMock))
  }

  test("is incorrect magic wand") {
    val pluginMock = mock[SpigotMagicWandTool]
    val itemMetaMock = mock[ItemMeta]
    val itemMock = mock[ItemStack]

    itemMetaMock.getDisplayName returns ""
    itemMock.getItemMeta returns itemMetaMock
    itemMock.getType returns Material.ACACIA_BOAT

    assert(!MagicWand.isMatches(itemMock, pluginMock))
  }

}
