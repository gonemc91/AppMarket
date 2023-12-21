package com.example.orders.presentation.orders.entites

import com.example.orders.domain.entities.Order
import com.example.orders.domain.entities.OrderItem
import com.example.orders.domain.entities.OrderStatus

data class UiOrder(
    val origin: Order,
    val canCancel: Boolean,
    val cancelInProgress: Boolean
){

    val uuid: String get() = origin.uuid
    val createdAt: String get() = origin.cratedAt
    val recipient: String get() = origin.orderDeliverInfo
    val orderItems: List<OrderItem> get() = origin.orderItem
    val status: OrderStatus get() = origin.orderStatus


}
