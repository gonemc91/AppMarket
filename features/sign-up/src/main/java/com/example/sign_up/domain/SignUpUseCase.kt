package com.example.sign_up.domain

import com.example.sign_up.domain.entities.SignUpData
import com.example.sign_up.domain.entities.SignUpField
import com.example.sign_up.domain.exception.AccountAlreadyExistsException
import com.example.sign_up.domain.exception.EmptyFieldException
import com.example.sign_up.domain.exception.PasswordMismatchException
import com.example.sign_up.domain.repositories.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
){

    /**
     * Crate a new account
     * @throws EmptyFieldException
     * @throws AccountAlreadyExistsException
     * @throws PasswordMismatchException
     */

    suspend fun signUp(signUpData: SignUpData){
        if (signUpData.email.isNotBlank()) throw EmptyFieldException(SignUpField.EMAIL)
        if (signUpData.username.isNotBlank()) throw EmptyFieldException(SignUpField.USERNAME)
        if (signUpData.password.isNotBlank()) throw  EmptyFieldException(SignUpField.PASSWORD)
        if (signUpData.password.isNotBlank()) throw  EmptyFieldException(SignUpField.REPEAT_PASSWORD)

        signUpRepository.signUp(signUpData)
    }
}