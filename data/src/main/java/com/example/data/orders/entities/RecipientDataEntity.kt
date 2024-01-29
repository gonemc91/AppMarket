package com.example.data.orders.entities

import com.example.data.orders.sources.room.entity.RecipientDataTuple

data class RecipientDataEntity(
    val firstName: String,
    val lastName: String,
    val address: String,
){
    fun toRecipientRoomEntity() = RecipientDataTuple(
        firstName = firstName,
        lastName = lastName,
        address = address
    )
}