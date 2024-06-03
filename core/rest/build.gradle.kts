plugins {
    id("androidLib")
    id("hilt")
    alias(libs.plugins.serialization)
}

dependencies {
    api(libs.bundles.retrofit)
}