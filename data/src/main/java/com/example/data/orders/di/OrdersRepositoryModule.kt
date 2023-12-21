package com.example.data.orders.di

import com.example.data.OrdersDataRepository
import com.example.data.orders.RealOrdersDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface OrdersRepositoryModule {

    @Binds
    @Singleton
    fun  bindOrdersDataRepository(
        ordersDataRepository: RealOrdersDataRepository
    ):OrdersDataRepository
}