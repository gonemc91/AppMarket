package com.example.data.orders.sources.room

import androidx.room.Dao
import androidx.room.Insert
import com.example.data.orders.sources.room.entity.OrdersItemsRoomEntity

@Dao

 interface OrdersItemsDao {

    @Insert(entity = OrdersItemsRoomEntity::class)
    suspend fun insert(item: OrdersItemsRoomEntity)

}