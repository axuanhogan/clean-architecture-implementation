package personal.my.core.port.out.repository

import personal.my.core.port.`in`.pdo.UserPDO
import java.util.*

interface UserRepository {
    fun findAllById(id: UUID): UserPDO?
}
