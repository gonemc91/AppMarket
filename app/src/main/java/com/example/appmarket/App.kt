package com.example.appmarket

import android.app.Application
import com.example.common.Core
import com.example.common.CoreProvider
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class App: Application() {


    @Inject
    lateinit var coreProvider: CoreProvider



    override fun onCreate() {
        super.onCreate()
        Core.init(coreProvider)
    }
}