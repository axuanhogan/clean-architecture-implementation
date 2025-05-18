package personal.my.use_case

import personal.my.use_case.port.out.repository.UserRepository
import personal.my.use_case.port.out.repository.UserRepository.*
import java.util.*

class UserUseCase(
    private val userRepository: UserRepository
) {

    fun getUser(id: UUID): FindByIdResponse {

        val user: FindByIdResponse = userRepository.findById(id = id) ?: throw UserNotFoundException(message = "User not found")

        return user
    }
}
