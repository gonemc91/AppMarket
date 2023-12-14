package com.example.appmarket.glue.signup

import com.example.navigation.GlobalNavComponentRouter
import com.example.sign_up.presentation.SignUpRouter
import javax.inject.Inject

class AdapterSignUpRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
): SignUpRouter {
    override fun goBack() {
        globalNavComponentRouter.pop()
    }
}