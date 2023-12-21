package com.example.appmarket.glue.catalog.di

import com.example.appmarket.glue.catalog.AdapterCatalogFilterRouter
import com.example.appmarket.glue.catalog.AdapterCatalogRouter
import com.example.catalog.presentation.CatalogFilterRouter
import com.example.catalog.presentation.CatalogRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RouterModule {


    @Binds
    fun bindCatalogRouter(
        catalogRouter: AdapterCatalogRouter
    ): CatalogRouter


    @Binds
    fun bindCatalogFilterRouter(
        router: AdapterCatalogFilterRouter
    ): CatalogFilterRouter

}