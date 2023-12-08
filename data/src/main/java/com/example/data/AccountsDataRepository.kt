package com.example.data

import com.example.common.Container
import com.example.data.accounts.entities.AccountDataEntity
import com.example.data.accounts.entities.SignUpDataEntity
import kotlinx.coroutines.flow.Flow

interface AccountsDataRepository {


    /**
     * Listen for the current account.
     * @return infinite flow, always success; errors are delivered to [Container]
     */

    fun getAccount(): Flow<Container<AccountDataEntity>>

    /**
     * Update the username of the current logger-in
     */
    suspend fun setAccountUsername(username: String)

    /**
     * Exchange email-password to auth token.
     */
    suspend fun signIn(email: String, password: String): String

    /**
     * Create a new account.
     * @throws AccountAlreadyExistsDataException
     */

    suspend fun signUp(singUpDat: SignUpDataEntity)

    /**
     * Reload the flow returned by [getAccount]
     */

    fun reload()


}