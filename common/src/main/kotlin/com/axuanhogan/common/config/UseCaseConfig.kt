package com.axuanhogan.common.config

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import com.axuanhogan.core.port.out.repository.UserRepository
import com.axuanhogan.core.port.out.service.KeycloakOidcService
import com.axuanhogan.core.use_case.auth.GetTokenByPasswordGrantUseCase
import com.axuanhogan.core.use_case.user.CreateUserUseCase
import com.axuanhogan.core.use_case.user.GetUserEmailUseCase

@ApplicationScoped
class UseCaseConfig {

    @Produces
    fun getUserUseCase(userRepository: UserRepository): GetUserEmailUseCase {
        return GetUserEmailUseCase(
            userRepository = userRepository
        )
    }

    @Produces
    fun createUserUseCase(userRepository: UserRepository): CreateUserUseCase {
        return CreateUserUseCase(
            userRepository = userRepository
        )
    }

    @Produces
    fun getTokenByPasswordGrantUseCase(keycloakOidcService: KeycloakOidcService): GetTokenByPasswordGrantUseCase {
        return GetTokenByPasswordGrantUseCase(
            keycloakOidcService = keycloakOidcService
        )
    }
}
