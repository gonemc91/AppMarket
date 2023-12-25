package com.example.cart.domain.repositories

import com.example.cart.domain.enttites.CartItem
import com.example.common.Container
import kotlinx.coroutines.flow.Flow

interface CartRepository {


    /**
     * Change quantity of [cartItemId] to the [newQuantity] values.
     * @throws NotFoundException
     */

    suspend fun changeQuantity(cartItemId: Long, newQuantity: Int)


    /**
     * Delete the specified cart items.
     * @throws NotFoundException
     */
    suspend fun deleteCartItems(cartItemsIds: List<Long>)

    /**
     * Get cart item by ID.
     * @throws NotFoundException
     */
    suspend fun getCartItemById(id : Long): CartItem

    /**
     * Listen for user's Cart.
     * @return infinite flow, always success; errors are delivering to [Container]
     */

    fun getCartItems(): Flow<Container<List<CartItem>>>


    /**
     * Reload Cart flow returned by [getCartItems]
     */
    fun reload()

}