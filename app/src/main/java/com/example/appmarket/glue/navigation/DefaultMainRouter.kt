package com.example.appmarket.glue.navigation

import com.example.navigation.GlobalNavComponentRouter
import com.example.navigation.presentation.MainRouter
import javax.inject.Inject

class DefaultMainRouter @Inject constructor(
    private val navComponentRouter: GlobalNavComponentRouter
): MainRouter {

    override fun launchCart() {
        TODO()
    }
}