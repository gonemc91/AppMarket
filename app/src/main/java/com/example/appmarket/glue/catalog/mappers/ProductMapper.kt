package com.example.appmarket.glue.catalog.mappers

import com.example.appmarket.formatters.PriceFormatter
import com.example.appmarket.glue.catalog.entities.CatalogUsdPrice
import com.example.catalog.domain.entites.Product
import com.example.data.ProductsDataRepository
import com.example.data.product.entities.ProductDataEntity
import javax.inject.Inject

class ProductMapper @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
    private val priceFormatter: PriceFormatter,
    ) {

    suspend fun toProduct(dataEntity: ProductDataEntity): Product{
        val discountPrice = productsDataRepository.getDiscountPriceUsdCentForEntity(dataEntity)?.let {
            CatalogUsdPrice(it, priceFormatter)
        }
        return Product(
            id = dataEntity.id,
            name = dataEntity.name,
            shortDetails = dataEntity.shortDescription,
            details = dataEntity.description,
            category = dataEntity.category,
            price = CatalogUsdPrice(dataEntity.priceUsdCents, priceFormatter),
            priceWithDiscount = discountPrice,
            photo = dataEntity.imageUrl
        )
    }
}