package com.example.mydata.orders.entities

data class OrderDataEntity(
    val uuid: String,
    val recipient: RecipientDataEntity,
    val items: List<OrderItemDataEntity>,
    val status: OrderStatusDataValue,
    val createdAtMillis: Long,
)
