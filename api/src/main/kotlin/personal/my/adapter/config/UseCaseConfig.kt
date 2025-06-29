package personal.my.adapter.config

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import personal.my.use_case.UserUseCase
import personal.my.use_case.port.out.repository.UserRepository

@ApplicationScoped
class UseCaseConfig {

    @Produces
    fun userUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCase(
            userRepository = userRepository
        )
    }
}
