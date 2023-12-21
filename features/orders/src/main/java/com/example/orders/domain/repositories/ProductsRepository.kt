package com.example.orders.domain.repositories

interface ProductsRepository {


    /**
     * Change quantity of the specified products.
     */

    suspend fun changeQuantity(productId: Long, byValue: Int)
}