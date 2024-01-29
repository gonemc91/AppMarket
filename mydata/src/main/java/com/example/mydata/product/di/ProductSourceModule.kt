package com.example.mydata.product.di

import com.example.mydata.product.sources.DiscountDataSource
import com.example.mydata.product.sources.InMemoryDiscountDataSource
import com.example.mydata.product.sources.InMemoryProductDataSource
import com.example.mydata.product.sources.ProductsDataSource
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
    fun bindDiscountSource(
       discountDataSource: InMemoryDiscountDataSource
    ): DiscountDataSource

}