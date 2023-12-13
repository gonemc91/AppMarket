package com.example.appmarket.glue.navigation.di

import com.example.appmarket.glue.navigation.DefaultDestinationProvider
import com.example.navigation.DestinationsProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StartDestinationProviderModule {

    @Binds
    fun bindStartDestinationProvider(
        startDestinationProvider: DefaultDestinationProvider
    ): DestinationsProvider

}