

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.data"
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
}

 dependencies {
     implementation(Dependencies.Android.coreKtx)
     implementation(Dependencies.Stuff.faker)

     implementation(Dependencies.Android.room_runtime)
     implementation(Dependencies.Android.room_ktx)
     kapt(Dependencies.Android.room_compiler)

     implementation(Dependencies.Google.hilt)
     kapt(Dependencies.Google.hiltCompiler)


    implementation(project(Modules.Core.common))
}


kapt {
    correctErrorTypes = true
}
hilt {
    enableAggregatingTask = true
}

