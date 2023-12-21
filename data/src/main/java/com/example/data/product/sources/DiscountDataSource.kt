package com.example.data.product.sources

interface DiscountDataSource {

    suspend fun getDiscountPercentage(productId: Long): Int?
}