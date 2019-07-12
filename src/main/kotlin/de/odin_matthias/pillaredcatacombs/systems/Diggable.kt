package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.commands.Dig
import de.odin_matthias.pillaredcatacombs.extensions.GameCommand
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType


object Diggable : BaseFacet<GameContext>() {
    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Dig::class) { (context, _, target) ->
        context.world.removeEntity(target)
        Consumed
    }
}