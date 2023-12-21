package com.example.data.orders

import com.example.common.Container
import com.example.common.flow.LazyFlowSubjectFactory
import com.example.data.OrdersDataRepository
import com.example.data.orders.entities.CreateOrderDataEntity
import com.example.data.orders.entities.OrderDataEntity
import com.example.data.orders.entities.OrderStatusDataValue
import com.example.data.orders.sources.OrdersDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RealOrdersDataRepository @Inject constructor(
    private val ordersDataSource: OrdersDataSource,
    lazyFlowSubjectFactory: LazyFlowSubjectFactory,
): OrdersDataRepository {

    private val orderSubject = lazyFlowSubjectFactory.create {
        delay(1000)
        ordersDataSource.getOrders()
    }


    override fun getOrders(): Flow<Container<List<OrderDataEntity>>> {
        return orderSubject.listen()
    }

    override fun reload() {
        orderSubject.newAsyncLoad()
    }

    override suspend fun changeStatus(orderUuid: String, status: OrderStatusDataValue) {
        ordersDataSource.setStatus(orderUuid, status)
        orderSubject.newAsyncLoad(silently = true)
    }

    override suspend fun createOrder(data: CreateOrderDataEntity) {
        ordersDataSource.createdOrder(data)
        orderSubject.newAsyncLoad(silently = true)
    }
}