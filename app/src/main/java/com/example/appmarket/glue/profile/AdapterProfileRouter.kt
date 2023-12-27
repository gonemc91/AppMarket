package com.example.appmarket.glue.profile

import com.example.appmarket.R
import com.example.navigation.GlobalNavComponentRouter
import com.example.profile.presentation.ProfileRouter
import javax.inject.Inject


class AdapterProfileRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : ProfileRouter {

    override fun launchEditUsername() {
        globalNavComponentRouter.launch(R.id.editProfileFragment)
    }

    override fun goBack() {
        globalNavComponentRouter.pop()
    }

    override fun restartApp() {
        globalNavComponentRouter.restart()
    }
}