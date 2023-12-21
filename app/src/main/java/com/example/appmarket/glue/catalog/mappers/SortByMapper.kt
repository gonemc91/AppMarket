package com.example.appmarket.glue.catalog.mappers

import com.example.catalog.domain.entites.SortBy
import com.example.data.product.entities.SortByDataValue
import javax.inject.Inject

class SortByMapper @Inject constructor() {

    fun toSortByDataValue(sortBy: SortBy): SortByDataValue {
        return when (sortBy){
            SortBy.PRICE -> SortByDataValue.PRICE
            SortBy.NAME -> SortByDataValue.NAME
        }
    }
}