package com.example.mydata.orders.sources

import com.example.mydata.orders.entities.CreateOrderDataEntity
import com.example.mydata.orders.entities.OrderDataEntity
import com.example.mydata.orders.entities.OrderStatusDataValue

interface OrdersDataSource {

    suspend fun getOrders(): List<OrderDataEntity>

    suspend fun setStatus(uuid: String, newStatus: OrderStatusDataValue)

    suspend fun createdOrder(createOrderDataEntity: CreateOrderDataEntity)
}