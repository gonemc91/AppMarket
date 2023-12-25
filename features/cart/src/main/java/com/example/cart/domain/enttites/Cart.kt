package com.example.cart.domain.enttites

import com.example.cart.domain.factories.PriceFactory

data class Cart(
    val cartItems: List<CartItem>,
    private val priceFactory: PriceFactory,
){
    val totalPrice: Price get() {
        if(cartItems.isEmpty()) return priceFactory.zero
        return cartItems
            .map { it.totalPrice }
            .reduce{acc, price -> acc + price }
    }

    val totalPriceWithoutDiscount: Price get() {
        if (cartItems.isEmpty()) return priceFactory.zero
        return cartItems
            .map { it.totalPriceWithoutDiscount}
            .reduce{acc, price -> acc + price }
    }


}
