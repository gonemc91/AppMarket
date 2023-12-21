package com.example.catalog.presentation.details

import com.example.catalog.R
import com.example.catalog.domain.AddToCartUseCase
import com.example.catalog.domain.GetProductsDetailsUseCase
import com.example.catalog.domain.entites.ProductWithCartInfo
import com.example.common.Container
import com.example.presentation.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


class ProduceDetailsViewModel @AssistedInject constructor(
    @Assisted private val screen:ProductDetailsFragment.Screen,
    private val getProductsDetailsUseCase: GetProductsDetailsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : BaseViewModel(){

    private val addToCartInProgressFlow = MutableStateFlow(false)
    private val productFlow = getProductsDetailsUseCase.getProduct(screen.productId)
    val stateLiveValue = combine(productFlow, addToCartInProgressFlow, ::merge)
        .toLiveValue(Container.Pending)

    fun reload() = debounce {
        getProductsDetailsUseCase.reload()
    }

    fun addToCart() = debounce {
        viewModelScope.launch {
            val state = stateLiveValue.value.getOrNull() ?: return@launch
            try {
                addToCartInProgressFlow.value = true
                addToCartUseCase.addToCart(state.product)
            }finally {
                addToCartInProgressFlow.value = false
            }
        }
    }

    private fun merge(
        productContainer: Container<ProductWithCartInfo>,
        isAddToCartInProgress: Boolean
    ): Container<State>{
        return productContainer.map{productWithCartInfo ->
            State(
                productWithCartInfo = productWithCartInfo,
                addToCartInProgress = isAddToCartInProgress
            )
        }
    }



    @AssistedFactory
    interface Factory{
        fun create(screen: ProductDetailsFragment.Screen): ProduceDetailsViewModel
    }

    class State(
        private val productWithCartInfo: ProductWithCartInfo,
        private val addToCartInProgress: Boolean,
    ){
        val product = productWithCartInfo.product

        val showAddToCartProgress: Boolean get() = addToCartInProgress
        val showAddToCartButton: Boolean get() = !addToCartInProgress
        val enableAddToCartButton: Boolean get() = !productWithCartInfo.isInCart
        val addToCartTextRes: Int get() = if (productWithCartInfo.isInCart){
            R.string.catalog_in_cart
        }else{
            R.string.catalog_add_to_cart
        }
    }
}