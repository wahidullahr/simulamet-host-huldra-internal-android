
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.4") // Ensure this is the correct version for 'com.android.application'
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10") // Ensure this is the correct version for 'org.jetbrains.kotlin.android'
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
        // Add any other classpath dependencies here
    }
}

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    // Do not include classpath dependencies here
}

