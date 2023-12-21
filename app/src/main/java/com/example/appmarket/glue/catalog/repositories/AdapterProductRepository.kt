package com.example.appmarket.glue.catalog.repositories

import com.example.appmarket.formatters.PriceFormatter
import com.example.appmarket.glue.catalog.entities.CatalogUsdPrice
import com.example.appmarket.glue.catalog.mappers.ProductFilterMapper
import com.example.appmarket.glue.catalog.mappers.ProductMapper
import com.example.catalog.domain.entites.Price
import com.example.catalog.domain.entites.Product
import com.example.catalog.domain.entites.ProductFilter
import com.example.catalog.domain.repositories.ProductsRepository
import com.example.common.Container
import com.example.data.ProductsDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterProductRepository @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
    private val productMapper: ProductMapper,
    private val productFilterMapper: ProductFilterMapper,
    private val priceFormatter: PriceFormatter,
): ProductsRepository {

    override fun getProducts(filter: ProductFilter): Flow<Container<List<Product>>> {
        val dataFilter = productFilterMapper.toProductDataFilter(filter)
        return productsDataRepository.getProducts(dataFilter).map {container->
            container.suspendMap{list ->
                    list.map {dataEntity ->
                        productMapper.toProduct(dataEntity)
                    }
                }
            }
        }


    override suspend fun getProduct(id: Long): Product {
        val productDataEntity = productsDataRepository.getProductById(id)
        return productMapper.toProduct(productDataEntity)
    }

    override suspend fun getMinPossiblePrice(): Price {
        return CatalogUsdPrice(productsDataRepository.getMinPriceUsdCents(), priceFormatter)
    }

    override suspend fun getMaxPossiblePrice(): Price {
        return CatalogUsdPrice(productsDataRepository.getMaxPriceUsdCents(), priceFormatter)
    }

    override suspend fun getAllCategories(): List<String> {
        return productsDataRepository.getAllCategories()
    }
}