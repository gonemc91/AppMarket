package com.example.appmarket.glue.signup.di

import com.example.appmarket.glue.signup.repositories.AdapterSignUpRepository
import com.example.sign_up.domain.repositories.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SignUpRepositoriesModule {

    @Binds
    fun bindSignUpDataSource(signUpDataSource: AdapterSignUpRepository): SignUpRepository
}