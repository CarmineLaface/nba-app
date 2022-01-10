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

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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

    //Androidx
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Dependencies.Version.lifecycle}")
    implementation("androidx.recyclerview:recyclerview:${Dependencies.Version.recyclerview}")
    implementation("androidx.fragment:fragment-ktx:${Dependencies.Version.fragment}")
    api("androidx.appcompat:appcompat:${Dependencies.Version.appcompat}")

    //Image loader
    implementation("io.coil-kt:coil:2.0.0-alpha06")
}
