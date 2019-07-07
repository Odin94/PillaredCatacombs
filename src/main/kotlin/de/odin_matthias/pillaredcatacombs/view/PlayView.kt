package de.odin_matthias.pillaredcatacombs.view

import de.odin_matthias.pillaredcatacombs.config.GameConfig
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.mvc.base.BaseView


class PlayView : BaseView() {
    override val theme = ColorThemes.arc()

    override fun onDock() {
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
            .withSize(GameConfig.WINDOW_WIDTH - GameConfig.SIDEBAR_WIDTH - 2, GameConfig.LOG_AREA_HEIGHT)
            .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_RIGHT)
            .build()
}