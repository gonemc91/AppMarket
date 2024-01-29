package com.example.mydata.accounts.sources

import com.example.mydata.accounts.entities.AccountDataEntity
import com.example.mydata.accounts.entities.SignUpDataEntity

interface AccountsDataSource {

    suspend fun signIn(email: String, password: String): String

    suspend fun signUp(signUpData: SignUpDataEntity)

    suspend fun getAccount(): AccountDataEntity

    suspend fun setAccountUsername(username: String): AccountDataEntity

}