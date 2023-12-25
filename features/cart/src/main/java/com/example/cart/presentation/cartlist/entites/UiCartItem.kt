package com.example.cart.presentation.cartlist.entites

import com.example.cart.domain.enttites.CartItem
import com.example.cart.domain.enttites.Price
import com.example.cart.domain.enttites.Product

data class UiCartItem (
    val origin: CartItem,
    val isChecked: Boolean,
    val showCheckBox: Boolean,
    val minQuantity: Int,
    val maxQuantity: Int,
){
    val id: Long get() = origin.id
    val product: Product get() = origin.product
    val imageUrl: String get() = product.photo
    val quantity: Int get() = origin.quantity
    val name: String get() = product.name
    val totalOriginPrice: Price get() = origin.totalPrice
    val totalDiscountPrice: Price get() = origin.totalPriceWithoutDiscount
    val canIncrement: Boolean get() = quantity < maxQuantity
    val canDecrement: Boolean get() = quantity > minQuantity
}