package personal.my.adapter.out.jpa_repository_impl

import jakarta.enterprise.context.ApplicationScoped
import personal.my.adapter.mapper.toPDO
import personal.my.adapter.out.jpa_repository.UserJpaRepository
import personal.my.core.port.`in`.pdo.UserPDO
import personal.my.core.port.out.repository.UserRepository
import java.util.*

@ApplicationScoped
class UserJpaRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {

    override fun findAllById(id: UUID): UserPDO? {
        return userJpaRepository.findAllById(id = id).firstOrNull()?.toPDO()
    }
}
