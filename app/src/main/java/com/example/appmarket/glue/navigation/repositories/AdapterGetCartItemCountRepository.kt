package com.example.appmarket.glue.navigation.repositories

import com.example.common.Container
import com.example.data.CartDataRepository
import com.example.navigation.domain.repositories.GetCartItemCountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AdapterGetCartItemCountRepository  @Inject constructor(
    private val cartDataRepository: CartDataRepository,
): GetCartItemCountRepository {
    override fun getCartItemCount(): Flow<Container<Int>> {
        TODO("Not yet implemented")
    }
}
