package com.example.data.settings.di

import com.example.data.settings.SettingDataSource
import com.example.data.settings.SharedPreferencesSettingsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppSettingsDataSourceModule {

    @Binds
    @Singleton

    fun bindAppSettingsDataSource(
        settingsDataSource: SharedPreferencesSettingsDataSource
    ): SettingDataSource
}