package com.example.catalog.presentation.catalog

import com.example.catalog.domain.AddToCartUseCase
import com.example.catalog.domain.GetCatalogUseCase
import com.example.catalog.domain.entites.ProductFilter
import com.example.catalog.domain.entites.ProductWithCartInfo
import com.example.catalog.presentation.CatalogFilterRouter
import com.example.catalog.presentation.CatalogRouter
import com.example.common.Container
import com.example.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
@ExperimentalCoroutinesApi
class CatalogViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val catalogRouter: CatalogRouter,
    private val catalogFilterRouter: CatalogFilterRouter,
): BaseViewModel(){

    private val filterFLow = MutableStateFlow(ProductFilter.EMPTY)

    val stateLiveValue = filterFLow
        .flatMapLatest { filter->
            getCatalogUseCase.getProducts(filter).map { container->
                container.map { products->
                    State(products, filter)
                }
            }
        }
        .toLiveValue(initialValue = Container.Pending)

    init {
        viewModelScope.launch {
            catalogFilterRouter.receiveFilterResults().collectLatest {
                filterFLow.value = it
            }
        }
    }

    fun launchFilter() = debounce {
        catalogFilterRouter.launchFilter(filterFLow.value)
    }

    fun launchDetails(productWithCartInfo: ProductWithCartInfo) = debounce {
        catalogRouter.launchDetails(productWithCartInfo.product.id)
    }

    fun addToCart(productWithCartInfo: ProductWithCartInfo) = debounce {
        viewModelScope.launch {
            addToCartUseCase.addToCart(productWithCartInfo.product)
        }
    }


    class State(
        val products: List<ProductWithCartInfo>,
        val filter: ProductFilter,
    )


}