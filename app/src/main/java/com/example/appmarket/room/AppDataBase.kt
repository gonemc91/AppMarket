package com.example.appmarket.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.accounts.sources.room.AccountsDao
import com.example.data.accounts.sources.room.entites.AccountRoomEntity
import com.example.data.cart.sources.room.CartDao
import com.example.data.cart.sources.room.entites.CartRoomEntity
import com.example.data.orders.sources.room.OrdersDao
import com.example.data.orders.sources.room.OrdersItemsDao
import com.example.data.orders.sources.room.converter.ConverterOrderStatus
import com.example.data.orders.sources.room.entity.OrdersItemsRoomEntity
import com.example.data.orders.sources.room.entity.OrdersRoomEntity
import com.example.data.product.sources.room.ProductsDao
import com.example.data.product.sources.room.entity.ProductsRoomEntity


@Database(
    version = 1,
    entities = [
        AccountRoomEntity::class,
        ProductsRoomEntity::class,
        CartRoomEntity::class,
        OrdersRoomEntity::class,
        OrdersItemsRoomEntity::class,
    ]
)
@TypeConverters(ConverterOrderStatus::class)

abstract class AppDataBase : RoomDatabase() {
    abstract fun getAccountDao(): AccountsDao

    abstract fun getProductDao(): ProductsDao

    abstract fun getCartDao(): CartDao

    abstract fun getOrdersDao(): OrdersDao

    abstract fun getOrdersItemsDao(): OrdersItemsDao


}
