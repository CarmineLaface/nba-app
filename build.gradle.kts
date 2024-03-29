// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.Version.kotlin}")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.20.0-RC2")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.google.com")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
