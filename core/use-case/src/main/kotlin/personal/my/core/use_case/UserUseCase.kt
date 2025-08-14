package personal.my.core.use_case

import personal.my.core.port.`in`.pdo.UserPDO
import personal.my.core.port.out.repository.UserRepository
import java.util.*

class UserUseCase(
    private val userRepository: UserRepository
) {

    fun getUser(id: UUID): UserPDO? {
        return userRepository.findAllById(id = id)
    }
}
