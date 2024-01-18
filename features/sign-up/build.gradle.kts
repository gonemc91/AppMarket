

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.sign_up"
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
    implementation(Dependencies.Android.fragmentKtx)
    implementation(Dependencies.Android.lifecycleViewModelKtx)


    implementation(Dependencies.Google.hilt)
    implementation(Dependencies.Google.material)

    kapt(Dependencies.Google.hiltCompiler)



    implementation(project(Modules.Core.presentation))
    implementation(project(Modules.Core.common))
}


kapt {
    correctErrorTypes = true
}