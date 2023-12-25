package com.example.appmarket.glue.orders.repositories

import com.example.appmarket.formatters.PriceFormatter
import com.example.appmarket.glue.orders.entities.OrderUsdPrice
import com.example.common.unwrapFirst
import com.example.data.cart.RealCartDataRepository
import com.example.data.product.RealProductDataRepository
import com.example.orders.domain.entities.Cart
import com.example.orders.domain.entities.CartItem
import com.example.orders.domain.factories.PriceFactory
import com.example.orders.domain.repositories.CartRepository
import javax.inject.Inject

class AdapterCartRepository @Inject constructor(
    private val cartDataRepository: RealCartDataRepository,
    private val productDataRepository: RealProductDataRepository,
    private val priceFormatter: PriceFormatter,
    private val priceFactory: PriceFactory,
):
CartRepository{
    override suspend fun getCart(): Cart {
        val cartItems = cartDataRepository.getCart().unwrapFirst()
        return Cart(
            cartItems = cartItems.map {
                val product = productDataRepository.getProductById(it.productId)
                val priceWithDiscount = productDataRepository.getDiscountPriceUsdCentForEntity(product)
                    ?: product.priceUsdCents
                CartItem(
                    name = product.name,
                    productId = it.productId,
                    price = OrderUsdPrice(priceWithDiscount, priceFormatter),
                    quantity = it.quantity
                )
            },
            priceFactory = priceFactory
        )
    }

    override suspend fun cleanUpCart() {
        cartDataRepository.deleteAll()
    }
}