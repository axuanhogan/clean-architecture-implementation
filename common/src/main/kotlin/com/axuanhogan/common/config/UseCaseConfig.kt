package com.axuanhogan.common.config

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import com.axuanhogan.core.use_case.UserUseCase
import com.axuanhogan.core.port.out.repository.UserRepository
import com.axuanhogan.core.port.out.service.KeycloakOidcService
import com.axuanhogan.core.use_case.AuthUseCase

@ApplicationScoped
class UseCaseConfig {

    @Produces
    fun userUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCase(
            userRepository = userRepository
        )
    }

    @Produces
    fun authUseCase(keycloakOidcService: KeycloakOidcService): AuthUseCase {
        return AuthUseCase(
            keycloakOidcService = keycloakOidcService
        )
    }
}
