package com.example.appmarket.glue.navigation.repositories

import com.example.common.Container
import com.example.data.CartDataRepository
import com.example.navigation.domain.repositories.GetCartItemCountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class AdapterGetCartItemCountRepository  @Inject constructor(
    private val cartDataRepository: CartDataRepository,
): GetCartItemCountRepository {
    override fun getCartItemCount(): Flow<Container<Int>> {
        return cartDataRepository.getCart().map {container->
            container.map{list->list.size}
        }
    }
}
