package com.example.cart.presentation.quantity

import com.example.cart.R
import com.example.cart.domain.GetCartUseCase
import com.example.cart.domain.ValidateCartItemQuantityUseCase
import com.example.cart.domain.enttites.CartItem
import com.example.cart.domain.exceptions.QuantityOutOfRangeException
import com.example.cart.presentation.CartRouter
import com.example.cart.presentation.quantity.entities.EditQuantityResult
import com.example.common.Container
import com.example.presentation.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class EditQuantityViewModel @AssistedInject constructor(
    @Assisted private val screen: EditQuantityDialogFragment.Screen,
    private val router: CartRouter,
    private val getCartUseCase: GetCartUseCase,
    private val validateCartItemQuantityUseCase: ValidateCartItemQuantityUseCase,
) : BaseViewModel() {

    val initialQuantityLiveEvent = liveEvent<Int>()
    val stateLiveValue = liveValue<Container<State>>()


    init {
        initialQuantityLiveEvent.publish(screen.initialQuantity)
        load()
    }


    fun load() = debounce {
        loadScreenInto(stateLiveValue){
            val cartItem = getCartUseCase.getCartById(screen.cartItemId)
            val maxQuantity = validateCartItemQuantityUseCase.getMaxQuantity(cartItem)
            State(cartItem, maxQuantity)
        }
    }


    fun saveNewQuantity(input: String) = debounce {
        val carItem = stateLiveValue.getValue()?.getOrNull()?.carItem ?: return@debounce
        val parseQuantity = try {
            input.toInt()
        } catch (e: Exception){
            commonUi.toast(resources.getString(R.string.cart_invalid_quantity))
            return@debounce
        }
        viewModelScope.launch {
            try {
                validateCartItemQuantityUseCase.validateNewQuantity(carItem, parseQuantity)
                router.sendsQuantity(EditQuantityResult(carItem.id, parseQuantity))
                delay(1000)
                router.goBack()
            }catch (e: QuantityOutOfRangeException){
                commonUi.toast(resources.getString(R.string.cart_quantity_out_of_range))
            }
        }
    }


    @AssistedFactory
    interface Factory {
        fun create(screen: EditQuantityDialogFragment.Screen): EditQuantityViewModel
    }

    class State(
        val carItem: CartItem,
        val maxQuantity: Int,
    )
}