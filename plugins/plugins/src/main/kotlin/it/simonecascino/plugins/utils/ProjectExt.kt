package it.simonecascino.plugins.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.regex.Pattern

val Project.libs get() = rootProject
    .extensions
    .getByType(VersionCatalogsExtension::class)
    .named("libs")

fun Project.getPluginId(alias: String) = libs
    .findPlugin(alias)
    .get()
    .get()
    .pluginId

fun Project.getLibrary(alias: String) = libs
    .findLibrary(alias)
    .get()

fun Project.getVersion(alias: String) = libs
    .findVersion(alias)
    .get()
    .toString()

fun Project.getFlavorName() = run {
    val pattern: Pattern = Pattern.compile("assemble(\\w+)(Release|Debug)")

    val matcher = pattern.matcher(gradle.startParameter.taskRequests.toString())
    if (matcher.find()) {
        matcher.group(1).lowercase()
    } else {
        ""
    }
}

fun Project.dependencies(
    vararg specs: Pair<DependencyConfiguration, String>
){
    val project = this

    dependencies {
        specs.forEach {
            add(it.first.getConfigName(), project.getLibrary(it.second))
        }
    }
}

enum class DependencyConfiguration{
    Implementation,
    Api,
    Kapt,
    Ksp,
    TestImplementation,
    AndroidTestImplementation;

    fun getConfigName() = name.replaceFirstChar { it.lowercase() }

}