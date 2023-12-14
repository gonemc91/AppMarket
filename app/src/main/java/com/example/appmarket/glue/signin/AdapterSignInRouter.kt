package com.example.appmarket.glue.signin

import com.example.appmarket.R
import com.example.navigation.GlobalNavComponentRouter
import com.example.sign_in.presentation.SignInRouter
import com.example.sign_up.presentation.signup.SignUpFragment
import javax.inject.Inject

class AdapterSignInRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : SignInRouter{

    override fun launchSignUp(email: String) {
        val screen = SignUpFragment.Screen(email)
        globalNavComponentRouter.launch(R.id.signUpFragment, screen)
    }

    override fun launchMain() {
        globalNavComponentRouter.startTabs()
    }
}