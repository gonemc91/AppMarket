package com.example.appmarket.glue.orders.mappers

import com.example.appmarket.formatters.DateTimeFormatter
import com.example.appmarket.formatters.PriceFormatter
import com.example.appmarket.glue.orders.entities.OrderUsdPrice
import com.example.data.orders.entities.OrderDataEntity
import com.example.data.orders.entities.RecipientDataEntity
import com.example.orders.domain.entities.Order
import com.example.orders.domain.entities.OrderItem
import javax.inject.Inject

class OrderMapper @Inject constructor(
    private val orderStatusMapper: OrderStatusMapper,
    private val priceFormatter: PriceFormatter,
    private val dataTimeFormatter: DateTimeFormatter,
) {
    fun toOrder(orderDataEntity: OrderDataEntity): Order{
        return Order(
            uuid = orderDataEntity.uuid,
            orderStatus = orderStatusMapper.toOrderStatus(orderDataEntity.status),
            cratedAt = dataTimeFormatter.formatDateTime(orderDataEntity.createdAtMillis),
            orderDeliverInfo = orderDataEntity.recipient.toDeliveryInfo(),
            orderItem = orderDataEntity.items.map {
                OrderItem(
                    id = it.id,
                    name = it.productName,
                    quantity = it.quantity,
                    price = OrderUsdPrice(it.priceUsdCents, priceFormatter)
                )
            }
        )

    }
    private fun RecipientDataEntity.toDeliveryInfo(): String{
        return "$firstName $lastName, $address"
    }



}