package com.example.catalog.domain.entites

data class Product (
    val id: Long,
    val name: String,
    val shortDetails: String,
    val details: String,
    val category: String,
    val price: Price,
    val priceWithDiscount: Price?,
    val photo: String,
)