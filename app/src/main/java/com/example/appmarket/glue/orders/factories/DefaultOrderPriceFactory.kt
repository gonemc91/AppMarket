package com.example.appmarket.glue.orders.factories

import com.example.appmarket.formatters.PriceFormatter
import com.example.appmarket.glue.orders.entities.OrderUsdPrice
import com.example.orders.domain.entities.Price
import com.example.orders.domain.factories.PriceFactory
import javax.inject.Inject

class DefaultOrderPriceFactory @Inject constructor(
    private val priceFormatter: PriceFormatter,
): PriceFactory {

    override val zero: Price
        get() = OrderUsdPrice(usdCents = 0, formatter = priceFormatter)
}