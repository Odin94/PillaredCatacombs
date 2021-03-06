package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.extensions.GameCommand
import de.odin_matthias.pillaredcatacombs.extensions.GameItem
import de.odin_matthias.pillaredcatacombs.extensions.GameItemHolder
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.impl.Position3D


data class DropItem(override val context: GameContext,
                    override val source: GameItemHolder,
                    val item: GameItem,
                    val position: Position3D) : GameCommand<EntityType>