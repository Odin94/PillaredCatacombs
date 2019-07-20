package de.odin_matthias.pillaredcatacombs.attributes


import org.hexworks.amethyst.api.base.BaseEntityType
import org.hexworks.amethyst.api.entity.EntityType

// completely new entities need to get a custom tile (GameTileRepository) and a factory function (EntityFactory)

object FogOfWarTypeType : BaseEntityType()

object Player : BaseEntityType(
        name = "player"), Combatant

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

interface Combatant : EntityType