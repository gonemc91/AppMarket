package com.example.appmarket.glue.cart.mapper

import com.example.appmarket.formatters.PriceFormatter
import com.example.appmarket.glue.cart.entities.CartUsdPrice
import com.example.cart.domain.enttites.CartItem
import com.example.cart.domain.enttites.Product
import com.example.data.ProductsDataRepository
import com.example.data.cart.entities.CartItemDataEntity
import javax.inject.Inject

class CartItemMapper @Inject constructor(
    private val productsDataRepository: ProductsDataRepository,
    private val priceFormatter: PriceFormatter,
) {

    suspend fun toCartItem(dataEntity: CartItemDataEntity): CartItem{
        val productDataEntity = productsDataRepository.getProductById(dataEntity.id)
        val productPriceWithDiscount = productsDataRepository.getDiscountPriceUsdCentForEntity(productDataEntity)
        val product = Product(
            id = productDataEntity.id,
            name = productDataEntity.name,
            shortDetails = productDataEntity.shortDescription,
            photo = productDataEntity.imageUrl,
            totalQuantity = productDataEntity.quantityAvailable
        )

        val discountPerItem = productDataEntity.priceUsdCents - (productPriceWithDiscount ?: productDataEntity.priceUsdCents)
        return CartItem(
            id = dataEntity.id,
            product = product,
            quantity = dataEntity.quantity,
            pricePerItem = CartUsdPrice(productDataEntity.priceUsdCents, priceFormatter),
            discountPerItem = CartUsdPrice(discountPerItem, priceFormatter)
        )
    }
}