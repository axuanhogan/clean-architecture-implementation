package personal.my.use_case.port.out.repository

import java.util.*

interface UserRepository {

    fun findById(id: UUID): FindByIdResponse?

    data class FindByIdResponse(
        val id: UUID,
        val name: String,
    )

    data class UserNotFoundException(
        override val message: String,
        override val cause: Throwable? = null
    ) : Exception(message, cause)
}
