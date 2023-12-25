package com.example.appmarket.glue.orders

import com.example.appmarket.R
import com.example.navigation.GlobalNavComponentRouter
import com.example.orders.presentation.OrdersRouter
import javax.inject.Inject

class AdapterOrdersRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
): OrdersRouter {

    override fun launchOrdersTab() {
        globalNavComponentRouter.startTabs(startTabDestinationId = R.id.ordersListFragment)
    }
}