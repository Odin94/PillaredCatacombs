package de.odin_matthias.pillaredcatacombs.view

import de.odin_matthias.pillaredcatacombs.blocks.GameBlock
import de.odin_matthias.pillaredcatacombs.config.GameConfig
import de.odin_matthias.pillaredcatacombs.game.Game
import de.odin_matthias.pillaredcatacombs.game.GameBuilder
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.GameComponents
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.extensions.onKeyboardEvent
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.mvc.base.BaseView
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.Processed


class PlayView(private val game: Game = GameBuilder.defaultGame()) : BaseView() {
    override val theme = ColorThemes.arc()

    override fun onDock() {
        screen.addComponent(buildGameComponent())
        screen.addComponent(buildSidebar())
        screen.addComponent(buildLogArea())

        screen.onKeyboardEvent(KeyboardEventType.KEY_PRESSED) { event, _ ->
            game.world.update(screen, event, game)
            Processed
        }
    }

    private fun buildSidebar() = Components.panel()
            .withSize(GameConfig.SIDEBAR_WIDTH, GameConfig.WINDOW_HEIGHT)
            .wrapWithBox()
            .build()

    private fun buildLogArea() = Components.logArea()
            .withTitle("Log")
            .wrapWithBox()
            .withSize(GameConfig.WINDOW_WIDTH - GameConfig.SIDEBAR_WIDTH, GameConfig.LOG_AREA_HEIGHT)
            .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_RIGHT)
            .build()

    private fun buildGameComponent() = GameComponents.newGameComponentBuilder<Tile, GameBlock>()
            .withGameArea(game.world)
            .withVisibleSize(game.world.visibleSize())
            .withProjectionMode(ProjectionMode.TOP_DOWN)
            .withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)
            .build()
}