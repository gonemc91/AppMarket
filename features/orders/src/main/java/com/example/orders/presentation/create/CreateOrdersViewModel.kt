package com.example.orders.presentation.create

import com.example.common.AlertDialogConfig
import com.example.common.Container
import com.example.common.Core
import com.example.orders.R
import com.example.orders.domain.CreatedOrderUseCase
import com.example.orders.domain.GetCartUseCase
import com.example.orders.domain.entities.Cart
import com.example.orders.domain.entities.Field
import com.example.orders.domain.entities.Recipient
import com.example.orders.domain.exceptions.EmptyFieldException
import com.example.orders.presentation.OrdersRouter
import com.example.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateOrdersViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val createOrderUseCase: CreatedOrderUseCase,
    private val router: OrdersRouter,
) : BaseViewModel() {

    private val createdOrderInProgressFlow = MutableStateFlow(false)
    private val cartFlow = MutableStateFlow<Container<Cart>>(Container.Pending)

    val stateLiveValue = combine(cartFlow, createdOrderInProgressFlow, ::merge)
        .toLiveValue(Container.Pending)

    val emptyFieldErrorLiveEvent = liveEvent<Field>()

    init {
        load()
    }

    fun load() = debounce {
        loadScreenInto(cartFlow){
            getCartUseCase.getCart()

        }
    }


    fun createdOrder(recipient: Recipient) = debounce {
        viewModelScope.launch {
            val cart = (stateLiveValue.value as? Container.Success)?.value?.cart ?: return@launch
            try {
                createdOrderInProgressFlow.value = true
                createOrderUseCase.createdOrder(cart, recipient)
                createdOrderInProgressFlow.value = false
                showSuccessDialog()
                router.launchOrdersTab()
            } catch (e: EmptyFieldException){
                emptyFieldErrorLiveEvent.publish(e.field)
            }finally {
                createdOrderInProgressFlow.value = false
            }
        }
    }



    private suspend fun showSuccessDialog(){
        commonUi.alertDialog(
            AlertDialogConfig(
                title = Core.resources.getString(R.string.orders_congrats),
                message = Core.resources.getString(R.string.orders_order_created),
                positiveButton = Core.resources.getString(R.string.orders_create_order_ok)
            )
        )
    }




    private fun merge(
        cartContainer: Container<Cart>,
        createdOrderInProgress: Boolean
    ): Container<State>{
        return cartContainer.map{
            State(it, createdOrderInProgress)
        }
    }


    class State(
        val cart: Cart,
        private val createdOrderInProgress: Boolean
    ){
        val enableCratedOrderButton: Boolean get() = !createdOrderInProgress
        val showProgressBar: Boolean get() = createdOrderInProgress
    }


}