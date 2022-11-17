plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // needed for component auto-deserialization
    id("com.google.devtools.ksp") version "1.6.10-1.0.4"
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "br.com.zup.nimbus.store"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

dependencies {
    // Layout lib for Nimbus
    implementation(project(":layout"))
    // Main Nimbus lib for Compose
    implementation("br.com.zup.nimbus:nimbus-compose:${rootProject.extra["nimbus_compose_version"]}")
    // Component auto-deserialization on Nimbus
    implementation("br.com.zup.nimbus:nimbus-compose-annotation:${rootProject.extra["nimbus_compose_version"]}")
    ksp("br.com.zup.nimbus:nimbus-compose-processor:${rootProject.extra["nimbus_compose_version"]}")
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.5.2")
    // Other
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.ui:ui-tooling-preview:${rootProject.extra["compose_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
    debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${rootProject.extra["compose_version"]}")
}

// Component auto-deserialization on Nimbus: pick up the code automatically generated
kotlin {
    sourceSets.debug {
        kotlin.srcDir("build/generated/ksp/debug/kotlin")
    }
    sourceSets.release {
        kotlin.srcDir("build/generated/ksp/release/kotlin")
    }
    sourceSets.test {}
}
