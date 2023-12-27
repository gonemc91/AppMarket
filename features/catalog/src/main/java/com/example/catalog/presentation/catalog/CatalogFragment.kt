package com.example.catalog.presentation.catalog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elveum.elementadapter.SimpleBindingAdapter
import com.elveum.elementadapter.simpleAdapter
import com.example.catalog.R
import com.example.catalog.databinding.FragmentCatalogBinding
import com.example.catalog.databinding.ItemProductBinding
import com.example.catalog.domain.entites.ProductWithCartInfo
import com.example.presentation.Mode
import com.example.presentation.loadResources
import com.example.presentation.loadUrl
import com.example.presentation.viewBinding
import com.example.presentation.views.observe
import com.example.presentation.views.setupSimpleList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CatalogFragment : Fragment(R.layout.fragment_catalog){

    private val binding by viewBinding<FragmentCatalogBinding>()

    private val viewModel by viewModels<CatalogViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = createAdapter()
        with(binding){
            observeState(adapter)
            setupList(adapter)
            setupListeners()
        }
    }


    private fun FragmentCatalogBinding.observeState(adapter: SimpleBindingAdapter<ProductWithCartInfo>){
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue){state->
            adapter.submitList(state.products)
        }
    }

    private fun FragmentCatalogBinding.setupList(adapter: SimpleBindingAdapter<ProductWithCartInfo>){
        productsRecyclerView.setupSimpleList()
        productsRecyclerView.adapter = adapter
    }

    private fun FragmentCatalogBinding.setupListeners(){
        sortAndFilterButton.setOnClickListener{
            viewModel.launchFilter()
        }
    }



    @SuppressLint("UseCompatLoadingForDrawables")
    private fun createAdapter() = simpleAdapter<ProductWithCartInfo, ItemProductBinding> {
        areItemsSame = {oldItem, newItem ->  oldItem.product.id == newItem.product.id}
        areContentsSame = {oldItem, newItem -> oldItem == newItem }
        
        
        bind { productWithCartInfo ->
            val product = productWithCartInfo.product
            nameTextView.text = product.name
            shortDescriptionTextView.text = product.shortDetails
            originPriceTextView.text = product.price.text
            if (product.priceWithDiscount == null){
                priceWithDiscountTextView.isVisible = false
                originPriceTextView.foreground = null
            }else{
                priceWithDiscountTextView.isVisible = true
                priceWithDiscountTextView.text = product.priceWithDiscount.text
                originPriceTextView.foreground = context?.getDrawable(com.example.theme.R.drawable.core_theme_diagonal_line)
            }
            addToCartImageView.isEnabled = !productWithCartInfo.isInCart
            addToCartImageView.setImageResource(
                if(productWithCartInfo.isInCart)
                com.example.theme.R.drawable.core_theme_ic_done
                else
                R.drawable.ic_add_to_cart
            )

            if (product.photo.isNotBlank()){
                productImageView.loadUrl(product.photo, Mode.RECTANGLE_CORNER_16F)
            }else{
                productImageView.loadResources(com.example.theme.R.drawable.core_theme_placeholder)
            }
            categoryHintTextView.text = getString(R.string.catalog_category, product.category)
        }


        listeners {
            addToCartImageView.onClick { productWithCartInfo ->
                viewModel.addToCart(productWithCartInfo)
            }
            root.onClick{ productWithCartInfo ->
                viewModel.launchDetails(productWithCartInfo)

            }
        }
    }

}