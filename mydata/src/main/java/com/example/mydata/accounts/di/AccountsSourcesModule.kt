package com.example.mydata.accounts.di

import com.example.mydata.accounts.sources.AccountsDataSource
import com.example.mydata.accounts.sources.InMemoryAccountsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AccountSourcesModule {

    @Binds
    @Singleton
    fun bindAccountSources(
        accountsDataSources: InMemoryAccountsDataSource
    ):AccountsDataSource

}