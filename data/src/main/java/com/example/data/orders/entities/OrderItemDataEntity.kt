package com.example.data.orders.entities

import com.example.data.orders.sources.room.entity.OrdersItemsRoomEntity

data class OrderItemDataEntity (
    val id: String,
    val productName: String,
    val quantity:Int,
    val priceUsdCents: Int,
){
    fun toOrdersItemsRoomEntity() = OrdersItemsRoomEntity(
        uuid = "",
        id = id,
        productName = productName,
        quantity = quantity,
        priceUsdCents = priceUsdCents
    )
}
