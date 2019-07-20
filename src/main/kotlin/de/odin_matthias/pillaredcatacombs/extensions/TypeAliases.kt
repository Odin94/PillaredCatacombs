package de.odin_matthias.pillaredcatacombs.extensions


import de.odin_matthias.pillaredcatacombs.attributes.Item
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType

typealias AnyGameEntity = Entity<EntityType, GameContext>
typealias GameEntity<T> = Entity<T, GameContext>
typealias GameCommand<T> = Command<T, GameContext>
typealias GameItem = GameEntity<Item>