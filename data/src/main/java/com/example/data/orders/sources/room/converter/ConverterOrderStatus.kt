package com.example.data.orders.sources.room.converter

import androidx.room.TypeConverter
import com.example.data.orders.entities.OrderStatusDataValue

class ConverterOrderStatus() {

    @TypeConverter
    fun fromEnumOrderStatus(ordersStatus: OrderStatusDataValue): String {
        return ordersStatus.name
    }

    @TypeConverter
    fun toEnumOrderStatus(status: String): OrderStatusDataValue {
        return OrderStatusDataValue.valueOf(status)
    }





}
