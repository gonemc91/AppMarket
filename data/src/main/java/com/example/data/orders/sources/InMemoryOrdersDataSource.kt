package com.example.data.orders.sources

import com.example.common.NotFoundException
import com.example.data.orders.entities.CreateOrderDataEntity
import com.example.data.orders.entities.OrderDataEntity
import com.example.data.orders.entities.OrderItemDataEntity
import com.example.data.orders.entities.OrderStatusDataValue
import com.example.data.orders.entities.RecipientDataEntity
import com.example.data.orders.sources.room.OrdersDao
import com.example.data.orders.sources.room.OrdersItemsDao
import com.example.data.orders.sources.room.converter.ConverterOrderStatus
import com.example.data.orders.sources.room.entity.OrderStatusTuple
import com.example.data.product.sources.ProductsDataSource
import com.github.javafaker.Faker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random
import java.util.UUID
import javax.inject.Inject

class InMemoryOrdersDataSource @Inject constructor(
    coroutineScope: CoroutineScope,
    private val productsDataSource: ProductsDataSource,
    private val ordersDao: OrdersDao,
    private val ordersItemsDao: OrdersItemsDao,
) : OrdersDataSource {


    private val orders = emptyList<OrderDataEntity>().toMutableList()

    init {
        coroutineScope.launch {
            val hasOrdersTable = ordersDao.hasTable()
            if (!hasOrdersTable) {
                val generateRandomOrders = createOrders()
                generateRandomOrders.map { orderDataEntity ->
                    orders.add(orderDataEntity)
                    ordersDao.createProduct(orderDataEntity.toOrdersRoomDataEntity())
                    orderDataEntity.items.map { productItem ->
                        ordersItemsDao.insert(
                            productItem.toOrdersItemsRoomEntity().copy(uuid = orderDataEntity.uuid)
                        )
                    }
                }
            } else {
                val getOrdersDataFromDB = ordersDao.getAllData()
                getOrdersDataFromDB.map {
                    orders.add(it.toOrdersRoomDataEntity())
                }
            }
        }
    }



    override suspend fun getOrders(): List<OrderDataEntity> {
        return orders.sortedByDescending {it.createdAtMillis}
    }

    override suspend fun setStatus(uuid: String, newStatus: OrderStatusDataValue) {
        val index = orders.indexOfFirst { it.uuid == uuid }
        if (index == -1) throw  NotFoundException()
        delay(1000)
        val updatedOrder = orders[index].copy(
            status = newStatus
        )
        orders[index] = updatedOrder
        ordersDao.updateOrderStatus(OrderStatusTuple(
            uuid = uuid,
            status = ConverterOrderStatus().fromEnumOrderStatus(newStatus)))
    }

    override suspend fun createdOrder(createOrderDataEntity: CreateOrderDataEntity) {
        delay(1000)
        val newOrder = OrderDataEntity(
            uuid = UUID.randomUUID().toString().substring(0,18),
            recipient = createOrderDataEntity.recipientDataEntity,
            status = OrderStatusDataValue.CREATED,
            createdAtMillis = System.currentTimeMillis(),
            items = createOrderDataEntity.items.map {
                OrderItemDataEntity(
                    id = UUID.randomUUID().toString().substring(0,18),
                    productName = productsDataSource.getProductById(it.productId).name,
                    quantity = it.quantity,
                    priceUsdCents = it.priceUsdCents,
                )
            }
        )
        orders.add(newOrder)
        ordersDao.createProduct(newOrder.toOrdersRoomDataEntity())
        newOrder.items.map { ordersItemsDao.insert(it.toOrdersItemsRoomEntity().copy(uuid = newOrder.uuid))}
    }



    companion object GenerateRandomOrders{

        /**
         * For presentation App.
         * Generates random orders and write in data base with table name "orders"
         * The database is created once if it does not exist.
         */

        private val faker = Faker.instance(Random(42))
        private val random = Random(42)
        private fun createOrders(): MutableList<OrderDataEntity>{
            return MutableList(7){
                OrderDataEntity(
                    uuid = UUID.randomUUID().toString().substring(0,18),
                    recipient = RecipientDataEntity(
                        firstName = faker.name().firstName(),
                        lastName = faker.name().lastName(),
                        address = faker.address().fullAddress()
                    ),
                    status = OrderStatusDataValue.values()[it % OrderStatusDataValue.values().size],
                    createdAtMillis = System.currentTimeMillis() - 10000 * 3600 * 6 * (random.nextInt(10) + 10),
                    items = List(random.nextInt(5) +1 ){
                        OrderItemDataEntity(
                            id = UUID.randomUUID().toString(),
                            productName = faker.food().dish(),
                            quantity = random.nextInt(6) + 1,
                            priceUsdCents = 1000 + random.nextInt(20000)
                        )
                    }
                )
            }
        }

    }

}