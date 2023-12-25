package com.example.cart.domain.enttites

data class Product(
    val id: Long,
    val name: String,
    val shortDetails: String,
    val photo: String,
    val totalQuantity: Int,
)
