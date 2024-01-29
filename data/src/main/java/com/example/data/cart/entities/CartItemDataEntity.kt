package com.example.data.cart.entities

import com.example.data.cart.sources.room.entites.CartDataTuples

class CartItemDataEntity(
    val id: Long,
    val productId: Long,
    val quantity: Int,
) {
    fun toCartDataTuples() =
        CartDataTuples(
            id = id,
            productId = productId,
            quantity = quantity
        )

}