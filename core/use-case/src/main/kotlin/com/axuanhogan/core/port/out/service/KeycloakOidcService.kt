package com.axuanhogan.core.port.out.service

interface KeycloakOidcService {

    fun tokenByPasswordGrant(body: TokenByPasswordGrantRequest): TokenByPasswordGrantResponse

    data class TokenByPasswordGrantRequest(
        val clientId: String,
        val clientSecret: String,
        val username: String,
        val password: String,
        val scope: String? = null,
    )

    data class TokenByPasswordGrantResponse(
        val accessToken: String,
        val expiresIn: Int,
        val refreshToken: String,
        val refreshExpiresIn: Int,
        val tokenType: String,
        val notBeforePolicy: Int,
        val sessionState: String,
        val scope: String,
    )
}
