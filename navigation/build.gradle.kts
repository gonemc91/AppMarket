plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.navigation"
    compileSdk = VersionSDK.compileSdk

    defaultConfig {
        minSdk = VersionSDK.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.activityKtx)
    implementation(Dependencies.Android.navigationFragments)
    implementation(Dependencies.Android.navigationUI)

    implementation(Dependencies.Google.material)
    implementation(Dependencies.Google.splashScreen)

    implementation(Dependencies.Google.hilt)
    kapt(Dependencies.Google.hiltCompiler)


    implementation(project(Modules.Core.presentation))
    implementation(project(Modules.Core.commonImpl))
    implementation(project(Modules.Core.theme))
}

kapt {
    correctErrorTypes = true
}