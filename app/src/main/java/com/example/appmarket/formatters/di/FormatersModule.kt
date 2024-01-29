package com.example.appmarket.formatters.di

import com.example.appmarket.formatters.DateTimeFormatter
import com.example.appmarket.formatters.DefaultDateTimeFormater
import com.example.appmarket.formatters.DefaultPriceFormatter
import com.example.appmarket.formatters.PriceFormatter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)

interface FormatersModule {

    @Binds
    fun bindDataTimeFormatter(
        formatter: DefaultDateTimeFormater
    ): DateTimeFormatter


    @Binds
    fun bindPriceFormatter(
        formatter: DefaultPriceFormatter
    ): PriceFormatter
}