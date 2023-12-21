package com.example.orders.domain.entities

data class Order(
    val uuid: String,
    val orderStatus: OrderStatus,
    val orderDeliverInfo: String,
    val cratedAt: String,
    val orderItem: List<OrderItem>,
)
