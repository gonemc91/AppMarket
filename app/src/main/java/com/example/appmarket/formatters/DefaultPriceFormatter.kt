package com.example.appmarket.formatters

import javax.inject.Inject

class DefaultPriceFormatter @Inject constructor(): PriceFormatter {

    override fun formatPrice(usdCents: Int): String {
        return "\$${String.format("%.2f", usdCents / 100f)}"
    }
}