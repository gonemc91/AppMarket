package com.example.mydata.product.di

import com.example.mydata.ProductsDataRepository
import com.example.mydata.product.RealProductDataRepository
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