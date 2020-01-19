import java.util

import dev.ekuinox.spigot_magic_wand_tool.MagicWand
import org.bukkit.inventory.meta.ItemMeta
import org.scalatest._
import org.specs2.mock.Mockito

class MagicWandSpecs extends FunSuite with Mockito {

  test("is collected magic wand") {
    val magicWandItemMetaMock = mock[ItemMeta]
    val magicWandMock = mock[MagicWand]

    magicWandItemMetaMock.getDisplayName returns MagicWand.DISPLAY_NAME
    magicWandMock.getItemMeta returns magicWandItemMetaMock
    magicWandMock.getType returns MagicWand.MATERIAL

    assert(MagicWand.isMatches(magicWandMock))
  }

}
