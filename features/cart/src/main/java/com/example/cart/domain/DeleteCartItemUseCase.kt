package com.example.cart.domain

import com.example.cart.domain.enttites.CartItem
import com.example.cart.domain.repositories.CartRepository
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(
    private val cartRepository: CartRepository,
){

    /**
     * Delete the list of cart items.
     * @throws ClassNotFoundException
     */

    suspend fun deleteCartItems(cartItems: List<CartItem>){
        cartRepository.deleteCartItems(cartItems.map { it.id })
    }

}