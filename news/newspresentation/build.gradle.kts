plugins {
    id("com.android.library")
    kotlin("android")
    id("io.gitlab.arturbosch.detekt")
}
apply(from = "../../ktlint.gradle")

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
    buildFeatures {
        viewBinding = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":common"))
    api(project(":news:newsdomain"))
    implementation(project(":navigation:navpublic"))

    //Android core
    implementation("androidx.core:core-ktx:${Dependencies.Version.core}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Dependencies.Version.lifecycle}")
    implementation("androidx.fragment:fragment-ktx:${Dependencies.Version.fragment}")

    //UI
    implementation("com.google.android.material:material:${Dependencies.Version.material}")
    implementation("androidx.recyclerview:recyclerview:${Dependencies.Version.recyclerview}")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:${Dependencies.Version.constraintlayout}")

    //Browser
    implementation("androidx.browser:browser:1.3.0")

    testImplementation(project(":test"))
    debugImplementation("androidx.fragment:fragment-testing:${Dependencies.Version.fragment}")
}
