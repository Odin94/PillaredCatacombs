package de.odin_matthias.pillaredcatacombs.view

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Button
import org.hexworks.zircon.api.component.Component
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.kotlin.onMouseReleased
import org.hexworks.zircon.api.mvc.base.BaseView
import kotlin.system.exitProcess


class LoseView : BaseView() {

    override val theme = ColorThemes.arc()

    override fun onDock() {
        val msg = "Game Over"
        val header = buildHeader(msg)

        screen.addComponent(header)
        screen.addComponent(buildRestartButton(header))
        screen.addComponent(buildExitButton(header))
    }

    private fun buildHeader(msg: String) = Components.textBox()
            .withContentWidth(30)
            .addHeader(msg)
            .withAlignmentWithin(screen, ComponentAlignment.CENTER)
            .build()

    private fun buildRestartButton(header: Component): Button {
        val restartButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_LEFT)
                .withText("Restart")
                .wrapSides(false)
                .wrapWithBox()
                .withBoxType(BoxType.SINGLE)
                .build()

        restartButton.onMouseReleased {
            replaceWith(PlayView())
            close()
        }

        return restartButton
    }

    private fun buildExitButton(header: Component): Button {
        val exitButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_RIGHT)
                .withText("Quit")
                .wrapSides(false)
                .wrapWithBox()
                .withBoxType(BoxType.SINGLE)
                .build()

        exitButton.onMouseReleased {
            exitProcess(0)
        }

        return exitButton
    }
}