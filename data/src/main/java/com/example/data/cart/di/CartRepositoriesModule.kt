package com.example.data.cart.di

import com.example.data.CartDataRepository
import com.example.data.cart.RealCartDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CartRepositoriesModule {

    @Binds
    @Singleton
    fun bindCartRepository(
        cartDataRepository : RealCartDataRepository
    ): CartDataRepository


}