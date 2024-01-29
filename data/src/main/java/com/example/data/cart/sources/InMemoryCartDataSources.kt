package com.example.data.cart.sources

import com.example.common.Core
import com.example.data.cart.entities.CartItemDataEntity
import com.example.data.cart.sources.room.CartDao
import com.example.data.cart.sources.room.entites.CartItemIdTuples
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class InMemoryCartDataSources @Inject constructor(
    coroutineScope: CoroutineScope,
    private val cartDao: CartDao,
) : CartDataSource {


    private val cart = mutableListOf<CartItemDataEntity>()

    init {
        coroutineScope.launch {
                val getAllDataFromDB = cartDao.getAllCartData()
               getAllDataFromDB.map {
                   cart.add(it.toCartItemDataEntity())
               }.toMutableList()
            }
        }

    override suspend fun clearCart() {
        cart.clear()
        cartDao.deleteAll()
    }

    override suspend fun getCart(): List<CartItemDataEntity> {
        cart.forEach {
            Core.logger.log("get cart ${it.productId}")}

        return ArrayList(cart)
    }

    override suspend fun saveToCart(productId: Long, quantity: Int) {
        Core.logger.log("$productId : $quantity")
        val index = cart.indexOfFirst {it.productId == productId}
        val cartItem = CartItemDataEntity(
            productId, productId, quantity
        )
        val carItemForDb = cartItem.toCartDataTuples()

        if(index != -1){
            cart[index] = cartItem
            cartDao.updateItem(carItemForDb)
        }else{
            cart.add(cartItem)
            cartDao.createProduct(carItemForDb)
        }
    }

    override suspend fun delete(cartItemId: Long) {
        cart.removeAll { it.id == cartItemId }
        cartDao.deleteProductByID(CartItemIdTuples(cartItemId))
    }

    override suspend fun deleteAll() {
        cart.clear()
        cartDao.deleteAll()
        Core.logger.log("deleteAll in Memory ${cart.forEach {it.productId}}")
    }
}