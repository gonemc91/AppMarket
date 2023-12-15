package com.example.data.cart.sources

import com.example.data.cart.entities.CartItemDataEntity

interface CartDataSource {

    suspend fun clearCart()

    suspend fun getCart(): List<CartItemDataEntity>

    suspend fun saveToCart(productId: Long, quantity: Int)

    suspend fun delete(cartItemId: Long)

    suspend fun deleteAll()

}