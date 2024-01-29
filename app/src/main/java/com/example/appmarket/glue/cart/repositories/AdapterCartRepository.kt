package com.example.appmarket.glue.cart.repositories

import com.example.appmarket.glue.cart.mapper.CartItemMapper
import com.example.cart.domain.enttites.CartItem
import com.example.cart.domain.repositories.CartRepository
import com.example.common.Container
import com.example.common.Core
import com.example.data.CartDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterCartRepository @Inject constructor(
    private val cartDataRepository: CartDataRepository,
    private val cartItemMapper: CartItemMapper,
): CartRepository{

    override suspend fun changeQuantity(cartItemId: Long, newQuantity: Int) {
        cartDataRepository.changeQuantity(cartItemId,newQuantity)
    }

    override suspend fun deleteCartItems(cartItemsIds: List<Long>) {
        cartDataRepository.deleteCartItem(cartItemsIds)
    }

    override suspend fun getCartItemById(id: Long): CartItem {
        return cartItemMapper.toCartItem(cartDataRepository.getCartItemById(id))
    }

    override fun getCartItems(): Flow<Container<List<CartItem>>> {
        return cartDataRepository.getCart().map { container->
            container.suspendMap {list ->
                list.forEach{
                    Core.logger.log("Id product ${it.productId}")
                }
                list.map { cartItemMapper.toCartItem(it) }
            }
        }
    }

    override fun reload() {
        cartDataRepository.reload()
    }
}