package com.axuanhogan.core.use_case.user

import com.axuanhogan.core.port.`in`.pdo.UserPDO
import com.axuanhogan.core.port.out.repository.UserRepository
import java.util.*

class GetUserUseCase(
    private val userRepository: UserRepository
) {

    fun execute(input: GetUserUseCaseInput): GetUserUseCaseOutput {
        val userPDO = userRepository.findAllById(id = input.userId)
        return GetUserUseCaseOutput(
            userPDO = userPDO
        )
    }
}

data class GetUserUseCaseInput(
    val userId: UUID,
)

data class GetUserUseCaseOutput(
    val userPDO: UserPDO?,
)
