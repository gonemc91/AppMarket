package com.example.data.accounts.sources

import android.util.Log
import com.example.common.AuthException
import com.example.data.accounts.entities.AccountDataEntity
import com.example.data.accounts.entities.SignUpDataEntity
import com.example.data.accounts.exceptions.AccountAlreadyExistsDataException
import com.example.data.accounts.sources.room.AccountsDao
import com.example.data.accounts.sources.room.entites.AccountRoomEntity
import com.example.data.accounts.sources.room.entites.AccountTokenTuple
import com.example.data.accounts.sources.room.entites.AccountUpdateUsernameTuple
import com.example.data.settings.SettingDataSource
import kotlinx.coroutines.delay
import java.sql.SQLException
import java.util.UUID
import javax.inject.Inject

class InMemoryAccountsDataSource  @Inject constructor(
    private val settings: SettingDataSource,
    private val accountsDao: AccountsDao,
): AccountsDataSource {

    private val record = Record()



    override suspend fun signIn(email: String, password: String): String {
        delay(1000)
        val account = accountsDao.getAccountPassword(email)
        if (account?.password != password || account.email != email)
            throw AuthException()

        val accountDataFromDB = accountsDao.getAccountData(email)
            ?: throw SQLException()
        val token = generateToken()
        accountsDao.updateToken(AccountTokenTuple(id = accountDataFromDB.id, token = token))
        record.account = accountDataFromDB.toAccountDataEntity()
        record.token = token
        Log.d("myToken","in data : $token")
        return token
    }

    override suspend fun signUp(signUpData: SignUpDataEntity) {
        delay(1000)
        val userInDb = accountsDao.getAccountPassword(signUpData.email)
        if (userInDb != null) throw AccountAlreadyExistsDataException()

        val newUser = AccountRoomEntity.fromSignUpData(signUpData)
        accountsDao.createAccount(newUser)
    }

    override suspend fun getAccount(): AccountDataEntity {

        val token = settings.getToken() ?: throw AuthException()
        val accountData = accountsDao.getAccountDataByToken(token)?.toAccountDataEntity()
            ?: throw AuthException()

        record.token = token
        record.account = accountData

        Log.d("AccountData", accountData.toString())
        return accountData
    }

    override suspend fun setAccountUsername(username: String): AccountDataEntity {
        val token = settings.getToken() ?: throw AuthException()
        if (record.token != token) throw AuthException()
        delay(1000)
        record.account = record.account!!.copy(
            username = username
        )
        val accountUpdateUsernameTuple = AccountUpdateUsernameTuple(
            id = record.account!!.id,
            username = username)
        accountsDao.updateUsername(accountUpdateUsernameTuple)

        return record.account!!
    }


    private fun generateToken(): String {
        return UUID.randomUUID().toString()
    }

    private class Record(
        var account: AccountDataEntity? = null,
        var token: String? = null,
    )


}