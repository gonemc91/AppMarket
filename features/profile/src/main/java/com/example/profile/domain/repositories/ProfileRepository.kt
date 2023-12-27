package com.example.profile.domain.repositories

import com.example.common.Container
import com.example.profile.domain.entites.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    /**
     * Listen for profile info of the current logger-in user.
     * @return infinite flow, always success; errors are delivering to [Container]
     */
    fun getProfile(): Flow<Container<Profile>>


    /**
     * Reload profile into flow returned [getProfile]
     */
    fun reload()

    /**
     * Update username of the current logged-in user.
     */

    suspend fun editUsername(newUsername : String)


}