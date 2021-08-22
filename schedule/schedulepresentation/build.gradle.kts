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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":common"))
    api(project(":game:gamedomain"))
    implementation(project(":navigation:navpublic"))

    //Android core
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Dependencies.Version.lifecycle}")
    implementation("androidx.fragment:fragment-ktx:${Dependencies.Version.fragment}")

    //UI
    implementation("com.google.android.material:material:${Dependencies.Version.material}")
    implementation("androidx.recyclerview:recyclerview:${Dependencies.Version.recyclerview}")
    implementation("androidx.constraintlayout:constraintlayout:${Dependencies.Version.constraintlayout}")

    //Test
    testImplementation(project(":test"))
}
