package com.example.appmarket.glue.profile.repositories

import com.example.data.settings.SettingDataSource
import com.example.profile.domain.repositories.AuthTokenRepository
import javax.inject.Inject

class AdapterAuthTokenRepository @Inject constructor(
    private val settingDataSource: SettingDataSource,
) : AuthTokenRepository {
    override suspend fun clearToken() {
        settingDataSource.setToken(null)
    }
}