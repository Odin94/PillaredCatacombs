package de.odin_matthias.pillaredcatacombs.view

import de.odin_matthias.pillaredcatacombs.GameBlock
import de.odin_matthias.pillaredcatacombs.config.GameConfig
import de.odin_matthias.pillaredcatacombs.model.Game
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.GameComponents
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.mvc.base.BaseView


class PlayView(private val game: Game = Game.create()) : BaseView() {
    override val theme = ColorThemes.arc()

    override fun onDock() {
        screen.addComponent(buildGameComponent())
        screen.addComponent(buildSidebar())
        screen.addComponent(buildLogArea())
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