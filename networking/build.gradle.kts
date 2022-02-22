plugins {
    id("java-library")
    id("kotlin")
    id("io.gitlab.arturbosch.detekt")
}
apply(from = "../ktlint.gradle.kts")

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api(project(":base"))

    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.okhttp3:logging-interceptor:${Dependencies.Version.okhttp}")
    api("com.google.code.gson:gson:2.9.0")
}