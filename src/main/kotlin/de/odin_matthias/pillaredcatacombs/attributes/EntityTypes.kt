package de.odin_matthias.pillaredcatacombs.attributes


import org.hexworks.amethyst.api.base.BaseEntityType
import org.hexworks.amethyst.api.entity.EntityType

// completely new entities need to get a custom tile (GameTileRepository) and a factory function (EntityFactory)

object Player : BaseEntityType(
        name = "player"), Combatant

object Wall : BaseEntityType(
        name = "wall")

object Fungus : BaseEntityType(
        name = "fungus"), Combatant

interface Combatant : EntityType