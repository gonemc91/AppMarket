package com.example.navigation.di

import com.example.common.AppRestarter
import com.example.common_ipl.ActivityRequired
import com.example.navigation.GlobalNavComponentRouter
import com.example.navigation.MainAppRestarter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)

class NavigationModule {

    @Provides
    fun providesAppRestarter(
        appRestarter: MainAppRestarter
    ): AppRestarter{
        return appRestarter
    }


    @Provides
    @IntoSet
    fun provideRouterActivityRequired(
        router: GlobalNavComponentRouter,
    ): ActivityRequired{
        return router
    }

}