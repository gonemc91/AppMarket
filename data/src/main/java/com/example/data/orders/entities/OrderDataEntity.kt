package com.example.data.orders.entities

import com.example.data.orders.sources.room.converter.ConverterOrderStatus
import com.example.data.orders.sources.room.entity.OrdersRoomEntity

data class OrderDataEntity(
    val uuid: String,
    val recipient: RecipientDataEntity,
    val items: List<OrderItemDataEntity>,
    val status: OrderStatusDataValue,
    val createdAtMillis: Long,
){
    fun toOrdersRoomDataEntity() = OrdersRoomEntity(
        uuid = uuid,
        recipientData = recipient.toRecipientRoomEntity(),
        status = ConverterOrderStatus().fromEnumOrderStatus(status),
        createdAtMillis = createdAtMillis,
    )
}
