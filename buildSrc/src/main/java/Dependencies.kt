object Dependencies {

    const val hiltVersion = "2.49"
    const val navVersion = "2.4.0"

    object Android {

        const val coreKtx = "androidx.core:core-ktx:1.9.0"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.5.5"
        const val activityKtx = "androidx.activity:activity-ktx:1.6.1"
        const val appCompat = "androidx.appcompat:appcompat:1.6.0"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
        const val navigationFragments = "androidx.navigation:navigation-fragment-ktx:$navVersion"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:$navVersion"
    }

    object Google{
        const val material = "com.google.android.material:material:1.7.0"
        const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
    }
    object JetBrains{
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    }
    object Testing{
        const val jUnit = "junit:junit:4.13.2"
    }

    object Stuff{
        const val ElementAdapter = "com.elveum:element-adapter:0.6"
        const val coil = "io.coil-kt:coil:2.2.0"
        const val faker = "com.github.javafaker:javafaker:1.0.2"
        const val javaxInject = "javax.inject:javax.inject:1"
    }



}