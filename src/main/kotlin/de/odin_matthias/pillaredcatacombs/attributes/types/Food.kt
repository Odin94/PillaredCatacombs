package de.odin_matthias.pillaredcatacombs.attributes.types

import de.odin_matthias.pillaredcatacombs.attributes.Item
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.extensions.tryToFindAttribute


interface Food : Item

val GameEntity<Food>.energy: Int
    get() = tryToFindAttribute(NutritionalValue::class).energy