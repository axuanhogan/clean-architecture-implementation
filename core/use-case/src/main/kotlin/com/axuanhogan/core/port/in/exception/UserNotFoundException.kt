package com.axuanhogan.core.port.`in`.exception

data class UserNotFoundException(
    override val message: String,
    override val cause: Throwable? = null,
): Exception(message, cause)
