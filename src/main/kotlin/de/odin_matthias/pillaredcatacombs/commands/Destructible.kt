package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType


object Destructible : BaseFacet<GameContext>() {
    override fun executeCommand(command: Command<out EntityType, GameContext>) = command.responseWhenCommandIs(Destroy::class) { (context, _, target) ->
        context.world.removeEntity(target)
        Consumed
    }

}