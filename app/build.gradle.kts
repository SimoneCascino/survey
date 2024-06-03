plugins {
    id("androidApp")
    id("hilt")
}

dependencies{
    implementation(libs.destinations)
    ksp(libs.destinations)
    implementation(project(":core:theme"))
    implementation(project(":core:architecture"))
    implementation(project(":features:home"))
    implementation(project(":features:survey"))
}