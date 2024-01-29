package com.example.data.accounts.sources.room.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.accounts.entities.SignUpDataEntity


/**
 * Database (Room) entity for storing account in the memory
 */

@Entity(
    tableName = "accounts",
    indices = [
        Index("email", unique = true)
    ]

)

data class AccountRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val email: String,
    val username: String,
    val password: String,
    val token: String?,
    val createdAtMillis: Long,
) {


    companion object{
        fun fromSignUpData(signUpDataEntity: SignUpDataEntity) = AccountRoomEntity(
            id = 0, //SQLite generates identifier automatically if ID = 0,
            email = signUpDataEntity.email,
            username = signUpDataEntity.username,
            password = signUpDataEntity.password,
            token = null,
            createdAtMillis = System.currentTimeMillis(),
        )
    }
}



