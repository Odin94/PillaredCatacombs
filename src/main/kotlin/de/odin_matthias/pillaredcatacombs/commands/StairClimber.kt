package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.attributes.StairsUp
import de.odin_matthias.pillaredcatacombs.blocks.GameBlock
import de.odin_matthias.pillaredcatacombs.extensions.position
import de.odin_matthias.pillaredcatacombs.functions.logGameEvent
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.extensions.map


object StairClimber : BaseFacet<GameContext>() {
    override fun executeCommand(command: Command<out EntityType, GameContext>) = command.responseWhenCommandIs(MoveUp::class) { (context, player) ->
        val world = context.world
        val playerPos = player.position

        world.fetchBlockAt(playerPos).map { block ->
            if (block.hasStairsUp) {
                logGameEvent("You moved up one level.")
                world.moveEntity(player, playerPos.withRelativeZ(1))
                world.scrollOneUp()
            } else {
                logGameEvent("Can't move up here.")
            }
        }

        Consumed
    }
}

private val GameBlock.hasStairsUp: Boolean
get() = this.entities.any { it.type == StairsUp }