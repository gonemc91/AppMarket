package com.example.catalog.domain.entites

import java.io.Serializable

data class ProductFilter (
    val category: String? = null,
    val minPrice: Price? = null,
    val maxPrice: Price? = null,
    val sortBy: SortBy = SortBy.NAME,
    val sortOrder: SortOrder = SortOrder.ASC,
) : Serializable{

    companion object{
        val EMPTY = ProductFilter()
    }

}
