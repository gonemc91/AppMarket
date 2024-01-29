package com.example.appmarket.glue.catalog.mappers

import com.example.catalog.domain.entites.SortOrder
import com.example.data.product.entities.SortOrderDataValue
import javax.inject.Inject

class SortOrderMapper @Inject constructor(){

    fun toSortOrderDataValue(sortOrder: SortOrder): SortOrderDataValue {
        return when (sortOrder){
            SortOrder.DESC -> SortOrderDataValue.DESC
            SortOrder.ASC -> SortOrderDataValue.ASC
        }
     }
}