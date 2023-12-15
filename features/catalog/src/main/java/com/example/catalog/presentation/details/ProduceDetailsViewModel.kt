package com.example.catalog.presentation.details

import com.example.catalog.R
import com.example.catalog.domain.AddToCartUseCase
import com.example.catalog.domain.GetProductsDetailsUseCase
import com.example.catalog.domain.entites.ProductWithCartInfo
import com.example.presentation.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class ProduceDetailsViewModel @AssistedInject constructor(
    @Assisted private val screen:ProductDetailsFragment.Screen,
    private val getProductsDetailsUseCase: GetProductsDetailsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : BaseViewModel(){

    private val addToCartProgressFlow = MutableStateFlow(false)
    private val productFlow = getProductsDetailsUseCase.getProduct(screen.productId)







    @AssistedFactory
    interface Factory{
        fun create(screen: ProductDetailsFragment.Screen): ProduceDetailsViewModel
    }

    class State(
        private val productWithCartInfo: ProductWithCartInfo,
        private val addToCartInProgress: Boolean,
    ){
        val product = productWithCartInfo.product
        val showAddCartProgress: Boolean get() = addToCartInProgress
        val showAddToCartButton get() = !addToCartInProgress
        val enableAddToCartButton: Boolean get() = !productWithCartInfo.isInCart
        val addToCartTextRes: Int get() = if (productWithCartInfo.isInCart){
            R.string.catalog_in_cart
        }else{
            R.string.catalog_add_to_cart
        }
    }
}