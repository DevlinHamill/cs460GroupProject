<<<<<<< HEAD
=======
// Top-level build file where you can add configuration options common to all sub-projects/modules.
<<<<<<< HEAD
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
<<<<<<< HEAD
=======
=======
plugins {
    alias(libs.plugins.androidApplication) apply false
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
>>>>>>> 8db2bd477f358e9f748ac9af52e711a1880efb9a
}