package com.example.cart.domain.enttites

interface Price {
    val text: String


    operator fun plus(price: Price): Price

    operator fun minus(price: Price): Price

    operator fun times(count: Int): Price
}