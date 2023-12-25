package com.example.cart.domain.factories

import com.example.cart.domain.enttites.Price

interface PriceFactory {

    /**
     * Create a zero price.
     */

    val zero: Price

}