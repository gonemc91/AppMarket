package com.example.data.orders.sources.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.orders.sources.room.entity.OrderDataTuple
import com.example.data.orders.sources.room.entity.OrderStatusTuple
import com.example.data.orders.sources.room.entity.OrdersRoomEntity

@Dao
interface OrdersDao {


    @Query("SELECT * FROM orders")
    suspend fun getAllData(): List<OrderDataTuple>


    @Query("SELECT EXISTS(SELECT 1 FROM orders)")
    suspend fun hasTable(): Boolean

    @Insert(entity = OrdersRoomEntity::class)
    suspend fun createProduct(orders: OrdersRoomEntity)


    @Update(entity = OrdersRoomEntity::class)
    suspend fun updateOrderStatus(orderStatusTuple: OrderStatusTuple)



}