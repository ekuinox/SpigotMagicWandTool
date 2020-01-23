package dev.ekuinox.spigot_magic_wand_tool.location

import org.bukkit.entity.Player
import org.bukkit.{Location => BukkitLocation}

/**
 * プレイヤが選択した座標情報を保管,管理する
 */
object LocationsManager {

  /**
   * プレイヤに対して座標を登録する
   * @param player Player
   * @param location Location
   * @return Option[(Int, Location)] 挿入に成功した場合、その要素のインデックスとLocationをタプルで返す
   */
  def set(player: Player, location: BukkitLocation): Option[(Int, Location)] = {
    val newLocations = Location.getLocations(player.getPersistentDataContainer) match {
      case Some(locations) => {
        val newLocation = Location(location)
        // 重複の挿入を許さないように
        if (locations.contains(newLocation)) return None
        locations :+ newLocation
      }
      case None => List(Location(location))
    }
    Location.storeLocations(player.getPersistentDataContainer, newLocations)
    Some((newLocations.length - 1, newLocations.last))
  }

  /**
   * プレイヤが登録した座標のListを取得する
   * @param player Player
   * @return Option[List[Location]] 座標のリスト
   */
  def get(player: Player): Option[List[Location]] = Location.getLocations(player.getPersistentDataContainer)

  /**
   * 登録した座標をまとめて削除する
   * @param player Player
   */
  def clear(player: Player): Unit = Location.clearLocations(player.getPersistentDataContainer)

  /**
   * 最後に登録した座標を削除する
   * @param player Player
   * @return Option[(Int, Location)] 削除した要素とインデックス
   */
  def undo(player: Player): Option[(Int, Location)] = {
    val container = player.getPersistentDataContainer
    for {
      locations <- Location.getLocations(container)
    } {
      val newLocations = locations.init
      if (newLocations.isEmpty) {
        Location.clearLocations(container)
        return Some((0, locations.last))
      }
      Location.storeLocations(container, newLocations)
      return Some((newLocations.length - 1, locations.last))
    }

    None
  }
}
