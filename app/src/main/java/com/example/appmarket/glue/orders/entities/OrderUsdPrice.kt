package com.example.appmarket.glue.orders.entities

import com.example.appmarket.formatters.PriceFormatter
import com.example.orders.domain.entities.Price

data class OrderUsdPrice(
    val usdCents: Int,
    private val formatter: PriceFormatter,
): Price {
    override val text: String
        get() = formatter.formatPrice(usdCents)

    override fun plus(price: Price): Price {
        return OrderUsdPrice(
            usdCents = usdCents + (price as OrderUsdPrice).usdCents,
            formatter = formatter
        )
    }
}
