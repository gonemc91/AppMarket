package com.example.appmarket.glue.signin.repositories

import com.example.data.settings.SettingDataSource
import com.example.sign_in.domain.repositories.AuthTokenRepository
import javax.inject.Inject

class AdapterAuthTokenRepository @Inject constructor(
    private val settingDataSource: SettingDataSource,
): AuthTokenRepository {
    override suspend fun setToken(token: String?) {
        settingDataSource.setToken(token)
    }

    override suspend fun getToken(): String? {
        return settingDataSource.getToken()
    }
}