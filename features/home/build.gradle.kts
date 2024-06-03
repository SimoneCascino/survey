plugins {
    id("androidLib")
    id("androidComposeLib")
    id("ksp")
}
dependencies{
    implementation(project(":core:theme"))
    implementation(libs.destinations)
    ksp(libs.destinations)
}