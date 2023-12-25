package com.example.cart.domain

import com.example.cart.domain.enttites.CartItem
import com.example.cart.domain.exceptions.QuantityOutOfRangeException
import com.example.cart.domain.repositories.ProductRepository
import javax.inject.Inject

class ValidateCartItemQuantityUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {

    /**
     * Validate a new quantity for the specified [cartItem]
     * @throws NotFoundException
     * @throws QuantityOutOfRangeException
     */

    suspend fun validateNewQuantity(cartItem: CartItem, newQuantity: Int){
        if (newQuantity > getMaxQuantity(cartItem)) throw QuantityOutOfRangeException()
        if (newQuantity < 1) throw QuantityOutOfRangeException()
    }

    /**
     * Get max available quantity fot the specified [cartItem]
     * @throws NotFoundException
     */

    suspend fun getMaxQuantity(cartItem: CartItem): Int{
        return productRepository.getAvailableQuantity(cartItem.product.id)
    }


}