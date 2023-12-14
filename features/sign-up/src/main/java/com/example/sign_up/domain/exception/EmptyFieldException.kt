package com.example.sign_up.domain.exception

import com.example.common.AppException
import com.example.sign_up.domain.entities.SignUpField

class EmptyFieldException (
    val field: SignUpField,
): AppException()