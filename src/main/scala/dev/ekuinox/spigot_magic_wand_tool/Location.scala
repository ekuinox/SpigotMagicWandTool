package dev.ekuinox.spigot_magic_wand_tool

import org.bukkit.NamespacedKey
import org.bukkit.persistence.{PersistentDataContainer, PersistentDataType}
import org.bukkit.{Location => BukkitLocation}

case class Location(x: Int, y: Int, z: Int) {
  def toList = List(x, y, z)
}

case object Location {
  private lazy val NAMESPACED_KEY: NamespacedKey = SpigotMagicWandTool.getInstance.get.makeNamespacedKey("Locations")
  private val CONTAINER_DATA_TYPE = PersistentDataType.INTEGER_ARRAY
  private val ARRAY_GROUPED_SIZE = 3

  def apply(location: Array[Int]): Location = new Location(location(0), location(1), location(2))

  def apply(location: BukkitLocation) = new Location(location.getX.toInt, location.getY.toInt, location.getZ.toInt)

  def getLocations(container: PersistentDataContainer): Option[List[Location]] = {
    if (!container.has(NAMESPACED_KEY, CONTAINER_DATA_TYPE)) return None
    val data = container.get(NAMESPACED_KEY, CONTAINER_DATA_TYPE)
    if (data.length % ARRAY_GROUPED_SIZE != 0) return None
    Some(data.grouped(ARRAY_GROUPED_SIZE).toList.map(Location(_)))
  }

  def storeLocations(container: PersistentDataContainer, locations: List[Location]): Unit = {
    container.set(NAMESPACED_KEY, CONTAINER_DATA_TYPE, locations.flatMap(_.toList).toArray)
  }

  def clearLocations(container: PersistentDataContainer): Unit = container.remove(NAMESPACED_KEY)

}
