// Top-level build file where you can add configuration options common to all sub-projects/modules.
<<<<<<< HEAD
buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
=======
plugins {
    alias(libs.plugins.androidApplication) apply false
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
}