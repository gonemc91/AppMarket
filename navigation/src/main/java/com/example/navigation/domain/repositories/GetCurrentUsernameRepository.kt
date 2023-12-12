package com.example.navigation.domain.repositories

import com.example.common.Container
import kotlinx.coroutines.flow.Flow

interface  GetCurrentUsernameRepository {

    /**
     * Listen for the username of the current logged-in user.
     * @return infinite flow, always success; errors are delivered to [Container]
     */

    fun getCurrentUsername(): Flow<Container<String>>


    /**
     * Reload username flow returned by [getCurrentUsername]
     */

    fun reload()

}