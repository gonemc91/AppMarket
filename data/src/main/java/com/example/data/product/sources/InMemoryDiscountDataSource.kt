package com.example.data.product.sources

import com.example.data.product.entities.DiscountDataEntity
import com.example.data.product.sources.room.ProductsDao
import com.example.data.product.sources.room.entity.ProductDiscountTuple
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class InMemoryDiscountDataSource @Inject constructor(
    private val productsDao: ProductsDao,
    coroutineScope: CoroutineScope,
): DiscountDataSource {

    private val discounts = mutableListOf<DiscountDataEntity>()

    init {
        coroutineScope.launch {
            generateRandomDiscount(productsDao, discounts)
        }
    }

    override suspend fun getDiscountPercentage(productId: Long): Int? {
        return discounts.firstOrNull { it.productID == productId }?.discountPriceUsdCents
    }


    companion object {
        suspend fun generateRandomDiscount(productsDao: ProductsDao, discounts: MutableList<DiscountDataEntity>){

            val discountsProductsForInit = productsDao.getAllDiscountsProducts()

            for (i in 1..50 step 3){
                val initProduct = discountsProductsForInit.first{it.productId == i.toLong()}
                val discountPercentage =  Random.nextInt(10,30)
                val discountProportion = 1 - discountPercentage / 100.0
                val priceCentsWithDiscount = (discountProportion * initProduct.priceUsdCents).toInt()
                discounts.add(DiscountDataEntity(i.toLong(), discountPercentage, priceCentsWithDiscount))

                sendDiscountPriceUsdCentsDataToDB(
                    productsDao,
                    productId = i.toLong(),
                    priceUsdCents = initProduct.priceUsdCents,
                    percentage = discountPercentage,
                    priceUsdCentsWithDiscount = priceCentsWithDiscount
                )
            }
        }

        private suspend fun sendDiscountPriceUsdCentsDataToDB(
            productsDao: ProductsDao,
            productId: Long,
            priceUsdCents: Int,
            percentage: Int,
            priceUsdCentsWithDiscount: Int,
        ) {
            productsDao.initDiscount(
                ProductDiscountTuple(
                    productId = productId,
                    priceUsdCents = priceUsdCents,
                    discountPercentage = percentage,
                    priceUsdCentsWithDiscount = priceUsdCentsWithDiscount,
                )
            )
        }




    }


}