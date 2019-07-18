package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.attributes.StairsDown
import de.odin_matthias.pillaredcatacombs.blocks.GameBlock
import de.odin_matthias.pillaredcatacombs.extensions.position
import de.odin_matthias.pillaredcatacombs.functions.logGameEvent
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.extensions.map


object StairDescender : BaseFacet<GameContext>() {
    override fun executeCommand(command: Command<out EntityType, GameContext>) = command.responseWhenCommandIs(MoveDown::class) { (context, player) ->
        val world = context.world
        val playerPos = player.position

        world.fetchBlockAt(playerPos).map { block ->
            if (block.hasStairsDown) {
                logGameEvent("You go down one level.")
                world.moveEntity(player, playerPos.withRelativeZ(-1))
                world.scrollOneDown()
            } else {
                logGameEvent("Can't go down here.")
            }
        }

        Consumed
    }
}

private val GameBlock.hasStairsDown: Boolean
    get() = this.entities.any { it.type == StairsDown }