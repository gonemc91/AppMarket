plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.appmarket"
    compileSdk = VersionSDK.compileSdk

    defaultConfig {
        applicationId = "com.example.appmarket"
        minSdk = VersionSDK.minSdk
        targetSdk = VersionSDK.targetSdk
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Stuff.faker)
    implementation(Dependencies.Google.hilt)
    implementation(Dependencies.Stuff.javaxInject)
    kapt(Dependencies.Google.hiltCompiler)

    project(Modules.Core.common)
}

kapt {
    correctErrorTypes = true
}