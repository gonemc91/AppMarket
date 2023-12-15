package com.example.catalog.presentation

interface CatalogRouter {

    /**
     * Launch product details screen.
     */

    fun launchDetails(productId: Long)

    /**
     * Launch a screen for creating a new order.
     */

    fun launchCreateOrder()

}