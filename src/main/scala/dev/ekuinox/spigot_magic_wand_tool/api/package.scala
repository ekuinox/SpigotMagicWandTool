package dev.ekuinox.spigot_magic_wand_tool

import dev.ekuinox.spigot_magic_wand_tool.location.Location
import org.bukkit.World
import org.bukkit.entity.Player

package object api {

  type Locations = List[Location]

  /**
   * Player と World に紐付いた Location のリストを取得する
   * @param player Player
   * @param world World
   * @return Option[Locations]
   */
  def getLocations(player: Player, world: World): Option[Locations] = SpigotMagicWandTool.getInstance.flatMap(plugin => plugin.locationsManager.get(player, world))

  /**
   * Player の現在いる World に紐付いた Location のリストを取得する
   * @param player Player
   * @return Option[Locations]
   */
  def getLocations(player: Player): Option[Locations] = getLocations(player, player.getWorld)

}