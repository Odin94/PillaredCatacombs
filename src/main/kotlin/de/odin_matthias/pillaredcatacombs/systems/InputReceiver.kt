package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.attributes.Player
import de.odin_matthias.pillaredcatacombs.commands.MoveDown
import de.odin_matthias.pillaredcatacombs.commands.MoveTo
import de.odin_matthias.pillaredcatacombs.commands.MoveUp
import de.odin_matthias.pillaredcatacombs.commands.PickUpItem
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.extensions.position
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.logging.api.LoggerFactory
import org.hexworks.zircon.api.data.impl.Position3D
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent


object InputReceiver : BaseBehavior<GameContext>() {
    private val logger = LoggerFactory.getLogger(this::class)

    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val (_, _, uiEvent, player) = context
        val position = player.position

        if (uiEvent is KeyboardEvent) {
            when (uiEvent.code) {
                KeyCode.KEY_W -> player.moveTo(position.withRelativeY(-1), context)
                KeyCode.KEY_A -> player.moveTo(position.withRelativeX(-1), context)
                KeyCode.KEY_S -> player.moveTo(position.withRelativeY(1), context)
                KeyCode.KEY_D -> player.moveTo(position.withRelativeX(1), context)
                KeyCode.KEY_R -> player.moveUp(context)
                KeyCode.KEY_F -> player.moveDown(context)
                KeyCode.KEY_P -> player.pickUpItem(position, context)
                else ->
                    logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
            }
        }

        return true
    }

    private fun GameEntity<Player>.moveTo(position: Position3D, context: GameContext) {
        executeCommand(MoveTo(context, this, position))
    }

    private fun GameEntity<Player>.moveUp(context: GameContext) {
        executeCommand(MoveUp(context, this))
    }

    private fun GameEntity<Player>.moveDown(context: GameContext) {
        executeCommand(MoveDown(context, this))
    }

    private fun GameEntity<Player>.pickUpItem(position: Position3D, context: GameContext) {
        executeCommand(PickUpItem(context, this, position))
    }
}