package com.example.data.accounts.sources.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.data.accounts.sources.room.entites.AccountRoomEntity
import com.example.data.accounts.sources.room.entites.AccountTokenTuple
import com.example.data.accounts.sources.room.entites.AccountUpdateUsernameTuple
import com.example.data.accounts.sources.room.entites.AccountsDataInTuple
import com.example.data.accounts.sources.room.entites.AccountsSignInTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountsDao {

    @Query("SELECT email, password FROM accounts WHERE email = :email")//+
    suspend fun getAccountPassword(email: String): AccountsSignInTuple?


    @Query("SELECT id,email,username,createdAtMillis FROM accounts WHERE email = :email")//++
    suspend fun getAccountData(email: String): AccountsDataInTuple?

    @Insert(entity = AccountRoomEntity::class)
    suspend fun createAccount(account: AccountRoomEntity)//++

    @Query("SELECT * FROM accounts WHERE id = :accountId")//-
    fun getById(accountId: Long): Flow<AccountRoomEntity?>

    @Transaction
    @Query("SELECT * FROM accounts")//-
    fun getAllData(): Flow<List<AccountsDataInTuple>>

    @Update(entity = AccountRoomEntity::class)//+
    suspend fun updateUsername(account: AccountUpdateUsernameTuple)

    @Update(entity = AccountRoomEntity::class)//+
    suspend fun updateToken(token: AccountTokenTuple)


    @Query("SELECT id,email,username,createdAtMillis FROM accounts WHERE token = :token")//+
    suspend fun getAccountDataByToken(token: String): AccountsDataInTuple?

}