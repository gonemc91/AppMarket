package com.example.orders.domain.factories

import com.example.orders.domain.entities.Price

interface PriceFactory {
    /**
     * Create a zero price.
     */

    val zero: Price

}