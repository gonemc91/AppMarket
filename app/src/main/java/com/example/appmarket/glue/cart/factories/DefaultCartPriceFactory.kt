package com.example.appmarket.glue.cart.factories

import com.example.appmarket.formatters.PriceFormatter
import com.example.appmarket.glue.cart.entities.CartUsdPrice
import com.example.cart.domain.enttites.Price
import com.example.cart.domain.factories.PriceFactory
import javax.inject.Inject

class DefaultCartPriceFactory @Inject constructor(
    private val priceFormatter: PriceFormatter
): PriceFactory {
    override val zero: Price
        get() = CartUsdPrice(0, priceFormatter)
}