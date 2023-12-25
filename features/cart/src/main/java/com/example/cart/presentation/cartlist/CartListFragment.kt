package com.example.cart.presentation.cartlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.elveum.elementadapter.ElementListAdapter
import com.elveum.elementadapter.setTintColor
import com.elveum.elementadapter.simpleAdapter
import com.example.cart.R
import com.example.cart.databinding.FragmentCartListBinding
import com.example.cart.databinding.ItemCartProductBinding
import com.example.cart.presentation.cartlist.entites.UiCartItem
import com.example.presentation.loadUrl
import com.example.presentation.viewBinding
import com.example.presentation.views.observe
import com.example.presentation.views.setupSimpleList
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartListFragment: Fragment(R.layout.fragment_cart_list) {

    private val viewModel by viewModels<CartListViewModel> ()

    private val binding by viewBinding<FragmentCartListBinding>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = createCartAdapter(viewModel)
        with(binding){
            setupList(adapter)
            setupActions()
            observeState(adapter)
            setupListeners()

        }
        viewModel.initBackListener(viewLifecycleOwner.lifecycleScope)
    }


    private fun FragmentCartListBinding.setupList(adapter: ElementListAdapter<UiCartItem>){
        cartRecyclerView.setupSimpleList()
        cartRecyclerView.adapter = adapter
    }

    private fun FragmentCartListBinding.setupActions(){
        deleteAction.actionImageView.setImageResource(R.drawable.ic_delete)
        deleteAction.actionTextView.setText(R.string.cart_action_delete)


        showDetailsAction.actionImageView.setImageResource(R.drawable.ic_details)
        showDetailsAction.actionTextView.setText(R.string.cart_action_details)

        editQuantityAction.actionImageView.setImageResource(R.drawable.ic_edit)
        editQuantityAction.actionTextView.setText(R.string.cart_action_edit)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun FragmentCartListBinding.observeState(adapter: ElementListAdapter<UiCartItem>){
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue){state ->
            adapter.submitList(state.cartItems)
            totalPriceValueTextView.text = state.totalPrice.text
            if(state.totalPriceWithDiscount == state.totalPrice){
                totalPriceWithDiscountValueTextView.isVisible = false
                totalPriceValueTextView.foreground = null
            }else{
                totalPriceWithDiscountValueTextView.text = state.totalPriceWithDiscount.text
                totalPriceWithDiscountValueTextView.isVisible = true
                totalPriceValueTextView.text = state.totalPrice.text
                totalPriceValueTextView.foreground = context?.getDrawable(com.example.theme.R.drawable.core_theme_diagonal_line)
            }

            binding.createOrderButton.isVisible = state.enableCreateOrderButton
            binding.deleteAction.root.isVisible = state.showDeleteAction
            binding.editQuantityAction.root.isVisible = state.showEditQuantityAction
            binding.showDetailsAction.root.isVisible = state.showDetailsAction
            binding.actionsContainer.isVisible = state.showActionsPanel
        }

    }

    private fun FragmentCartListBinding.setupListeners(){
        createOrderButton.setOnClickListener {
            viewModel.createOrder()
        }
        deleteAction.root.setOnClickListener{
            viewModel.deleteSelected()
        }
        editQuantityAction.root.setOnClickListener {
            viewModel.showEditQuantity()
        }
        showDetailsAction.root.setOnClickListener {
            viewModel.showDetails()
        }
        root.setTryAgainListener { viewModel.reload() }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun createCartAdapter(
        listener: CartAdapterListener
    )= simpleAdapter<UiCartItem, ItemCartProductBinding> {

        areItemsSame = {oldItem, newItem -> oldItem.id == newItem.id }
        areContentsSame = {oldItem, newItem -> oldItem == newItem}

        bind { cartItem ->
            nameTextView.text = cartItem.name
            productImageView.loadUrl(cartItem.imageUrl)
            quantityTextView.text = cartItem.quantity.toString()
            decrementImageView.setEnabledAndTint(cartItem.canDecrement)
            incrementImageView.setEnabledAndTint(cartItem.canIncrement)
            originPriceTextView.text = cartItem.totalOriginPrice.text
            priceWithDiscountTextView.text = cartItem.totalDiscountPrice.text
            checkBox.isVisible = cartItem.showCheckBox
            checkBox.isChecked = cartItem.isChecked

            if (cartItem.totalOriginPrice == cartItem.totalDiscountPrice ){
                priceWithDiscountTextView.isVisible = false
                originPriceTextView.foreground = null
            }else{
                priceWithDiscountTextView.isVisible = true
                originPriceTextView.foreground = context?.getDrawable(com.example.theme.R.drawable.core_theme_diagonal_line)
            }
        }

        listeners{
            incrementImageView.onClick { listener.onIncrement(it) }
            decrementImageView.onClick { listener.onDecrement(it) }
            quantityTextView.onClick { listener.onChangeQuantity(it) }
            root.onClick { listener.onChosen(it) }
            root.onLongClick {
                listener.onToggle(it)
                true
            }
            checkBox.onClick { listener.onToggle(it) }
        }
    }


    private fun ImageView.setEnabledAndTint(isEnabled: Boolean){
        this.isEnabled = isEnabled
        if(isEnabled){
            this.setTintColor(com.example.theme.R.color.action)
        }else{
            this.setTintColor(com.example.theme.R.color.action_disabled)
        }
    }







}