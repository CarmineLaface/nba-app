plugins {
    id("com.android.application")
    kotlin("android")
    id("io.gitlab.arturbosch.detekt")
}
apply(from = "../ktlint.gradle.kts")

android {
    compileSdk = Dependencies.Version.compileSdk

    defaultConfig {
        applicationId = "it.laface.fantanba"
        minSdk = Dependencies.Version.minSdk
        targetSdk = Dependencies.Version.targetSdk
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        allWarningsAsErrors = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":navigation:navimpl"))
    implementation(project(":navigation:navpublic"))
    implementation(project(":news:newspresentation"))
    implementation(project(":playerlist:playerlistpresentation"))
    implementation(project(":playerdetail:playerpresentation"))
    implementation(project(":team:teampresentation"))
    implementation(project(":playerlist:playerlistapi"))
    implementation(project(":news:newsnetworking"))
    implementation(project(":schedule:scheduleapi"))
    implementation(project(":statistic:statsapi"))
    implementation(project(":team:teamapi"))
    implementation(project(":ranking:rankingnetworking"))
    implementation(project(":playerdetail:playerapi"))
    implementation(project(":ranking:rankingpresentation"))
    implementation(project(":schedule:schedulepresentation"))
    implementation(project(":statistic:statspresentation"))
    implementation(project(":game:gamenetworking"))
    implementation(project(":game:gamepresentation"))

    //Android
    implementation("androidx.appcompat:appcompat:${Dependencies.Version.appcompat}")
    implementation("com.google.android.material:material:${Dependencies.Version.material}")
    implementation("androidx.fragment:fragment-ktx:${Dependencies.Version.fragment}")
    implementation("androidx.activity:activity-ktx:${Dependencies.Version.activity}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Dependencies.Version.lifecycle}")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Dependencies.Version.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Dependencies.Version.coroutines}")

    //Leak detection
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.7")
}
