package com.axuanhogan.core.use_case.user

import com.axuanhogan.core.port.`in`.pdo.UserPDO
import com.axuanhogan.core.port.out.repository.UserRepository
import java.util.UUID

class CreateUserUseCase(
    private val userRepository: UserRepository
) {

    fun execute(input: CreateUserUseCaseInput) {

        val pdo = UserPDO(
            id = UUID.randomUUID(),
            email = input.email,
            name = input.name,
        )

        return userRepository.saveData(pdo = pdo)
    }
}

data class CreateUserUseCaseInput(
    val email: String,
    val name: String,
)
