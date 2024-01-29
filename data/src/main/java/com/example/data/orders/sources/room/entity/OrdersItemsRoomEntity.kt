package com.example.data.orders.sources.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.orders.entities.OrderItemDataEntity


/**
 * Database (Room) entity for storing orders items in the memory
 */

@Entity(
    tableName = "orders_items"
)

data class OrdersItemsRoomEntity(
    @ColumnInfo(name = "orders_uuid") val uuid: String,
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @ColumnInfo(name = "productName") val productName: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "priceUsdCent") val priceUsdCents: Int,
) {
    fun toOrdersItemDataEntity() = OrderItemDataEntity(
        id = id,
        productName = productName,
        quantity = quantity,
        priceUsdCents = priceUsdCents
    )
}