package com.example.appmarket.glue.catalog.mappers

import com.example.appmarket.glue.catalog.entities.CatalogUsdPrice
import com.example.catalog.domain.entites.ProductFilter
import com.example.data.product.entities.ProductDataFilter
import javax.inject.Inject

class ProductFilterMapper @Inject constructor(
    private val sortOrderMapper: SortOrderMapper,
    private val sortByMapper: SortByMapper,
) {

    fun toProductDataFilter(filter: ProductFilter): ProductDataFilter {
        return ProductDataFilter(
            category = filter.category,
            minPriceUsdCents = (filter.minPrice as? CatalogUsdPrice)?.usdCents,
            maxPriceUsdCents = (filter.maxPrice as? CatalogUsdPrice)?.usdCents,
            sortBy = sortByMapper.toSortByDataValue(filter.sortBy),
            sortOrder = sortOrderMapper.toSortOrderDataValue(filter.sortOrder),
        )
    }
}