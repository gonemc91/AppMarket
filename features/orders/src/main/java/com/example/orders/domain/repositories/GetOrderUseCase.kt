package com.example.orders.domain.repositories

import com.example.common.Container
import com.example.orders.domain.entities.Order
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val ordersRepository: OrdersRepository,
) {
    /**
     * Listen for all user's orders.
     * @return infinite flow, always success; errors are delivered to [Container]
     */

    fun getOrders(): Flow<Container<List<Order>>> {
        return ordersRepository.getOrders()
    }

    /**
     * Reload the flow returned by [getOrders]
     */

    fun reload(){
        ordersRepository.reload()
    }


}