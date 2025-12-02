package com.axuanhogan.core.port.out.service

interface KeycloakOidcService {

    fun getTokenByPasswordGrant(body: GetTokenByPasswordGrantBody): GetTokenByPasswordGrantResponse

    data class GetTokenByPasswordGrantBody(
        val username: String,
        val password: String,
        val scope: String? = null,
    )

    data class GetTokenByPasswordGrantResponse(
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
