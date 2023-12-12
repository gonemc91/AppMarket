package com.example.sign_in

import com.example.sign_in.domain.repositories.AuthTokenRepository
import com.example.sign_in.domain.repositories.ProfileRepository
import javax.inject.Inject

class IsSignedInUsesCase @Inject constructor(
    private val authTokenRepository: AuthTokenRepository,
    private val profileRepository: ProfileRepository,
) {
    /**
     * Whether the user is signed-in or not.
     */

    suspend fun isSignedIn(): Boolean{
        return authTokenRepository.getToken() != null
                && profileRepository.canLoadProfile()
    }

}