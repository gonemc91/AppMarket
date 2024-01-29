package com.example.appmarket.glue.signin.repositories

import android.util.Log
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
           val ac = accountDataRepository.getAccount().unwrapFirst()

            Log.d("DataAC", ac.toString())
            return true
        }catch (ignored: AuthException){
            false
        }
    }
}