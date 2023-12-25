package com.example.appmarket.glue.orders.di

import com.example.appmarket.glue.orders.repositories.AdapterCartRepository
import com.example.appmarket.glue.orders.repositories.AdapterOrdersRepository
import com.example.appmarket.glue.orders.repositories.AdapterProductRepositories
import com.example.orders.domain.repositories.CartRepository
import com.example.orders.domain.repositories.OrdersRepository
import com.example.orders.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)

interface OrderRepositoriesModule {

    @Binds
    fun bindOrdersRepository(
        ordersRepository: AdapterOrdersRepository
    ):OrdersRepository

    @Binds
    fun bindCartRepository(
        cartRepository: AdapterCartRepository
    ): CartRepository

    @Binds
    fun bindProductRepository(
        productRepository: AdapterProductRepositories
    ): ProductsRepository

}