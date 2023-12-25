package com.example.appmarket.glue.navigation.di

import com.example.appmarket.glue.navigation.repositories.AdapterGetCartItemCountRepository
import com.example.appmarket.glue.navigation.repositories.AdapterGetCurrentUsernameRepository
import com.example.navigation.domain.repositories.GetCartItemCountRepository
import com.example.navigation.domain.repositories.GetCurrentUsernameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface MainRepositoriesModule {
    @Binds
    fun bindGetCurrentUsernameRepository(
        getCurrentUsernameRepository: AdapterGetCurrentUsernameRepository
    ): GetCurrentUsernameRepository

   @Binds
    fun bindGetCartItemCountRepository(
        getCartItemCountRepository: AdapterGetCartItemCountRepository
    ): GetCartItemCountRepository



}