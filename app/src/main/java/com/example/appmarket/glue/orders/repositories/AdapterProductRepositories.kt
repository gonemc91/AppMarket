package com.example.appmarket.glue.orders.repositories

import com.example.data.product.RealProductDataRepository
import com.example.orders.domain.repositories.ProductsRepository
import javax.inject.Inject

class AdapterProductRepositories @Inject constructor(
    private val productDataRepository: RealProductDataRepository,
) : ProductsRepository  {

    override suspend fun changeQuantity(productId: Long, byValue: Int) {
        productDataRepository.changeQuantityBy(productId, byValue)
    }
}