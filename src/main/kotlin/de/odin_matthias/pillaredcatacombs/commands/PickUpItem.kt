package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.attributes.ItemHolder
import de.odin_matthias.pillaredcatacombs.extensions.GameCommand
import de.odin_matthias.pillaredcatacombs.extensions.GameItemHolder
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.zircon.api.data.impl.Position3D


data class PickUpItem(override val context: GameContext,
                      override val source: GameItemHolder,
                      val position: Position3D) : GameCommand<ItemHolder>