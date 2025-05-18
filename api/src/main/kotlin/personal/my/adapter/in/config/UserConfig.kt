package personal.my.adapter.`in`.config

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import personal.my.use_case.UserUseCase
import personal.my.use_case.port.out.repository.UserRepository

@ApplicationScoped
class UserConfig(
    private val userRepository: UserRepository
) {

    @Produces
    fun createUserUseCase(): UserUseCase {
        return UserUseCase(userRepository)
    }
}
