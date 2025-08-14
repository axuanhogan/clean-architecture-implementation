package personal.my.adapter.config

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import personal.my.core.use_case.UserUseCase
import personal.my.core.port.out.repository.UserRepository

@ApplicationScoped
class UseCaseConfig {

    @Produces
    fun userUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCase(
            userRepository = userRepository
        )
    }
}
