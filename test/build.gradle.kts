plugins {
    id("com.android.library")
    kotlin("android")
    id("io.gitlab.arturbosch.detekt")
}
apply(from = "../ktlint.gradle.kts")

android {
    compileSdk = Dependencies.Version.compileSdk

    defaultConfig {
        minSdk = Dependencies.Version.minSdk
        targetSdk = Dependencies.Version.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:${Dependencies.Version.okhttp}")

    //Json Converter
    implementation("com.google.code.gson:gson:2.8.9")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Dependencies.Version.coroutines}")
    //Test
    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit-ktx:1.1.4-alpha03")
    api("org.mockito:mockito-core:4.2.0")

    api("com.adevinta.android:barista:4.2.0") {
        exclude(group = "org.jetbrains.kotlin")
    }

    api("org.robolectric:robolectric:4.7.3")
}
