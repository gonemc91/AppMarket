package com.example.data.product.sources

import com.example.data.product.entities.ProductDataEntity
import com.example.data.product.entities.ProductDataFilter

interface ProductsDataSource {

    suspend fun getProducts(filter: ProductDataFilter): List<ProductDataEntity>

    suspend fun getProductById(id: Long): ProductDataEntity

    suspend fun getAllCategories(): List<String>

    suspend fun getDiscountPriceUsdCentsForEntity(product: ProductDataEntity): Int?

    suspend fun changeQuantityBy(id: Long, quantityBt: Int)
}