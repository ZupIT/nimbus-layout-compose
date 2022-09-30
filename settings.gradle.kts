pluginManagement {
    repositories {
        mavenLocal()
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Nimbus Compose Layout"
include(":layout")
include(":sample")
include(":store")
