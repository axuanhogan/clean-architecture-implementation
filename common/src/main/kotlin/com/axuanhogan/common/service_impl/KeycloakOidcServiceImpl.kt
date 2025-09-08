package com.axuanhogan.common.service_impl

import com.axuanhogan.common.client.KeycloakOidcClient
import com.axuanhogan.core.port.out.service.KeycloakOidcService
import com.axuanhogan.core.port.out.service.KeycloakOidcService.TokenByPasswordGrantRequest
import com.axuanhogan.core.port.out.service.KeycloakOidcService.TokenByPasswordGrantResponse
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class KeycloakOidcServiceImpl(
    @param:RestClient private val client: KeycloakOidcClient,
): KeycloakOidcService {

    override fun tokenByPasswordGrant(body: TokenByPasswordGrantRequest): TokenByPasswordGrantResponse {
        val result = client.tokenByPasswordGrant(
            grantType = "password",
            clientId = body.clientId,
            clientSecret = body.clientSecret,
            username = body.username,
            password = body.password,
            scope = body.scope
        )

        return TokenByPasswordGrantResponse(
            accessToken = result.accessToken,
            expiresIn = result.expiresIn,
            refreshToken = result.refreshToken,
            refreshExpiresIn = result.refreshExpiresIn,
            tokenType = result.tokenType,
            notBeforePolicy = result.notBeforePolicy,
            sessionState = result.sessionState,
            scope = result.scope,
        )
    }
}
