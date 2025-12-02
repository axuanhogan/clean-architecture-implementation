package com.axuanhogan.core.use_case.user

import com.axuanhogan.core.exception.UserNotFoundException
import com.axuanhogan.core.port.out.repository.UserRepository
import java.util.*

class GetUserEmailUseCase(
    private val userRepository: UserRepository
) {

    fun execute(input: GetUserEmailUseCaseInput): GetUserEmailUseCaseOutput {

        val user = userRepository.findAllById(id = input.userId)
            ?: throw UserNotFoundException("User not found")

        return GetUserEmailUseCaseOutput(
            email = user.email
        )
    }
}

data class GetUserEmailUseCaseInput(
    val userId: UUID,
)

data class GetUserEmailUseCaseOutput(
    val email: String,
)
