package com.example.orders.presentation.create

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elveum.elementadapter.ElementListAdapter
import com.elveum.elementadapter.simpleAdapter
import com.example.orders.R
import com.example.orders.databinding.FragmentCreateOrderBinding
import com.example.orders.databinding.ItemOrderProductBinding
import com.example.orders.domain.entities.CartItem
import com.example.orders.domain.entities.Field
import com.example.orders.domain.entities.Recipient
import com.example.presentation.live.observeEvent
import com.example.presentation.viewBinding
import com.example.presentation.views.observe
import com.example.presentation.views.setupSimpleList
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateOrdersFragment : Fragment(R.layout.fragment_create_order) {

    private val viewModel by viewModels<CreateOrdersViewModel>()

    private val binding by viewBinding<FragmentCreateOrderBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            val adapter = setupOrderItemsList()
            observeState(adapter)
            observeEvent()
            setupListeners()
        }
    }


    private fun FragmentCreateOrderBinding.observeState(adapter: ElementListAdapter<CartItem>){
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue){state ->
            createOrderButton.isEnabled = state.enableCratedOrderButton
            progressBar.isInvisible = !state.showProgressBar
            totalPriceValueTextView.text = state.cart.totalPrice.text
            adapter.submitList(state.cart.cartItems)
        }
    }


    private fun FragmentCreateOrderBinding.observeEvent(){
        val errorMessage = getString(R.string.orders_empty_field)
        viewModel.emptyFieldErrorLiveEvent.observeEvent(viewLifecycleOwner){
            val input = when(it){
                Field.FIRST_NAME -> firstNameTextInput
                Field.LAST_NAME -> lastNameTextInput
                Field.ADDRESS -> addressTextInput
            }
            input.isErrorEnabled = true
            input.error = errorMessage
        }
    }

    private fun FragmentCreateOrderBinding.setupListeners() {
        root.setTryAgainListener { viewModel.load() }
        createOrderButton.setOnClickListener {
            viewModel.createdOrder(makeRecipient())
        }
        firstNameEditText.addTextChangedListener { firstNameEditText.error = null }
        lastNameEditText.addTextChangedListener { lastNameEditText.error = null }
        addressEditText.addTextChangedListener { addressEditText.error = null }
    }


    private fun FragmentCreateOrderBinding.setupOrderItemsList(): ElementListAdapter<CartItem>{
        val adapter = createAdapter()
        productsRecyclerView.setupSimpleList()
        productsRecyclerView.adapter = adapter
        return adapter
    }


    private fun createAdapter(): ElementListAdapter<CartItem> = simpleAdapter<CartItem, ItemOrderProductBinding>{
        areItemsSame = {oldItem, newItem ->  oldItem.productId == newItem.productId}
        bind { cartItem ->
            productNameTextView.text = getString(R.string.orders_item_text, cartItem.name, cartItem.quantity)
            priceTextView.text = cartItem.price.text
        }
    }

    private fun FragmentCreateOrderBinding.makeRecipient(): Recipient {
        return Recipient(
            firstName = firstNameEditText.text.toString(),
            lastName = lastNameEditText.text.toString(),
            address = addressEditText.text.toString(),
            comment = commentEditText.text.toString()
        )
    }

}