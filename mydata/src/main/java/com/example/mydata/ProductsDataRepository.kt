package com.example.mydata

import com.example.common.Container
import com.example.mydata.product.entities.ProductDataEntity
import com.example.mydata.product.entities.ProductDataFilter
import kotlinx.coroutines.flow.Flow

interface ProductsDataRepository {

    /**
     * Listen for products that match the specified filter.
     * @retutn infinite flow, always success; errors are delivered to [Container]
     */

    fun getProducts(filter: ProductDataFilter): Flow<Container<List<ProductDataEntity>>>

    /**
     * Change the quantity by a value for product with the specified ID.
     * @throws NotFountException
     */

    suspend fun  changeQuantityBy(id: Long, quantityBy: Int)

    /**
     * Get the product ID
     * @throws NotFountException
     */

    suspend fun getProductById(id: Long): ProductDataEntity

    /**
     * Get mon product price.
     */

    suspend fun getMinPriceUsdCents(): Int

    /**
     * Get max product price.
     */

    suspend fun getMaxPriceUsdCents(): Int


    /**
     * Get price with discount for the specified products.
     */

    suspend fun getDiscountPriceUsdCentForEntity(product: ProductDataEntity): Int?

    /**
     * Get all available categories.
     */

    suspend fun getAllCategories(): List<String>

}