package com.example.orders.presentation.orders

import com.example.common.Container
import com.example.common.entities.OnChange
import com.example.orders.domain.CancelOrderUseCase
import com.example.orders.domain.entities.Order
import com.example.orders.domain.repositories.GetOrderUseCase
import com.example.orders.presentation.orders.entites.UiOrder
import com.example.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class OrdersListViewModel @Inject constructor(
    private val getOrderUseCase: GetOrderUseCase,
    private val cancelOrderUseCase: CancelOrderUseCase,
): BaseViewModel() {

    private val cancellationUuidsFlow = MutableStateFlow(OnChange(mutableSetOf<String>()))

    val stateLiveValue = combine(
        getOrderUseCase.getOrders(),
        cancellationUuidsFlow,
        ::merge
    ).toLiveValue()

    fun reload() = debounce {
        getOrderUseCase.reload()
    }


    fun cancelOrder(order: UiOrder) = debounce {
        viewModelScope.launch {
            val cancellationIds = cancellationUuidsFlow.value.value
            cancellationIds.add(order.uuid)
            cancellationUuidsFlow.value = OnChange(cancellationIds)
            try {
                cancelOrderUseCase.cancelOrder(order.origin)
                cancellationIds.remove(order.uuid)// change cancellation state bun not update
            }catch (e: Exception){
                cancellationIds.remove(order.uuid) //change cancellation state...
                cancellationUuidsFlow.value = OnChange(cancellationIds) //..and update
                throw e
            }
        }
    }



    private fun merge(
        orderContainer: Container<List<Order>>,
        cancellations: OnChange<MutableSet<String>>
    ): Container<State>{
        return orderContainer.map { list ->
            State(
                orders = list.map {
                    UiOrder(
                        origin = it,
                        canCancel = cancelOrderUseCase.canCancel(it),
                        cancelInProgress = cancellations.value.contains(it.uuid)
                    )
                }
            )
        }
    }

    class State(
        val orders: List<UiOrder>
    )

}