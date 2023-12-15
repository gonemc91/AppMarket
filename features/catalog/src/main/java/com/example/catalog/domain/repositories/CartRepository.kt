package com.example.catalog.domain.repositories

import com.example.common.Container
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    /**
     * Listen for all product IDs already added to the cart.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getProduceIdentifiersInCart(): Flow<Container<Set<Long>>>

    /**
     * Reload the flow returned by [getProduceIdentifiersInCart]
     */
    fun reload()

    /**
     * Add a new product to the cart.
     */

    suspend fun addToCart(productId: Long)

}