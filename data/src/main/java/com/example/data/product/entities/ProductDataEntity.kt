package com.example.data.product.entities

import com.example.data.product.sources.room.entity.ProductsRoomEntity

data class ProductDataEntity(
    val id: Long,
    val name: String,
    val category: String,
    val shortDescription: String,
    val description: String,
    val imageUrl: String,
    val quantityAvailable: Int,
    val priceUsdCents: Int,
    val priceUsdCentsWithDiscount: Int
){
    fun toProductsRoomEntity(): ProductsRoomEntity = ProductsRoomEntity(
        productId = id,
        name = name,
        category = category,
        shortDescription = shortDescription,
        description = description,
        imageUrl = imageUrl,
        quantityAvailable = quantityAvailable,
        priceUsdCents = priceUsdCents,
        priceUsdCentsWithDiscount = priceUsdCentsWithDiscount,
        discountPercentage = 0
    )

}



