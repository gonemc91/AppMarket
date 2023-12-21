package com.example.data.cart.di

import com.example.data.cart.sources.CartDataSource
import com.example.data.cart.sources.InMemoryCartDataSources
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface CartSourceModule {


    @Binds
    @Singleton
    fun bindCartSource(
        inMemoryCartDataSources: InMemoryCartDataSources
    ): CartDataSource
}