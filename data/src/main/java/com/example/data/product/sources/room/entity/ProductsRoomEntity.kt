package com.example.data.product.sources.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/**
* Database (Room) entity for storing product in the memory
*/

@Entity(
    tableName = "products",
    indices = [
        Index("name", unique = true)
    ]

)

data class ProductsRoomEntity(
    @ColumnInfo(name = "productId")@PrimaryKey val productId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "shortDescription") val shortDescription: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "quantityAvailable") val quantityAvailable: Int,
    @ColumnInfo(name = "priceUsdCents") val priceUsdCents: Int,
    @ColumnInfo(name = "discountPercentage") val discountPercentage: Int,
    @ColumnInfo(name = "priceUsdCentsWithDiscount") val priceUsdCentsWithDiscount: Int,
)

