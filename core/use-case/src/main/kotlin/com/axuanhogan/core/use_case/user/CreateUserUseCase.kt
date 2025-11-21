package com.axuanhogan.core.use_case.user

import com.axuanhogan.core.port.`in`.pdo.UserPDO
import com.axuanhogan.core.port.out.repository.UserRepository

class CreateUserUseCase(
    private val userRepository: UserRepository
) {

    fun execute(input: CreateUserUseCaseInput) {
        return userRepository.saveData(pdo = input.userPDO)
    }
}

data class CreateUserUseCaseInput(
    val userPDO: UserPDO,
)
