package com.example.sign_in.domain.repositories

interface AuthServiceRepository {
    /**
     * Exchange the email-password pair to auth token.
     */

    suspend fun signIn(email: String, password: String): String

}