package com.example.sign_up.domain.exception

import com.example.common.AppException

class AccountAlreadyExistsException(
    cause: Throwable? = null
) : AppException()