package com.example.data.product

import com.example.common.Container
import com.example.common.entities.OnChange
import com.example.common.flow.LazyFlowSubjectFactory
import com.example.data.ProductsDataRepository
import com.example.data.product.entities.ProductDataEntity
import com.example.data.product.entities.ProductDataFilter
import com.example.data.product.sources.ProductsDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class RealProductDataRepository @Inject constructor(
    private val productsDataSource: ProductsDataSource,
    private val lazyFlowSubjectFactory: LazyFlowSubjectFactory,
): ProductsDataRepository {

    private val updateNotifierFlow = MutableStateFlow(OnChange(Unit))

    override suspend fun getProductById(id: Long): ProductDataEntity {
        return productsDataSource.getProductById(id)
    }
@ExperimentalCoroutinesApi
    override fun getProducts(filter: ProductDataFilter): Flow<Container<List<ProductDataEntity>>> {

        return updateNotifierFlow.flatMapLatest{
            delay(1000)
            lazyFlowSubjectFactory.create {
                productsDataSource.getProducts(filter)
            }.listen()
        }
    }

    override suspend fun changeQuantityBy(id: Long, quantityBy: Int) {
        productsDataSource.changeQuantityBy(id, quantityBy)
        updateNotifierFlow.value = OnChange(Unit)
    }



    override suspend fun getMinPriceUsdCents(): Int {
        return productsDataSource.getProducts(ProductDataFilter.DEFAULT)
           .minOf { getDiscountPriceUsdCentForEntity(it) ?: it.priceUsdCents }
    }

    override suspend fun getMaxPriceUsdCents(): Int {
        return  productsDataSource.getProducts(ProductDataFilter.DEFAULT)
            .maxOf { getDiscountPriceUsdCentForEntity(it) ?: it.priceUsdCents }
    }

    override suspend fun getDiscountPriceUsdCentForEntity(product: ProductDataEntity): Int? {
       return  productsDataSource.getDiscountPriceUsdCentsForEntity(product)
    }

    override suspend fun getAllCategories(): List<String> {
        return productsDataSource.getAllCategories()
    }
}