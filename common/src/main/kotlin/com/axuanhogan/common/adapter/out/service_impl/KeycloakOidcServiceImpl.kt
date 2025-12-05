package com.axuanhogan.common.adapter.out.service_impl

import com.axuanhogan.common.adapter.out.client.KeycloakOidcClient
import com.axuanhogan.core.port.out.service.KeycloakOidcService
import com.axuanhogan.core.port.out.service.KeycloakOidcService.GetTokenByPasswordGrantBody
import com.axuanhogan.core.port.out.service.KeycloakOidcService.GetTokenByPasswordGrantResponse
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class KeycloakOidcServiceImpl(
    @param:RestClient private val client: KeycloakOidcClient,
    @param:ConfigProperty(name = "quarkus.rest-client.keycloak-oidc.client-secret") val clientSecret: String,
): KeycloakOidcService {

    private val clientId: String = KeycloakOidcClient.Client.CLEAN_ARCHITECTURE_IMPLEMENTATION.name

    override fun getTokenByPasswordGrant(body: GetTokenByPasswordGrantBody): GetTokenByPasswordGrantResponse {
        val result = client.getTokenByPasswordGrant(
            grantType = "password",
            clientId = clientId,
            clientSecret = clientSecret,
            username = body.username,
            password = body.password,
            scope = body.scope
        )

        return GetTokenByPasswordGrantResponse(
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
