pluginManagement {
    includeBuild("plugins")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Survey"
include(":app")
include(":core:architecture")
include(":core:theme")
include(":core:rest")
include(":features:home")
include(":features:survey")
include(":features:survey-data")
include(":features:survey-domain")
include(":core:utils")
include(":core:test")
