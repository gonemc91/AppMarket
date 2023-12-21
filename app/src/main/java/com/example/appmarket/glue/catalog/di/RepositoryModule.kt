package com.example.appmarket.glue.catalog.di

import com.example.appmarket.glue.catalog.repositories.AdapterCartRepository
import com.example.appmarket.glue.catalog.repositories.AdapterProductRepository
import com.example.catalog.domain.repositories.CartRepository
import com.example.catalog.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)

interface RepositoryModule {

    @Binds
    fun provideProductRepository(
        repository: AdapterProductRepository
    ): ProductsRepository

    @Binds
    fun provideCartRepository(
        repository: AdapterCartRepository
    ): CartRepository

}