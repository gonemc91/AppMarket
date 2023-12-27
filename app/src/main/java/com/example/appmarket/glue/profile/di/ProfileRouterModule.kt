package com.example.appmarket.glue.profile.di

import com.example.appmarket.glue.profile.AdapterProfileRouter
import com.example.profile.presentation.ProfileRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)

interface ProfileRouterModule {

    @Binds
    fun bindAdapterProfileRouter(
        adapterProfileRouter: AdapterProfileRouter
    ):ProfileRouter

}