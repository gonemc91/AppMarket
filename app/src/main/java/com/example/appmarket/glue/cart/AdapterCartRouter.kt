package com.example.appmarket.glue.cart

import com.example.appmarket.R
import com.example.cart.presentation.CartRouter
import com.example.cart.presentation.quantity.EditQuantityDialogFragment
import com.example.cart.presentation.quantity.entities.EditQuantityResult
import com.example.catalog.presentation.details.ProductDetailsFragment
import com.example.common.ScreenCommunication
import com.example.common.listen
import com.example.navigation.GlobalNavComponentRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdapterCartRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter,
    private val screenCommunication: ScreenCommunication,
): CartRouter {
    override fun launchProductDetails(productId: Long) {
        globalNavComponentRouter.launch(
            R.id.productDetailsFragment,
            ProductDetailsFragment.Screen(productId)
        )
    }

    override fun goBack() {
     globalNavComponentRouter.pop()
    }

    override fun launchEditQuantity(cartItemId: Long, initialQty: Int) {
        globalNavComponentRouter.launch(
            R.id.editQuantityDialogFragment,
            EditQuantityDialogFragment.Screen(cartItemId, initialQty)
        )
    }

    override fun launchCreateOrder() {
        globalNavComponentRouter.launch(
            R.id.createOrdersFragment,
        )
    }

    override fun receiveQuantity(): Flow<EditQuantityResult> {
        return screenCommunication.listen(EditQuantityResult::class.java)
    }

    override fun sendsQuantity(editQuantityResult: EditQuantityResult) {
        return screenCommunication.publishResult(editQuantityResult)
    }

    override fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean) {
        globalNavComponentRouter.registerBackHandler(scope, handler)
    }
}