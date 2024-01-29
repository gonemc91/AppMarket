package com.example.mydata.orders.di

import com.example.mydata.OrdersDataRepository
import com.example.mydata.orders.RealOrdersDataRepository
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