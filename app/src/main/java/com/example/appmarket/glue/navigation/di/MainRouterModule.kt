package com.example.appmarket.glue.navigation.di

import com.example.appmarket.glue.navigation.DefaultMainRouter
import com.example.navigation.presentation.MainRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface MainRouterModule {

    @Binds
    fun bindMainRouter(router: DefaultMainRouter): MainRouter
}