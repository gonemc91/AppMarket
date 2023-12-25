package com.example.appmarket.glue.orders.mappers

import com.example.data.orders.entities.RecipientDataEntity
import com.example.orders.domain.entities.Recipient
import javax.inject.Inject

class RecipientMapper @Inject constructor(){

    fun toRecipientDataEntity(recipient: Recipient): RecipientDataEntity{
        return RecipientDataEntity(
            firstName = recipient.firstName,
            lastName = recipient.lastName,
            address = recipient.address
        )
    }
}