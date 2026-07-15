plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose) // The modern Kotlin 2.0+ Compose Compiler plugin
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.perru.markethub"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.perru.markethub"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true // Still required by AGP to enable Compose tool support in Android Studio
    }
}

dependencies {
    // Jetpack Compose BOM & Core
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // Material Design
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)

    // Core AndroidX Lifecycle & Core KTX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Jetpack Navigation
    // Cleaned up: Removed the duplicate runtime string and kept your version catalog & Compose Navigation
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)

    // Firebase
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)

    // Kotlin Standard Library
    implementation(libs.kotlin.stdlib)

    // Unit Testing
    testImplementation(libs.junit)

    // UI/Integration Testing
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // Debugging Tools
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}