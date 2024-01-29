package com.example.data.accounts.sources.room.entites

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.data.accounts.entities.AccountDataEntity


/**
 * Fetch only ID and password from 'accounts' table.
 */
data class AccountsSignInTuple (
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
)

/**
 * Fetch only data without password from 'accounts' table.
 */


data class AccountsDataInTuple(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "createdAtMillis") val createdAtMillis: Long,
){
    fun toAccountDataEntity(): AccountDataEntity = AccountDataEntity(
        id = id,
        email = email,
        username = username,
        createdAtMillis = createdAtMillis,
    )
}

/**
 * Tuple for updating username by account id
 */

data class AccountUpdateUsernameTuple(
    @ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "username") val username: String,
)


data class AccountTokenTuple(
    @ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "token") val token: String?,
)