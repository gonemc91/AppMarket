package com.example.navigation.domain

import com.example.common.Container
import com.example.navigation.domain.repositories.GetCartItemCountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartItemsCountUseCase @Inject constructor(
    private val getCartItemCountRepository: GetCartItemCountRepository,
) {

    /**
     * Listen for the current of items added to the cart.
     * @return infinite flow, always success; errors are delivered to [Container]
     */

    fun getCartItemCount(): Flow<Container<Int>> {
        return getCartItemCountRepository.getCartItemCount()
    }
}