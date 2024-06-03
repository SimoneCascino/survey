plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}

dependencies{
    implementation(libs.coroutines)
    implementation(libs.coroutines.test)
    implementation(libs.junit)
}