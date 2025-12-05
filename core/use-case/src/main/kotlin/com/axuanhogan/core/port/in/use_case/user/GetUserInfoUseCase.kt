package com.axuanhogan.core.port.`in`.use_case.user

import com.axuanhogan.core.port.`in`.exception.UserNotFoundException
import com.axuanhogan.core.port.out.repository.UserRepository
import java.util.*

class GetUserInfoUseCase(
    private val userRepository: UserRepository
) {

    fun execute(input: GetUserInfoUseCaseInput): GetUserInfoUseCaseOutput {

        val user = userRepository.findById(id = input.userId)
            ?: throw UserNotFoundException("User not found")

        return GetUserInfoUseCaseOutput(
            email = user.email,
            name = user.name,
        )
    }
}

data class GetUserInfoUseCaseInput(
    val userId: UUID,
)

data class GetUserInfoUseCaseOutput(
    val email: String,
    val name: String,
)
