package com.example.cart.presentation

import com.example.cart.presentation.quantity.entities.EditQuantityResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface CartRouter {

    /**
     * Launch product details screen.
     */

    fun launchProductDetails(productId: Long)

    /**
     * Go back to the previous screen.
     */

    fun goBack()

    /**
     * Launch screen for editing cart item's quantity.
     */

    fun launchEditQuantity(cartItemId: Long, initialQty: Int)

    /**
     * Launch a screen for making a new order.
     */

    fun launchCreateOrder()

    /**
     * Listen for quantity results sent by screen launched by [launchEditQuantity]
     */
    fun receiveQuantity(): Flow<EditQuantityResult>

    /**
     * Send quantity result from the screen launched by [launchEditQuantity]
     */

    fun sendsQuantity(editQuantityResult: EditQuantityResult)

    /**
     * Register back button listener. Callback may return true in order to
     * cancel the default back-button logic.
     */

    fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean)


}