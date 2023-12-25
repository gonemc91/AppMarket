package com.example.cart.domain

import com.example.cart.domain.enttites.Cart
import com.example.cart.domain.enttites.CartItem
import com.example.cart.domain.factories.PriceFactory
import com.example.cart.domain.repositories.CartRepository
import com.example.common.Container
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val priceFactory: PriceFactory,
) {

    /**
     * Listen for user's Cart
     * @return infinite flow, always success; error are delivered to [Container]
     */
    fun getCart(): Flow<Container<Cart>>{
        return cartRepository.getCartItems().map {container->
            container.map {
                Cart(it, priceFactory)
            }
        }
    }

    /**
     * Get cart item by ID.
     * @throws NotFoundException
     */

    suspend fun getCartById(cartItemId: Long): CartItem{
        return cartRepository.getCartItemById(cartItemId)
    }

    /**
     * Reload Cart flow returned by [getCart].
     */

    fun reload(){
        cartRepository.reload()
    }




}