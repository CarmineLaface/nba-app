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
    implementation(project(":playerdetail:playerdomain"))
    implementation(project(":networking"))

    testImplementation(project(":test"))
}