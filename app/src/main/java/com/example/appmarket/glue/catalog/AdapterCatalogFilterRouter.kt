package com.example.appmarket.glue.catalog

import com.example.appmarket.R
import com.example.catalog.domain.entites.ProductFilter
import com.example.catalog.presentation.CatalogFilterRouter
import com.example.catalog.presentation.filter.CatalogFilterFragment
import com.example.common.ScreenCommunication
import com.example.common.listen
import com.example.navigation.GlobalNavComponentRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdapterCatalogFilterRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter,
    private val screenCommunication: ScreenCommunication,
) : CatalogFilterRouter {


    override fun launchFilter(initialFilter: ProductFilter) {
        globalNavComponentRouter.launch(R.id.catalogFilterFragment,
            CatalogFilterFragment.Screen(initialFilter)
        )
    }

    override fun sendFilterResults(filter: ProductFilter) {
        return screenCommunication.publishResult(filter)
    }

    override fun receiveFilterResults(): Flow<ProductFilter> {
        return screenCommunication.listen(ProductFilter::class.java)
    }

    override fun goBack() {
        globalNavComponentRouter.pop()
    }

    override fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean) {
        globalNavComponentRouter.registerBackHandler(scope, handler)
    }
}