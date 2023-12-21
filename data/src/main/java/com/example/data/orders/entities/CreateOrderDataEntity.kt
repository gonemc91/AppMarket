package com.example.data.orders.entities

data class CreateOrderDataEntity(
    val items: List<CreateOrderItemDataEntity>,
    val recipientDataEntity: RecipientDataEntity,
)
