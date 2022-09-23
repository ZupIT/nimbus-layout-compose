plugins{
    id("io.gitlab.arturbosch.detekt") version "1.20.0"
}

buildscript {
    val compose_version by extra("1.1.0")
    val accompanist_version by extra("0.24.11-rc")
    val nimbus_compose_version by extra("1.0.0-alpha34")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.15.1")
        classpath ("com.karumi:shot:5.14.1")
    }
}

allprojects {
    apply("$rootDir/detekt.gradle")
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
