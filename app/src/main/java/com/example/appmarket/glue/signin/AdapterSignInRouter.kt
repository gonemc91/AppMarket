package com.example.appmarket.glue.signin

import com.example.sign_in.presentation.SignInRouter
import javax.inject.Inject

class AdapterSignInRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : SignInRouter{

    override fun launchSignUp(email: String) {
        TODO("Not yet implemented")
    }

    override fun launchMain() {
    }
}