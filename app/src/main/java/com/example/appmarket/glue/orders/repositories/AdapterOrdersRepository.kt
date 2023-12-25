package com.example.appmarket.glue.orders.repositories

import com.example.appmarket.glue.orders.entities.OrderUsdPrice
import com.example.appmarket.glue.orders.mappers.OrderMapper
import com.example.appmarket.glue.orders.mappers.OrderStatusMapper
import com.example.appmarket.glue.orders.mappers.RecipientMapper
import com.example.common.Container
import com.example.data.orders.RealOrdersDataRepository
import com.example.data.orders.entities.CreateOrderDataEntity
import com.example.data.orders.entities.CreateOrderItemDataEntity
import com.example.orders.domain.entities.Cart
import com.example.orders.domain.entities.Order
import com.example.orders.domain.entities.OrderStatus
import com.example.orders.domain.entities.Recipient
import com.example.orders.domain.repositories.OrdersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterOrdersRepository @Inject constructor(
    private val ordersDataRepository: RealOrdersDataRepository,
    private val orderStatusMapper: OrderStatusMapper,
    private val orderMapper: OrderMapper,
    private val recipientMapper: RecipientMapper
) : OrdersRepository {

    override suspend fun makeOrder(cart: Cart, recipient: Recipient) {
        ordersDataRepository.createOrder(CreateOrderDataEntity(
            recipientDataEntity = recipientMapper.toRecipientDataEntity(recipient),
            items = cart.cartItems.map {
                CreateOrderItemDataEntity(
                    productId = it.productId,
                    quantity = it.quantity,
                    priceUsdCents = (it.price as OrderUsdPrice).usdCents)
            }
        ))
    }

    override suspend fun changeStatus(orderUuid: String, status: OrderStatus) {
        ordersDataRepository.changeStatus(orderUuid,orderStatusMapper.toOrderStatusDataValue(status) )
    }

    override fun getOrders(): Flow<Container<List<Order>>> {
        return ordersDataRepository.getOrders().map { container->
            container.map{list->
                list.map { orderDataEntity ->
                    orderMapper.toOrder(orderDataEntity)
                }
            }
        }
    }

    override fun reload() {
        ordersDataRepository.reload()
    }
}