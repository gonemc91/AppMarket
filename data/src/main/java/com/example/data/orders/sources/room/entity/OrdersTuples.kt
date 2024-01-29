package com.example.data.orders.sources.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.data.orders.entities.OrderDataEntity
import com.example.data.orders.entities.OrderItemDataEntity
import com.example.data.orders.entities.RecipientDataEntity
import com.example.data.orders.sources.room.converter.ConverterOrderStatus


data class OrderDataTuple(
    @ColumnInfo(name = "uuid") @PrimaryKey val uuid: String,
    @Embedded val recipientData: RecipientDataTuple,
    @Relation(entity = OrdersItemsRoomEntity::class, parentColumn = "uuid", entityColumn = "orders_uuid") val items: List<OrdersItemsRoomEntity>,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "createdAtMillis")val createdAtMillis: Long,
){


    fun toOrdersRoomDataEntity(): OrderDataEntity {
        val listItems = emptyList<OrderItemDataEntity>().toMutableList()
        items.map { listItems.add(it.toOrdersItemDataEntity()) }

        return OrderDataEntity(
            uuid = uuid,
            recipient = recipientData.toRecipientDataEntity(),
            items = listItems.toList(),
            status = ConverterOrderStatus().toEnumOrderStatus(status),
            createdAtMillis = createdAtMillis,
        )
    }

}





data class OrdersItemTuple(
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "productName") val productName: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "priceUsdCent") val priceUsdCents: Int,
) {
    fun toOrdersItemDataEntity() = OrderItemDataEntity(
        id = id,
        productName = productName,
        quantity = quantity,
        priceUsdCents = priceUsdCents
    )
}


data class RecipientDataTuple(
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "address") val address: String,
){
    fun toRecipientDataEntity() = RecipientDataEntity(
        firstName = firstName,
        lastName = lastName,
        address = address
    )
}


data class OrderStatusTuple(
    @ColumnInfo(name = "uuid") val uuid: String,
    @ColumnInfo(name = "status") val status: String,
)



