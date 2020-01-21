package dev.ekuinox.spigot_magic_wand_tool

import org.bukkit.{Location => BukkitLocation}
import org.bukkit.entity.Player

/**
 * プレイヤが選択した座標情報を保管,管理する
 */
object LocationsManager {

  /**
   * プレイヤに対して座標を登録する
   * @param player Player
   * @param location Location
   * @return Int 追加した座標のインデックス
   */
  def set(player: Player, location: BukkitLocation): Int = {
    val newLocations = Location.getLocations(player.getPersistentDataContainer) match {
      case Some(locations) => locations :+ Location(location)
      case None => List(Location(location))
    }
    Location.storeLocations(player.getPersistentDataContainer, newLocations)
    newLocations.length - 1
  }

  /**
   * プレイヤが登録した座標のListを取得する
   * @param player Player
   * @return List[Location] 座標のリスト
   */
  def get(player: Player): List[Location] = Location.getLocations(player.getPersistentDataContainer).getOrElse(List())

  /**
   * 登録した座標をまとめて削除する
   * @param player Player
   */
  def clear(player: Player): Unit = Location.clearLocations(player.getPersistentDataContainer)

}
