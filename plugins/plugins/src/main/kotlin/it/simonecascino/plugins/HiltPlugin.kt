package it.simonecascino.plugins

import it.simonecascino.plugins.utils.DependencyConfiguration
import it.simonecascino.plugins.utils.dependencies
import it.simonecascino.plugins.utils.getPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project

class HiltPlugin: Plugin<Project> {

    override fun apply(target: Project) {

        val hiltPlugin = target.getPluginId("hilt")
        val ksp = target.getPluginId("ksp")

        target.pluginManager.apply {
            apply(hiltPlugin)
            apply(ksp)
        }

        target.dependencies(
            DependencyConfiguration.Implementation to "hilt",
            DependencyConfiguration.Implementation to "hilt-navigation",
            DependencyConfiguration.Ksp to "hilt-compiler"
        )

    }

}