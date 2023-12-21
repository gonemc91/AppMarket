package com.example.orders.domain.entities

data class CartItem(
    val name: String,
    val quantity: Int,
    val productId: Long,
    val price: Price,
)
