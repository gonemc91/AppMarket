package com.example.mydata.product.sources

interface DiscountDataSource {

    suspend fun getDiscountPercentage(productId: Long): Int?
}