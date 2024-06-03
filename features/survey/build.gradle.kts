plugins {
    id("androidLib")
    id("androidComposeLib")
    id("hilt")
}
dependencies{
    implementation(project(":core:theme"))
    implementation(project(":core:architecture"))
    implementation(project(":core:utils"))
    implementation(project(":features:survey-domain"))
    implementation(project(":features:survey-data"))

    testImplementation(project(":core:test"))

    implementation(libs.destinations)
    ksp(libs.destinations)
}