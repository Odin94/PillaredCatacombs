package de.odin_matthias.pillaredcatacombs.attributes


import de.odin_matthias.pillaredcatacombs.attributes.types.EnergyUser
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.extensions.GameItem
import de.odin_matthias.pillaredcatacombs.extensions.GameItemHolder
import org.hexworks.amethyst.api.base.BaseEntityType
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.GraphicalTile
import org.hexworks.zircon.api.data.Tile

// completely new entities need to get a custom tile (GameTileRepository) and a factory function (EntityFactory)

object FogOfWarTypeType : BaseEntityType()

object Player : BaseEntityType(
        name = "player"), Combatant, ItemHolder, EnergyUser

object Fungus : BaseEntityType(
        name = "fungus"), Combatant

object Ghoul : BaseEntityType(
        name = "ghoul"), Combatant

object Wall : BaseEntityType(
        name = "wall")

object StairsDown : BaseEntityType(
        name = "stairs down")

object StairsUp : BaseEntityType(
        name = "stairs up")

object StoneMaskFragment : BaseEntityType(
        name = "Stone Mask Fragment",
        description = "A fragment of a stone mask. Assembling a whole mask will unleash unfathomable power."), Item

object GhoulBlood : BaseEntityType(
        name = "Ghoul blood",
        description = "Juicy ghoul blood. It's drinkable, but not tasty")


interface Combatant : EntityType

interface Item : EntityType

val GameEntity<Item>.tile: Tile
    get() = findAttribute(EntityTile::class).get().tile

val GameEntity<Item>.iconTile: GraphicalTile
    get() = findAttribute(ItemIcon::class).get().iconTile

interface ItemHolder : EntityType

fun GameItemHolder.addItem(item: GameItem) = inventory.addItem(item)

fun GameItemHolder.removeItem(item: GameItem) = inventory.removeItem(item)

val GameItemHolder.inventory: Inventory
    get() = findAttribute(Inventory::class).get()