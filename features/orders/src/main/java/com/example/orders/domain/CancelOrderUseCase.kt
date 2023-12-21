package com.example.orders.domain

import com.example.orders.domain.entities.Order
import com.example.orders.domain.entities.OrderStatus
import com.example.orders.domain.exceptions.InvalidOrderStatusException
import com.example.orders.domain.repositories.OrdersRepository
import javax.inject.Inject

class CancelOrderUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository,
) {

    /**
     * Cancel the specified order.
     * @throws InvalidOrderStatuException
     */

    suspend fun cancelOrder(order: Order){
        if(!canCancel(order)){
            throw InvalidOrderStatusException()
        }
        ordersRepository.changeStatus(order.uuid, OrderStatus.CANCELLED)
    }

    /**
     * Whether the order can be cancelled or not.
     */


    fun canCancel(order: Order): Boolean {
        return order.orderStatus == OrderStatus.CREATED
                || order.orderStatus == OrderStatus.ACCEPTED
    }

}