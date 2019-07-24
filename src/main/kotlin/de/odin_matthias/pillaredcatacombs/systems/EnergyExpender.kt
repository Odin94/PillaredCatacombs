package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.attributes.EnergyLevel
import de.odin_matthias.pillaredcatacombs.attributes.types.EnergyUser
import de.odin_matthias.pillaredcatacombs.attributes.types.energyLevel
import de.odin_matthias.pillaredcatacombs.commands.Destroy
import de.odin_matthias.pillaredcatacombs.commands.Expend
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.extensions.whenTypeIs
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseActor
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType


object EnergyExpender : BaseActor<GameContext>(EnergyLevel::class) {
    override fun executeCommand(command: Command<out EntityType, GameContext>) = command.responseWhenCommandIs(Expend::class) { (context, entity, energy) ->
        entity.energyLevel.currentEnergy -= energy
        checkStarvation(context, entity, entity.energyLevel)

        Consumed
    }

    override fun update(entity: Entity<EntityType, GameContext>, context: GameContext): Boolean {
        entity.whenTypeIs<EnergyUser> {
            entity.executeCommand(Expend(
                    context = context,
                    source = it,
                    energy = 2))
        }

        return true
    }

    private fun checkStarvation(context: GameContext, entity: GameEntity<EntityType>, energyLevel: EnergyLevel) {
        if (energyLevel.currentEnergy <= 0) {
            entity.executeCommand(Destroy(
                    context = context,
                    source = entity,
                    target = entity,
                    cause = "starvation"))
        }
    }
}