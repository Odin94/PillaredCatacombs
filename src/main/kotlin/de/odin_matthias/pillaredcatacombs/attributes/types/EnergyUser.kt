package de.odin_matthias.pillaredcatacombs.attributes.types

import de.odin_matthias.pillaredcatacombs.attributes.EnergyLevel
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import org.hexworks.amethyst.api.entity.EntityType


interface EnergyUser : EntityType

val GameEntity<EnergyUser>.energyLevel: EnergyLevel
    get() = findAttribute(EnergyLevel::class).get()