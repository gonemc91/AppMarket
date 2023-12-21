package com.example.data.orders.di

import com.example.data.orders.sources.InMemoryOrdersDataSource
import com.example.data.orders.sources.OrdersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface OrdersSourcesModule {

    @Binds
    @Singleton
    fun bindOrdersDataSource(
        ordersDataSource: InMemoryOrdersDataSource
    ): OrdersDataSource
}