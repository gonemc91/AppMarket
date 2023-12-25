package com.example.cart.domain.enttites

data class CartItem(
    val id: Long,
    val product: Product,
    val quantity: Int,
    val pricePerItem: Price,
    val discountPerItem: Price,
){
    val totalPrice: Price get() = pricePerItem * quantity
    val totalPriceWithoutDiscount: Price get() = (pricePerItem - discountPerItem) * quantity
}
