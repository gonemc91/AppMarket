package com.example.sign_up.domain.repositories

import com.example.sign_up.domain.entities.SignUpData

interface SignUpRepository {

    /**
     * @throws AccountAlreadyExistsException if a user with the provided email already exists.
     */

    suspend fun signUp(signUpData: SignUpData)
}