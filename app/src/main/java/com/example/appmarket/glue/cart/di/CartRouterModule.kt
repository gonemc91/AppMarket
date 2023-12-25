package com.example.appmarket.glue.cart.di

import com.example.appmarket.glue.cart.AdapterCartRouter
import com.example.cart.presentation.CartRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)


 interface CartRouterModule {

     @Binds
     fun bindCartRouter(cartRouter: AdapterCartRouter): CartRouter
}