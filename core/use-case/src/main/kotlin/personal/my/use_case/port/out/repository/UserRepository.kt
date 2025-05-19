package personal.my.use_case.port.out.repository

import personal.my.use_case.port.`in`.pdo.UserPDO
import java.util.*

interface UserRepository {
    fun findById(id: UUID): UserPDO?

    data class UserNotFoundException(
        override val message: String,
        override val cause: Throwable? = null
    ) : Exception(message, cause)
}
