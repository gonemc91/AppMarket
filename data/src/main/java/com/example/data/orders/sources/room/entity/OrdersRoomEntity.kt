package com.example.data.orders.sources.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Database (Room) entity for storing orders in the memory
 */

@Entity(
    tableName = "orders"
)


data class OrdersRoomEntity (
    @ColumnInfo(name = "uuid") @PrimaryKey val uuid: String,
    @Embedded val recipientData: RecipientDataTuple,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "createdAtMillis")val createdAtMillis: Long,
)

