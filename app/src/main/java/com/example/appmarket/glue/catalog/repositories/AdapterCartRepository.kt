package com.example.appmarket.glue.catalog.repositories

import com.example.catalog.domain.repositories.CartRepository
import com.example.common.Container
import com.example.data.CartDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterCartRepository @Inject constructor(
    private val cartRepository: CartDataRepository,
): CartRepository {

    override fun getProduceIdentifiersInCart(): Flow<Container<Set<Long>>> {
        return cartRepository.getCart().map { container ->
            container.map{list ->
                list.map { it.productId }.toSet()
            }
        }
    }

    override fun reload() {
        cartRepository.reload()
    }

    override suspend fun addToCart(productId: Long) {
        cartRepository.addToCart(productId, quantity = 1)
    }
}