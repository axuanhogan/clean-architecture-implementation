package com.axuanhogan.common.exception

data class KeycloakOidcException(
    val status: Int,
    val reasonPhrase: String,
    val error: String,
    val errorDescription: String? = null,
) : RuntimeException()
