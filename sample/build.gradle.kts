plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("com.google.devtools.ksp") version "1.6.10-1.0.4"
}

val serializationVersion = "1.3.2"
val ktorVersion = "2.0.0"

dependencies {
    implementation(project(":layout"))
    implementation("br.com.zup.nimbus:nimbus-compose:${rootProject.extra["nimbus_compose_version"]}")
    implementation("br.com.zup.nimbus:nimbus-compose-processor:${rootProject.extra["nimbus_compose_version"]}")
    ksp("br.com.zup.nimbus:nimbus-compose-processor:${rootProject.extra["nimbus_compose_version"]}")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.navigation:navigation-compose:2.5.1")
    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.ui:ui-tooling-preview:${rootProject.extra["compose_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
    debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "br.com.zup.nimbus.android.layout"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/debug/kotlin")
    }
}
