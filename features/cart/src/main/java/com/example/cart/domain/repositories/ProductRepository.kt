package com.example.cart.domain.repositories

interface ProductRepository {

    /**
     * @throws NotFounException
     */

    suspend fun getAvailableQuantity(productId: Long): Int
}