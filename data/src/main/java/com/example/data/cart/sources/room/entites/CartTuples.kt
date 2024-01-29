package com.example.data.cart.sources.room.entites

import androidx.room.ColumnInfo
import com.example.data.cart.entities.CartItemDataEntity

data class CartDataTuples(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "productId") val productId : Long,
    @ColumnInfo(name = "quantity") val quantity: Int,
){
    fun toCartItemDataEntity() =
        CartItemDataEntity(
            id = id,
            productId = productId,
            quantity = quantity
        )
    }



data class CartItemIdTuples(
    @ColumnInfo(name = "id") val id: Long,
)