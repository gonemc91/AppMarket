package com.example.data.product.di

import com.example.data.ProductsDataRepository
import com.example.data.product.RealProductDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProductRepositoryModule {

    @Binds
    @Singleton
    fun bindProductRepository(
        productDataRepository: RealProductDataRepository
    ): ProductsDataRepository
}