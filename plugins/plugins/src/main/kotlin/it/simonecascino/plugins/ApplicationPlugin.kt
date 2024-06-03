package it.simonecascino.plugins

import com.android.build.api.dsl.ApplicationExtension
import enableCompose
import kotlinOptions
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import it.simonecascino.plugins.utils.DependencyConfiguration
import it.simonecascino.plugins.utils.dependencies
import it.simonecascino.plugins.utils.getPluginId
import it.simonecascino.plugins.utils.getVersion

class ApplicationPlugin: Plugin<Project> {

    override fun apply(target: Project) {

        val applicationPlugin = target.getPluginId("androidApplication")
        val kotlinPlugin = target.getPluginId("jetbrainsKotlinAndroid")

        target.pluginManager.apply {
            apply(applicationPlugin)
            apply(kotlinPlugin)
        }

        target.extensions.configure<ApplicationExtension> {

            apply {

                namespace = "it.simonecascino.survey"
                compileSdk = 34

                defaultConfig {
                    applicationId = "it.simonecascino.survey"
                    minSdk = 26
                    targetSdk = 34
                    versionCode = 1
                    versionName = "1.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
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

        }

        target
            .extensions
            .getByType<ApplicationExtension>()
            .enableCompose(target.getVersion("compose-compiler"))

        target.dependencies(
            DependencyConfiguration.Implementation to "androidx-core-ktx",
            //DependencyConfiguration.Implementation to "appcompat",
            //DependencyConfiguration.Implementation to "material",
            DependencyConfiguration.Implementation to "compose-activity",
            DependencyConfiguration.Implementation to "compose-ui",
            DependencyConfiguration.Implementation to "compose-ui-tooling",
            DependencyConfiguration.Implementation to "compose-material3",
            DependencyConfiguration.Implementation to "compose-navigation",
            DependencyConfiguration.TestImplementation to "junit",
            DependencyConfiguration.AndroidTestImplementation to "androidx-junit",
            DependencyConfiguration.AndroidTestImplementation to "androidx-espresso-core"
        )

    }
}