package com.example.data.product.di

import com.example.data.product.sources.DiscountDataSource
import com.example.data.product.sources.InMemoryDiscountDataSource
import com.example.data.product.sources.InMemoryProductDataSource
import com.example.data.product.sources.ProductsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

interface ProductSourceModule {

    @Binds
    @Singleton
    fun bindProductSource(
        productDataSource: InMemoryProductDataSource
    ): ProductsDataSource

    @Binds
    @Singleton
    fun bindDiscountDataSource(
        discountDataSource: InMemoryDiscountDataSource
    ): DiscountDataSource



}