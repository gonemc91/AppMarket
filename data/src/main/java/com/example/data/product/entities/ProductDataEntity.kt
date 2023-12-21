package com.example.data.product.entities

data class ProductDataEntity(
    val id: Long,
    val name: String,
    val category: String,
    val shortDescription: String,
    val description: String,
    val imageUrl: String,
    val quantityAvailable: Int,
    val priceUsdCents: Int,
)