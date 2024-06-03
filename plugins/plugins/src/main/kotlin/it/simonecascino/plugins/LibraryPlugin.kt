package it.simonecascino.plugins

import com.android.build.api.dsl.LibraryExtension
import enableCompose
import it.simonecascino.plugins.utils.DependencyConfiguration
import it.simonecascino.plugins.utils.dependencies
import it.simonecascino.plugins.utils.getPluginId
import it.simonecascino.plugins.utils.getVersion
import kotlinOptions
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class LibraryPlugin: Plugin<Project> {

    override fun apply(target: Project) {

        val libraryPlugin = target.getPluginId("androidLibrary")
        val kotlinPlugin = target.getPluginId("jetbrainsKotlinAndroid")

        target.pluginManager.apply {
            apply(libraryPlugin)
            apply(kotlinPlugin)
        }

        target.extensions.configure<LibraryExtension>{

            namespace = "it.simonecascino.${target.name.replace("-", "_")}"

            compileSdk = 34

            defaultConfig {
                minSdk = 26

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }

            buildTypes {
                release {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_19
                targetCompatibility = JavaVersion.VERSION_19
            }
            kotlinOptions {
                jvmTarget = "19"
            }

        }

        target
            .extensions
            .getByType<LibraryExtension>()
            .enableCompose(target.getVersion("compose-compiler"))

        target.dependencies(
            DependencyConfiguration.Implementation to "androidx-core-ktx",
            DependencyConfiguration.Implementation to "lifecycle-viewmodel",

            DependencyConfiguration.TestImplementation to "junit",
            DependencyConfiguration.TestImplementation to "truth",
            DependencyConfiguration.TestImplementation to "coroutines-test",
            DependencyConfiguration.TestImplementation to "core-test",
            DependencyConfiguration.AndroidTestImplementation to "androidx-junit",
            DependencyConfiguration.AndroidTestImplementation to "androidx-espresso-core"
        )

    }


}