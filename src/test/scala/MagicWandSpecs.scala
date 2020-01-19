import java.util

import dev.ekuinox.spigot_magic_wand_tool.MagicWand
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.scalatest._
import org.specs2.mock.Mockito
import scala.jdk.CollectionConverters._

class MagicWandSpecs extends FunSuite with Mockito {

  test("is collect magic wand") {
    val magicWandItemMetaMock = mock[ItemMeta]
    val magicWandMock = mock[MagicWand]

    magicWandItemMetaMock.getDisplayName returns MagicWand.DISPLAY_NAME
    magicWandItemMetaMock.getLore returns MagicWand.LORE.asJava
    magicWandItemMetaMock.hasLore returns true
    magicWandMock.getItemMeta returns magicWandItemMetaMock
    magicWandMock.getType returns MagicWand.MATERIAL

    assert(MagicWand.isMatches(magicWandMock))
  }

  test("is incorrect magic wand") {
    val itemMetaMock = mock[ItemMeta]
    val itemMock = mock[ItemStack]

    itemMetaMock.getDisplayName returns ""
    itemMock.getItemMeta returns itemMetaMock
    itemMock.getType returns Material.ACACIA_BOAT

    assert(!MagicWand.isMatches(itemMock))
  }

}
