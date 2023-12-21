package com.example.data.product.sources

import com.example.data.product.entities.DiscountDataEntity
import javax.inject.Inject
import kotlin.random.Random

class InMemoryDiscountDataSource @Inject constructor(): DiscountDataSource {

    private val discounts = mutableListOf<DiscountDataEntity>()

    init {
        for (i in 1..50 step 3){
            discounts.add(DiscountDataEntity(i.toLong(), Random.nextInt(10,30)))
        }
    }
    override suspend fun getDiscountPercentage(productId: Long): Int? {
        return discounts.firstOrNull{it.productID == productId}?.discountPercentage
    }
}