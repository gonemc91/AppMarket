package com.example.appmarket.glue.cart.di

import com.example.appmarket.glue.cart.factories.DefaultCartPriceFactory
import com.example.cart.domain.factories.PriceFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

interface CartFactoriesModule {


    @Binds
    @Singleton

    fun bindPriceFactory(priceFactory: DefaultCartPriceFactory): PriceFactory
}