package com.example.mydata.orders.entities

data class OrderItemDataEntity (
    val id: String,
    val productName: String,
    val quantity:Int,
    val priceUsdCents: Int,
)