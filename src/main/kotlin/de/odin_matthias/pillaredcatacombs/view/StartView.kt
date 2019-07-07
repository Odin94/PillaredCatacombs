package de.odin_matthias.pillaredcatacombs.view

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Button
import org.hexworks.zircon.api.component.Component
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.kotlin.onMouseReleased
import org.hexworks.zircon.api.mvc.base.BaseView


class StartView : BaseView() {
    override val theme = ColorThemes.arc()

    override fun onDock() {
        val msg = "Welcome to Pillared Catacombs"
        val header = buildHeader(msg)

        screen.addComponent(header)
        screen.addComponent(buildStartButton(header))
    }

    private fun buildHeader(msg: String) = Components.textBox()
            .withContentWidth(msg.length)
            .addHeader(msg)
            .addNewLine()
            .withAlignmentWithin(screen, ComponentAlignment.CENTER)
            .build()

    private fun buildStartButton(header: Component): Button {
        val startButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_CENTER)
                .withText("Start!")
                .wrapSides(false)
                .withBoxType(BoxType.SINGLE)
                .wrapWithShadow()
                .wrapWithBox()
                .build()

        startButton.onMouseReleased {
            replaceWith(PlayView())
            close()
        }

        return startButton
    }
}