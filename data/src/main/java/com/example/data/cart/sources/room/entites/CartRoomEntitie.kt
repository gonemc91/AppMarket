package com.example.data.cart.sources.room.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Database (Room) entity for storing product in the memory
 */

@Entity(
    tableName = "cart",
    indices = [
        Index("productId", unique = true)
    ]

)
data class CartRoomEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "productId") val productId : Long,
    @ColumnInfo(name = "quantity") val quantity: Int,
)