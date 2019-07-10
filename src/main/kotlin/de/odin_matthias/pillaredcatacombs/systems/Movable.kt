package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.attributes.Player
import de.odin_matthias.pillaredcatacombs.commands.MoveCamera
import de.odin_matthias.pillaredcatacombs.commands.MoveTo
import de.odin_matthias.pillaredcatacombs.extensions.GameCommand
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.extensions.position
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.CommandResponse
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.impl.Position3D


object Movable : BaseFacet<GameContext>() {
    override fun executeCommand(command: GameCommand<out EntityType>) =
            command.responseWhenCommandIs(MoveTo::class) { (context, entity, position) ->
                val previousPosition = entity.position
                var result: Response = Pass

                if (context.world.moveEntity(entity, position)) {
                    result = if (entity.type == Player) moveCameraResponse(context, entity, previousPosition) else Consumed
                }

                result
            }

    private fun moveCameraResponse(context: GameContext, entity: GameEntity<EntityType>, previousPosition: Position3D) =
            CommandResponse(MoveCamera(
                    context = context,
                    source = entity,
                    previousPosition = previousPosition
            ))
}