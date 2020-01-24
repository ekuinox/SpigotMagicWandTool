package dev.ekuinox.spigot_magic_wand_tool.location

import org.bukkit.entity.Player
import org.bukkit.{World, Location => BukkitLocation}

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
    val newLocations = Location.getLocations(location.getWorld, player.getPersistentDataContainer) match {
      case Some(locations) => {
        val newLocation = Location(location)
        // 重複の挿入を許さないように
        if (locations.contains(newLocation)) return None
        locations :+ newLocation
      }
      case None => List(Location(location))
    }
    Location.storeLocations(location.getWorld, player.getPersistentDataContainer, newLocations)
    Some((newLocations.length - 1, newLocations.last))
  }

  /**
   * プレイヤが登録した座標のListを取得する
   * @param player Player
   * @return Option[List[Location]] 座標のリスト
   */
  def get(player: Player, world: World): Option[List[Location]] = Location.getLocations(world, player.getPersistentDataContainer)

  /**
   * プレイヤの現在いるワールドを使って get する
   * @param player Player
   * @return Option[List[Location]]
   */
  def get(player: Player): Option[List[Location]] = get(player, player.getWorld)

  /**
   * 登録した座標をまとめて削除する
   * @param player Player
   * @param world World
   */
  def clear(player: Player, world: World): Unit = Location.clearLocations(world, player.getPersistentDataContainer)

  /**
   * プレイヤの現在いるワールドを使って clear する
   * @param player world
   */
  def clear(player: Player): Unit = clear(player, player.getWorld)

  /**
   * 最後に登録した座標を削除する
   * @param player Player
   * @param world World
   * @return Option[(Int, Location)] 削除した要素とインデックス
   */
  def undo(player: Player, world: World): Option[(Int, Location)] = {
    val container = player.getPersistentDataContainer
    for {
      locations <- Location.getLocations(world, container)
    } {
      val newLocations = locations.init
      if (newLocations.isEmpty) {
        Location.clearLocations(world, container)
        return Some((0, locations.last))
      }
      Location.storeLocations(world, container, newLocations)
      return Some((newLocations.length, locations.last))
    }

    None
  }

  /**
   * プレイヤの現在いるワールドを使って undo する
   * @param player Player
   * @return
   */
  def undo(player: Player): Option[(Int, Location)] = undo(player, player.getWorld)
}
