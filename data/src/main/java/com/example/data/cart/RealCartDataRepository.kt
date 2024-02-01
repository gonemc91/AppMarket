package com.example.data.cart

import com.example.common.Container
import com.example.common.NotFoundException
import com.example.common.flow.LazyFlowSubjectFactory
import com.example.data.CartDataRepository
import com.example.data.cart.entities.CartItemDataEntity
import com.example.data.cart.sources.CartDataSource
import com.example.data.settings.SettingDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RealCartDataRepository @Inject constructor (
    private val cartDataSource: CartDataSource,
    private val settingDataSource: SettingDataSource,
    scope: CoroutineScope,
    lazyFlowSubjectFactory: LazyFlowSubjectFactory,
) : CartDataRepository {

    init {
        scope.launch {
            settingDataSource.listenToken().collect {
                if (it == null){
                    cartDataSource.clearCart()
                    cartSubject.updateWith(Container.Success(emptyList()))
                }
            }
        }
    }

    private val cartSubject = lazyFlowSubjectFactory.create {
        cartDataSource.getCart()
    }

    override fun getCart(): Flow<Container<List<CartItemDataEntity>>> {
        cartSubject.newAsyncLoad(silently = true)
        return cartSubject.listen()
    }

    override suspend fun getCartItemById(id: Long): CartItemDataEntity {
        return cartDataSource.getCart().firstOrNull{it.id == id} ?: throw NotFoundException()
    }

    override suspend fun addToCart(productId: Long, quantity: Int) {
        cartDataSource.saveToCart(productId, quantity)
        notifyChanges()
    }

    override suspend fun deleteCartItem(ids: List<Long>) {
        ids.forEach{ cartDataSource.delete(it) }
        cartSubject.newAsyncLoad(silently = true)
    }

    override suspend fun deleteAll() {
        cartDataSource.deleteAll()
        cartSubject.newAsyncLoad(silently = true)
    }

    override suspend fun changeQuantity(cartId: Long, quantity: Int) {
        val cartItem = getCartItemById(cartId)
        val productId = cartItem.productId
        cartDataSource.saveToCart(productId, quantity)
        cartSubject.newAsyncLoad(silently = true)
    }

    override fun reload() {
        cartSubject.newAsyncLoad()
    }


    private suspend fun notifyChanges(){
        cartSubject.updateWith(Container.Success(cartDataSource.getCart()))
    }
}