object Dependencies {

    const val hiltVersion = "2.49"
    const val navVersion = "2.7.6"
    const val lifecycle = "2.7.0"
    const val room_version = "2.6.1"

    object Android {

        const val coreKtx = "androidx.core:core-ktx:1.12.0"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.6.2"
        const val activityKtx = "androidx.activity:activity-ktx:1.8.2"
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
        const val navigationFragments = "androidx.navigation:navigation-fragment-ktx:$navVersion"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:$navVersion"


        const val room_compiler = "androidx.room:room-compiler:$room_version"
        const val room_ktx = "androidx.room:room-ktx:$room_version"
        const val room_runtime = "androidx.room:room-runtime:$room_version"


    }

    object Google{
        const val material = "com.google.android.material:material:1.7.0"

        const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"

        const val splashScreen = "androidx.core:core-splashscreen:1.0.0"
    }
    object JetBrains{
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2"
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