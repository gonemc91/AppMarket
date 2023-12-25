package com.example.appmarket.glue.cart.repositories

import com.example.cart.domain.repositories.ProductRepository
import com.example.data.ProductsDataRepository
import javax.inject.Inject

class AdapterProductRepositories  @Inject constructor(
    private val productDataRepository: ProductsDataRepository
): ProductRepository{

    override suspend fun getAvailableQuantity(productId: Long): Int {
        return productDataRepository.getProductById(productId).quantityAvailable
    }
}