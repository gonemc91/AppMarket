package com.example.navigation

import com.example.common.AppRestarter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainAppRestarter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : AppRestarter{

    override fun restartApp() {
        globalNavComponentRouter.restart()
    }
}