package dev.ekuinox.spigot_magic_wand_tool.location

import dev.ekuinox.spigot_magic_wand_tool.SpigotMagicWandTool
import org.bukkit.persistence.{PersistentDataContainer, PersistentDataType}
import org.bukkit.{NamespacedKey, World, Location => BukkitLocation}

case class Location(x: Int, y: Int, z: Int) {
  def toList = List(x, y, z)
  def toBukkitLocation(world: World) = new BukkitLocation(world, x.toDouble, y.toDouble, z.toDouble)
}

case object Location {
  private val NAMESPACED_KEY_ROOT = "Locations"
  private val CONTAINER_DATA_TYPE = PersistentDataType.INTEGER_ARRAY
  private val ARRAY_GROUPED_SIZE = 3

  def apply(location: Array[Int]): Location = new Location(location(0), location(1), location(2))

  def apply(location: BukkitLocation) = new Location(location.getX.toInt, location.getY.toInt, location.getZ.toInt)

  def getLocations(world: World, container: PersistentDataContainer): Option[List[Location]] = {
    if (!container.has(makeNamespacedKey(world), CONTAINER_DATA_TYPE)) return None
    val data = container.get(makeNamespacedKey(world), CONTAINER_DATA_TYPE)
    if (data.length % ARRAY_GROUPED_SIZE != 0) return None
    Some(data.grouped(ARRAY_GROUPED_SIZE).toList.map(Location(_)))
  }

  def storeLocations(world: World, container: PersistentDataContainer, locations: List[Location]): Unit = {
    container.set(makeNamespacedKey(world), CONTAINER_DATA_TYPE, locations.flatMap(_.toList).toArray)
  }

  def clearLocations(world: World, container: PersistentDataContainer): Unit = container.remove(makeNamespacedKey(world))

  // ワールドに応じたNamespacedKeyを生成する
  private def makeNamespacedKey(world: World): NamespacedKey = SpigotMagicWandTool.getInstance.get.makeNamespacedKey(s"$NAMESPACED_KEY_ROOT.${world.getUID}")

}
