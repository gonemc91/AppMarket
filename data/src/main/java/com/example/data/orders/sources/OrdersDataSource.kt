package com.example.data.orders.sources

import com.example.data.orders.entities.CreateOrderDataEntity
import com.example.data.orders.entities.OrderDataEntity
import com.example.data.orders.entities.OrderStatusDataValue

interface OrdersDataSource {

    suspend fun getOrders(): List<OrderDataEntity>

    suspend fun setStatus(uuid: String, newStatus: OrderStatusDataValue)

    suspend fun createdOrder(createOrderDataEntity: CreateOrderDataEntity)
}