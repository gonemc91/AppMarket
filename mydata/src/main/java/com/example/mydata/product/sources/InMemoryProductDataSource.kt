package com.example.mydata.product.sources

import com.example.common.NotFoundException
import com.example.mydata.product.entities.ProductDataEntity
import com.example.mydata.product.entities.ProductDataFilter
import com.example.mydata.product.entities.SortByDataValue
import com.example.mydata.product.entities.SortOrderDataValue
import com.github.javafaker.Faker
import kotlinx.coroutines.runBlocking
import java.util.Locale
import java.util.Random
import javax.inject.Inject


class InMemoryProductDataSource @Inject constructor (
    private val discountDataSource: DiscountDataSource,
): ProductsDataSource {


    private val availableImages = listOf(
        "https://images.unsplash.com/photo-1546554137-f86b9593a222?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=480&ixid=MnwxfDB8MXxyYW5kb218MHx8Zm9vZHx8fHx8fDE2NzUxNjk1ODU&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=640",
        "https://images.unsplash.com/photo-1540189549336-e6e99c3679fe?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=480&ixid=MnwxfDB8MXxyYW5kb218MHx8Zm9vZHx8fHx8fDE2NzUxNjk2MDQ&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=640",
        "https://images.unsplash.com/photo-1545093149-618ce3bcf49d?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=480&ixid=MnwxfDB8MXxyYW5kb218MHx8Zm9vZHx8fHx8fDE2NzUxNjk2MjI&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=640",
        "https://images.unsplash.com/photo-1454944338482-a69bb95894af?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=480&ixid=MnwxfDB8MXxyYW5kb218MHx8Zm9vZHx8fHx8fDE2NzUxNjk2MTU&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=640",
        "https://images.unsplash.com/photo-1486328228599-85db4443971f?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=480&ixid=MnwxfDB8MXxyYW5kb218MHx8Zm9vZHx8fHx8fDE2NzUxNjk2NTU&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=640",
        "https://images.unsplash.com/photo-1562967916-eb82221dfb92?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=480&ixid=MnwxfDB8MXxyYW5kb218MHx8Zm9vZHx8fHx8fDE2NzUxNjk2NTA&ixlib=rb-4.0.3&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=640",
    )


    private val faker = Faker.instance(Locale("en-GB"), Random(10))

    private val products = generateRandomProducts()

    override suspend fun getProducts(filter: ProductDataFilter): List<ProductDataEntity> {
        val filterList = products.filter { filterProduct(it, filter) }
        val sortedList = when(filter.sortBy){
            SortByDataValue.NAME -> filterList.sortedBy { it.name }
            SortByDataValue.PRICE -> filterList.sortedBy {
                runBlocking {
                    getDiscountPriceUsdCentsForEntity(it) ?: it.priceUsdCents
                }
            }
        }
        return if (filter.sortOrder == SortOrderDataValue.DESC){
            sortedList.reversed()
        }else{
            sortedList
        }

    }

    override suspend fun getProductById(id: Long): ProductDataEntity {
        return products.firstOrNull { it.id == id } ?: throw NotFoundException()
    }

    override suspend fun getAllCategories(): List<String> {
        return products.map { it.category }.distinct()
    }

    override suspend fun getDiscountPriceUsdCentsForEntity(product: ProductDataEntity): Int? {
        val percentage = discountDataSource.getDiscountPercentage(product.id) ?: return null
        val discountProportion = 1 - percentage/100.0
        return (discountProportion * product.priceUsdCents).toInt()
    }

    override suspend fun changeQuantityBy(id: Long, quantityBt: Int) {
        val index = products.indexOfFirst { it.id == id }
        if (index == -1) throw NotFoundException()
        val oldProduct = products[index]
        val newQuantity = oldProduct.quantityAvailable + quantityBt
        if (newQuantity > 0){
            products[index] = oldProduct.copy(
                quantityAvailable = newQuantity
            )
        }else{
            products.removeAt(index)
        }
    }


     private fun generateRandomProducts():MutableList<ProductDataEntity> {
        val categories = listOf(
            "Dishes",
            "Desserts",
            "Vegetables",
            "Fruits",
            "Meat & Cheese"
        )
        val random = kotlin.random.Random(0)
        var idSequence: Long = 0
        return categories.flatMap { category ->
            List(random.nextInt(10, 30)){
                val generateDesc = faker.lorem().paragraph(8)
                ProductDataEntity(
                    id = ++idSequence,
                    name = "${faker.food().dish()} ${faker.food().ingredient()}",
                    category = category,
                    shortDescription = generateDesc.substring(0,100),
                    description = generateDesc,
                    imageUrl = availableImages[idSequence.toInt() % availableImages.size],
                    quantityAvailable = random.nextInt(10, 60),
                    priceUsdCents = random.nextInt(15, 100) * 100 + 99
                )
            }
        }.shuffled().toMutableList()

    }

    private suspend fun filterProduct(product: ProductDataEntity, filter: ProductDataFilter):Boolean{
        if (filter.category != null && product.category != filter.category) return false
        if (filter.minPriceUsdCents != null){
            val price = getDiscountPriceUsdCentsForEntity(product) ?: product.priceUsdCents
            if (price < filter.minPriceUsdCents) return false
        }
        if(filter.minPriceUsdCents != null){
            val price = getDiscountPriceUsdCentsForEntity(product) ?: product.priceUsdCents
            if(price > filter.maxPriceUsdCents!!) return false
        }
        return true
    }

}