package com.example.appmarket.glue.orders.di

import com.example.appmarket.glue.orders.factories.DefaultOrderPriceFactory
import com.example.orders.domain.factories.PriceFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface OrderFactoriesModule {

    @Binds
    fun bindOrderPriceFactory(
        priceFactory: DefaultOrderPriceFactory
    ): PriceFactory
}