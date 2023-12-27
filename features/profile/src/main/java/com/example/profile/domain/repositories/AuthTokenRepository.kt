package com.example.profile.domain.repositories

interface AuthTokenRepository {

    /**
     * Clear auth token saved in app.
     */

    suspend fun clearToken()



}