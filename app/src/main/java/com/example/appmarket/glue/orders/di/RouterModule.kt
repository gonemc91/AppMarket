package com.example.appmarket.glue.orders.di

import com.example.appmarket.glue.orders.AdapterOrdersRouter
import com.example.orders.presentation.OrdersRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouterModule {

    @Binds
    fun bindRouter(orderRouter: AdapterOrdersRouter): OrdersRouter

}