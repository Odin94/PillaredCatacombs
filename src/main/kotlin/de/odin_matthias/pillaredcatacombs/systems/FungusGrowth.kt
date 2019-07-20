package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.attributes.FungusSpread
import de.odin_matthias.pillaredcatacombs.builders.EntityFactory
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.extensions.position
import de.odin_matthias.pillaredcatacombs.extensions.tryToFindAttribute
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.zircon.api.Sizes


object FungusGrowth : BaseBehavior<GameContext>(FungusSpread::class) {
    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val fungusSpread = entity.tryToFindAttribute(FungusSpread::class)
        val (spreadCount, maxSpread) = fungusSpread

        return if (spreadCount < maxSpread && Math.random() < 0.015) {
            context.world.findEmptyLocationWithin(
                    offset = entity.position
                            .withRelativeX(-1)
                            .withRelativeY(-1),
                    size = Sizes.create3DSize(3, 3, 0)).map { emptyLocation ->
                context.world.addEntity(EntityFactory.newFungus(fungusSpread), emptyLocation)
                fungusSpread.spreadCount++
            }

            true
        } else {
            false
        }
    }
}