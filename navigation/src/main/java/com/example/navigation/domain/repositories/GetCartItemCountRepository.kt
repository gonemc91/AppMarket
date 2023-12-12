package com.example.navigation.domain.repositories

import com.example.common.Container
import kotlinx.coroutines.flow.Flow

interface GetCartItemCountRepository {

    /**
     * Listen for the current count of items added the the cart.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getCartItemCount(): Flow<Container<Int>>

}