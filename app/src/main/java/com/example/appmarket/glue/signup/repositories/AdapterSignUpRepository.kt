package com.example.appmarket.glue.signup.repositories

import com.example.data.AccountsDataRepository
import com.example.data.accounts.entities.SignUpDataEntity
import com.example.data.accounts.exceptions.AccountAlreadyExistsDataException
import com.example.sign_up.domain.entities.SignUpData
import com.example.sign_up.domain.exception.AccountAlreadyExistsException
import com.example.sign_up.domain.repositories.SignUpRepository
import javax.inject.Inject

class AdapterSignUpRepository @Inject constructor(
    private val accountRepository: AccountsDataRepository
): SignUpRepository {
    override suspend fun signUp(signUpData: SignUpData) {
        try {
            accountRepository.signUp(
                SignUpDataEntity(
                    email = signUpData.email,
                    username = signUpData.username,
                    password = signUpData.password,
                )
            )
        }catch (e: AccountAlreadyExistsDataException){
            throw AccountAlreadyExistsException(e)
        }
    }
}