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

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:${Dependencies.Version.okhttp}")

    //Json Converter
    implementation("com.google.code.gson:gson:2.9.0")

    //Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Dependencies.Version.coroutines}")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Dependencies.Version.coroutines}")
    //Test
    api("junit:junit:4.13.2")
    //api("androidx.test.ext:junit-ktx:1.1.4-alpha04")
    api("org.mockito:mockito-core:4.4.0")
}
