package com.example.orders.domain

import com.example.orders.domain.entities.Cart
import com.example.orders.domain.repositories.CartRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    /**
     * Get the current user's cart.
     */

    suspend fun getCart(): Cart {
        return cartRepository.getCart()
    }

}