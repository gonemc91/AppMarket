package com.example.cart.presentation.cartlist

import com.example.cart.presentation.cartlist.entites.UiCartItem

interface CartAdapterListener{
    fun onIncrement(cartItem: UiCartItem)
    fun onDecrement(cartItem: UiCartItem)
    fun onChangeQuantity(cartItem: UiCartItem)

    fun onChosen(cartItem: UiCartItem)
    fun onToggle(cartItem: UiCartItem)
}

