package com.axuanhogan.core.use_case

import com.axuanhogan.core.port.out.service.KeycloakOidcService
import com.axuanhogan.core.port.out.service.KeycloakOidcService.TokenByPasswordGrantRequest
import com.axuanhogan.core.port.out.service.KeycloakOidcService.TokenByPasswordGrantResponse

class AuthUseCase(
    private val keycloakOidcService: KeycloakOidcService
) {

    fun tokenByPasswordGrant(body: TokenByPasswordGrantRequest): TokenByPasswordGrantResponse {
        return keycloakOidcService.tokenByPasswordGrant(body = body)
    }
}
