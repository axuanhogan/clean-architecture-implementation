package com.axuanhogan.core.use_case

import com.axuanhogan.core.port.`in`.pdo.UserPDO
import com.axuanhogan.core.port.out.repository.UserRepository
import java.util.*

class UserUseCase(
    private val userRepository: UserRepository
) {

    fun getUser(id: UUID): UserPDO? {
        return userRepository.findAllById(id = id)
    }
}
