package com.example.appmarket.glue.signin.di

import com.example.appmarket.glue.signin.AdapterSignInRouter
import com.example.sign_in.presentation.SignInRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface SignInRouterModule {

    @Binds
    fun bindSignInRouter(router: AdapterSignInRouter): SignInRouter
}