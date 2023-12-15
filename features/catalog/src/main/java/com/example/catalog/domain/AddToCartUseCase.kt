package com.example.catalog.domain

import com.example.catalog.domain.entites.Product
import com.example.catalog.domain.repositories.CartRepository
import com.example.common.Container
import com.example.common.Core
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
) {

    /**
     * Add a new product to the cart.
     */

    suspend fun addToCart(product: Product) = withTimeout(Core.localTimeoutMillis){
        val productInCart = cartRepository.getProduceIdentifiersInCart()
            .filterIsInstance<Container.Success<Set<Long>>>()
            .first()
        if (!productInCart.value.contains(product.id)){
            cartRepository.addToCart(product.id)
        }
    }
}