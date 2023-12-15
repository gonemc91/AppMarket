package com.example.catalog.domain.entites

data class ProductWithCartInfo (
    val product: Product,
    val isInCart: Boolean,
)