package com.axuanhogan.core.port.`in`.use_case.auth

import com.axuanhogan.core.port.out.service.KeycloakOidcService

class SignInUseCase(
    private val keycloakOidcService: KeycloakOidcService
) {

    fun execute(input: SignInUseCaseInput): SignInUseCaseOutput {

        val response =  keycloakOidcService.getTokenByPasswordGrant(
            body = KeycloakOidcService.GetTokenByPasswordGrantBody(
                username = input.username,
                password = input.password,
                scope = input.scope,
            )
        )

        return SignInUseCaseOutput(
            accessToken = response.accessToken,
            expiresIn = response.expiresIn,
            refreshToken = response.refreshToken,
            refreshExpiresIn = response.refreshExpiresIn,
            tokenType = response.tokenType,
            notBeforePolicy = response.notBeforePolicy,
            sessionState = response.sessionState,
            scope = response.scope,
        )
    }
}

data class SignInUseCaseInput(
    val username: String,
    val password: String,
    val scope: String? = null,
)

data class SignInUseCaseOutput(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshExpiresIn: Int,
    val tokenType: String,
    val notBeforePolicy: Int,
    val sessionState: String,
    val scope: String,
)
