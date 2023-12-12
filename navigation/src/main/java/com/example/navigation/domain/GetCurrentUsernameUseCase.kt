package com.example.navigation.domain

import com.example.common.Container
import com.example.navigation.domain.repositories.GetCurrentUsernameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUsernameUseCase @Inject constructor(
    private val getCurrentUsernameRepository: GetCurrentUsernameRepository
) {

    /**
     * Listen for the username of the current logged-in user.
     * @return infinite flow, always success; errors are delivered to [Container]
     */

    fun getUsername(): Flow<Container<String>> {
        return getCurrentUsernameRepository.getCurrentUsername()
    }


}