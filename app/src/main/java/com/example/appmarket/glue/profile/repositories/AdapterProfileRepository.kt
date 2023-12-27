package com.example.appmarket.glue.profile.repositories

import com.example.appmarket.formatters.DateTimeFormatter
import com.example.common.Container
import com.example.data.AccountsDataRepository
import com.example.profile.domain.entites.Profile
import com.example.profile.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterProfileRepository @Inject constructor(
    private val accountsDataRepository: AccountsDataRepository,
    private val dateTimeFormatter: DateTimeFormatter,
): ProfileRepository {

    override fun getProfile(): Flow<Container<Profile>> {
       return accountsDataRepository.getAccount().map {container->
           container.map{
              Profile(
                  id = it.id,
                  email = it.email,
                  username = it.username,
                  createdAtString = dateTimeFormatter.formatDateTime(it.createdAtMillis)
              )
           }
       }
    }

    override fun reload() {
        accountsDataRepository.reload()
    }

    override suspend fun editUsername(newUsername: String) {
        accountsDataRepository.setAccountUsername(newUsername)
    }
}