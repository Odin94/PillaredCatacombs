package de.odin_matthias.pillaredcatacombs

import de.odin_matthias.pillaredcatacombs.view.StartView
import org.hexworks.zircon.api.SwingApplications

@Suppress("ConstantConditionIf")
fun main(args: Array<String>) {

    val application = SwingApplications.startApplication()

    application.dock(StartView())

}
