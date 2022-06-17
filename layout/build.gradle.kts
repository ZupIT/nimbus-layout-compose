plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("shot")
}

val serializationVersion = "1.3.2"
val ktorVersion = "2.0.0"
val applicationId by extra("br.com.zup.nimbus.android.layout.test")

dependencies {
    implementation("br.com.zup.nimbus:nimbus-compose:1.0.0-alpha10")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.navigation:navigation-compose:2.4.2")
    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.ui:ui-tooling-preview:${rootProject.extra["compose_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")

    implementation("com.google.accompanist:accompanist-flowlayout:${rootProject.extra["accompanist_version"]}")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:${rootProject.extra["accompanist_version"]}")
    implementation ("com.google.accompanist:accompanist-insets:${rootProject.extra["accompanist_version"]}")
    // If using insets-ui
    implementation ("com.google.accompanist:accompanist-insets-ui:${rootProject.extra["accompanist_version"]}")

    //Scrollview
    implementation("androidx.compose.material3:material3:1.0.0-alpha13")
    implementation("androidx.compose.ui:ui-util:${rootProject.extra["compose_version"]}")

    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation ("androidx.compose.ui:ui-test:${rootProject.extra["compose_version"]}")

    androidTestImplementation("io.ktor:ktor-client-mock:$ktorVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
    debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${rootProject.extra["compose_version"]}")
}

android {
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
        vectorDrawables {
            useSupportLibrary = true
        }
        testApplicationId = applicationId
        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
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

shot {
    tolerance =  0.5 // 0,5% tolerance
}

apply("$rootDir/maven-publish.gradle")
