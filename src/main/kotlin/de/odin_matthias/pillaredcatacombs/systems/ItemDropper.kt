package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.attributes.removeItem
import de.odin_matthias.pillaredcatacombs.commands.DropItem
import de.odin_matthias.pillaredcatacombs.extensions.isPlayer
import de.odin_matthias.pillaredcatacombs.functions.logGameEvent
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType


object ItemDropper : BaseFacet<GameContext>() {
    override fun executeCommand(command: Command<out EntityType, GameContext>) = command.responseWhenCommandIs(DropItem::class) { (context, itemHolder, item, position) ->
        val world = context.world
        if (itemHolder.removeItem(item)) {
            world.addEntity(item, position)
            val subject = if (itemHolder.isPlayer) "You" else "The $itemHolder"
            val verb = if (itemHolder.isPlayer) "drop" else "drops"
            logGameEvent("$subject $verb the $item")
        }

        Consumed
    }
}