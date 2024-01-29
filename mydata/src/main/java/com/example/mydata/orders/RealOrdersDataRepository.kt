package com.example.mydata.orders

import com.example.common.Container
import com.example.common.flow.LazyFlowSubjectFactory
import com.example.mydata.orders.sources.OrdersDataSource
import com.example.mydata.OrdersDataRepository
import com.example.mydata.orders.entities.CreateOrderDataEntity
import com.example.mydata.orders.entities.OrderDataEntity
import com.example.mydata.orders.entities.OrderStatusDataValue
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