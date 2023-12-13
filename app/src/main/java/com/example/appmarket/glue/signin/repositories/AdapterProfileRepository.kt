package com.example.appmarket.glue.signin.repositories

import com.example.common.AuthException
import com.example.common.unwrapFirst
import com.example.data.AccountsDataRepository
import com.example.sign_in.domain.repositories.ProfileRepository
import javax.inject.Inject

class AdapterProfileRepository @Inject constructor(
    private val accountDataRepository: AccountsDataRepository,
): ProfileRepository {

    override suspend fun canLoadProfile(): Boolean {
        return try {
            accountDataRepository.getAccount().unwrapFirst()
            return true
        }catch (ignored: AuthException){
            false
        }
    }
}