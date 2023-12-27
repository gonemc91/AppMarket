package com.example.profile.domain

import com.example.profile.domain.exception.EmptyUsernameException
import com.example.profile.domain.repositories.ProfileRepository
import javax.inject.Inject

class EditUserNameUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
){

    suspend fun editUsername(newUsername: String){
        if (newUsername.isBlank()) throw EmptyUsernameException()
        profileRepository.editUsername(newUsername)
    }

}