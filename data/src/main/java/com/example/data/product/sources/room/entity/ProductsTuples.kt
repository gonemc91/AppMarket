package com.example.data.product.sources.room.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.data.product.entities.ProductDataEntity


data class ProductDataTuple(
    @ColumnInfo(name = "productId") val productId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "shortDescription") val shortDescription: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "quantityAvailable") val quantityAvailable: Int,
    @ColumnInfo(name = "priceUsdCents") val priceUsdCents: Int,
    @ColumnInfo(name = "discountPercentage") val discountPercentage: Int,
    @ColumnInfo(name = "priceUsdCentsWithDiscount") val priceUsdCentsWithDiscount: Int,

) {
    fun toProductsDataEntity(): ProductDataEntity = ProductDataEntity(
        id = productId,
        name = name,
        category = category,
        shortDescription = shortDescription,
        description = description,
        imageUrl = imageUrl,
        quantityAvailable = quantityAvailable,
        priceUsdCents = priceUsdCents,
        priceUsdCentsWithDiscount = priceUsdCentsWithDiscount,
    )

}


data class ProductCategoryTuple(
    @ColumnInfo(name = "category") val category: String,
)

data class ProductUpdateQuantityTuple(
    @ColumnInfo(name = "productId") @PrimaryKey val productId: Long,
    @ColumnInfo(name = "quantityAvailable") val quantityAvailable: Int,
)



data class ProductDiscountTuple(
    @ColumnInfo(name = "productId") @PrimaryKey val productId: Long,
    @ColumnInfo(name = "priceUsdCents") val priceUsdCents: Int,
    @ColumnInfo(name = "discountPercentage") val discountPercentage: Int?,
    @ColumnInfo(name = "priceUsdCentsWithDiscount") val priceUsdCentsWithDiscount: Int,
)
