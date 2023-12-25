package com.example.appmarket.glue.cart.di

import com.example.appmarket.glue.cart.repositories.AdapterCartRepository
import com.example.appmarket.glue.cart.repositories.AdapterProductRepositories
import com.example.cart.domain.repositories.CartRepository
import com.example.cart.domain.repositories.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)

interface CartRepositoriesModule {


    @Binds
    fun bindCartRepository(cartRepository: AdapterCartRepository): CartRepository

    @Binds
    fun bindProductRepository(productRepository: AdapterProductRepositories): ProductRepository
}