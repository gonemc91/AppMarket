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

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
}
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
    implementation(Dependencies.Android.navigationFragments)

    implementation(Dependencies.Android.room_runtime)
    implementation(Dependencies.Android.room_ktx)
    kapt(Dependencies.Android.room_compiler)


    implementation(Dependencies.Google.hilt)
    kapt(Dependencies.Google.hiltCompiler)

    implementation(project(Modules.Core.commonImpl))
    implementation(project(Modules.Core.presentation))
    implementation(project(Modules.Navigation.navigation))
    implementation(project(Modules.Data.data))

    implementation(project(Modules.Features.signIn))
    implementation(project(Modules.Features.signUp))
    implementation(project(Modules.Features.catalog))
    implementation(project(Modules.Features.orders))
    implementation(project(Modules.Features.cart))
    implementation(project(Modules.Features.profile))


}

kapt {
    correctErrorTypes = true
}
hilt {
    enableAggregatingTask = true
}