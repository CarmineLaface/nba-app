plugins {
    id("java-library")
    id("kotlin")
    id("io.gitlab.arturbosch.detekt")
}
apply(from = "../../ktlint.gradle.kts")

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api(project(":playerdetail:playerdomain"))
    api(project(":team:teamdomain"))
    implementation(project(":networking"))

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Dependencies.Version.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Dependencies.Version.coroutines}")
}