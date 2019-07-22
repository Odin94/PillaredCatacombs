package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.attributes.Item
import de.odin_matthias.pillaredcatacombs.attributes.addItem
import de.odin_matthias.pillaredcatacombs.commands.PickUpItem
import de.odin_matthias.pillaredcatacombs.extensions.filterType
import de.odin_matthias.pillaredcatacombs.extensions.isPlayer
import de.odin_matthias.pillaredcatacombs.functions.logGameEvent
import de.odin_matthias.pillaredcatacombs.game.GameContext
import de.odin_matthias.pillaredcatacombs.world.World
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.cobalt.datatypes.extensions.flatMap
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.zircon.api.data.impl.Position3D


object ItemPicker : BaseFacet<GameContext>() {
    override fun executeCommand(command: Command<out EntityType, GameContext>) = command.responseWhenCommandIs(PickUpItem::class) { (context, itemHolder, position) ->
        val world = context.world

        world.findTopItem(position).map { item ->
            if (itemHolder.addItem(item)) {
                world.removeEntity(item)
                val subject = if (itemHolder.isPlayer) "You" else "The $itemHolder"
                val verb = if (itemHolder.isPlayer) "pick up" else "picks up"
                logGameEvent("$subject $verb the $item")
            }
        }

        Consumed
    }

    private fun World.findTopItem(position: Position3D) =
            this.fetchBlockAt(position).flatMap { block ->
                Maybe.ofNullable(block.entities.filterType<Item>().firstOrNull())
            }
}