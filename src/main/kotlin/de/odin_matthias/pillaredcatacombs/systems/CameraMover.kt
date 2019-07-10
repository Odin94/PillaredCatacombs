package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.commands.MoveCamera
import de.odin_matthias.pillaredcatacombs.config.GameConfig.CAMERA_DELAY
import de.odin_matthias.pillaredcatacombs.extensions.position
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType


object CameraMover : BaseFacet<GameContext>() {
    override fun executeCommand(command: Command<out EntityType, GameContext>) =
            command.responseWhenCommandIs(MoveCamera::class) { (context, source, previousPosition) ->
                val world = context.world
                val screenPos = source.position - world.visibleOffset()
                val halfWidth = world.visibleSize().xLength / 2
                val halfHeight = world.visibleSize().yLength / 2
                val currentPosition = source.position

                when {
                    previousPosition.y > currentPosition.y && screenPos.y + CAMERA_DELAY < halfHeight -> world.scrollOneBackward()

                    previousPosition.y < currentPosition.y && screenPos.y - CAMERA_DELAY > halfHeight -> world.scrollOneForward()

                    previousPosition.x > currentPosition.x && screenPos.x + CAMERA_DELAY < halfWidth -> world.scrollOneLeft()

                    previousPosition.x < currentPosition.x && screenPos.x - CAMERA_DELAY > halfWidth -> world.scrollOneRight()
                }

                Consumed
            }
}