package com.example.appmarket.formatters

interface PriceFormatter {

    /**
     * Convert cents into user-friendly price text.
     */

    fun formatPrice(usdCents: Int): String
}