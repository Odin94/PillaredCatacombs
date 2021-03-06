package de.odin_matthias.pillaredcatacombs.extensions


import de.odin_matthias.pillaredcatacombs.attributes.*
import de.odin_matthias.pillaredcatacombs.flags.BlockOccupier
import de.odin_matthias.pillaredcatacombs.flags.VisionBlocker
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Attribute
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.cobalt.datatypes.extensions.orElseThrow
import org.hexworks.zircon.api.data.Tile
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf

var AnyGameEntity.position
    get() = tryToFindAttribute(EntityPosition::class).position
    set(value) {
        findAttribute(EntityPosition::class).map {
            it.position = value
        }
    }

val AnyGameEntity.blocksVision: Boolean
    get() = findAttribute(VisionBlocker::class).isPresent

val AnyGameEntity.tile: Tile
    get() = tryToFindAttribute(EntityTile::class).tile

val AnyGameEntity.occupiesBlock: Boolean
    get() = findAttribute(BlockOccupier::class).isPresent

val AnyGameEntity.isPlayer: Boolean
    get() = type == Player

fun <T : Attribute> AnyGameEntity.tryToFindAttribute(klass: KClass<T>): T = findAttribute(klass).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}'.")
}

fun AnyGameEntity.tryActionsOn(context: GameContext, target: AnyGameEntity): Response {
    var result: Response = Pass
    findAttribute(EntityActions::class).map {
        it.createActionsFor(context, this, target).forEach { action ->
            if (target.executeCommand(action) is Consumed) {
                result = Consumed
                return@forEach
            }
        }
    }

    return result
}

// access a GameEntity's combatstats with a simple entity.combatStats (because an amethyst Attribute is not a object-level-attrbute)
val GameEntity<Combatant>.combatStats: CombatStats
    get() = findAttribute(CombatStats::class).get()

fun GameEntity<Combatant>.whenHasNoHealthLeft(fn: () -> Unit) {
    if (combatStats.hp <= 0) {
        fn()
    }
}

// check the items of a Block for a given type (e.g. Item)
inline fun <reified T : EntityType> Iterable<AnyGameEntity>.filterType(): List<Entity<T, GameContext>> =
        filter { T::class.isSuperclassOf(it.type::class) }.toList() as List<Entity<T, GameContext>>

inline fun <reified T : EntityType> AnyGameEntity.whenTypeIs(fn: (Entity<T, GameContext>) -> Unit) {
    if (this.type::class.isSubclassOf(T::class)) {
        fn(this as Entity<T, GameContext>)
    }
}
