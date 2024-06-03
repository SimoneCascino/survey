plugins {
    `kotlin-dsl`
}
dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {

    plugins {

        register("androidApplication") {
            id = "androidApp"
            implementationClass = "it.simonecascino.plugins.ApplicationPlugin"
        }
        register("androidLibrary") {
            id = "androidLib"
            implementationClass = "it.simonecascino.plugins.LibraryPlugin"
        }
        register("androidLibraryCompose") {
            id = "androidComposeLib"
            implementationClass = "it.simonecascino.plugins.LibraryComposePlugin"
        }
        register("hiltPlugin") {
            id = "hilt"
            implementationClass = "it.simonecascino.plugins.HiltPlugin"
        }
        register("kspPlugin") {
            id = "ksp"
            implementationClass = "it.simonecascino.plugins.KspPlugin"
        }


    }
}