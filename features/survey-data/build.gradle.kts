plugins {
    id("androidLib")
    id("hilt")
    alias(libs.plugins.serialization)
}
dependencies{
    implementation(project(":core:utils"))
    implementation(project(":core:rest"))
    implementation(project(":features:survey-domain"))

    testImplementation(project(":core:test"))
}