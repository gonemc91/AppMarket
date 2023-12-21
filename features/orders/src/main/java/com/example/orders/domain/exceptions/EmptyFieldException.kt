package com.example.orders.domain.exceptions

import com.example.common.AppException
import com.example.orders.domain.entities.Field

class EmptyFieldException(
    val field: Field
) : AppException()