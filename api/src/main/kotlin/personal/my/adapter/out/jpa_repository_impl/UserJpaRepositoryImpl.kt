package personal.my.adapter.out.jpa_repository_impl

import jakarta.enterprise.context.ApplicationScoped
import personal.my.adapter.mapper.toPDO
import personal.my.adapter.out.jpa_repository.UserJpaRepository
import personal.my.use_case.port.`in`.pdo.UserPDO
import personal.my.use_case.port.out.repository.UserRepository
import java.util.*

@ApplicationScoped
class UserJpaRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {

    override fun findById(id: UUID): UserPDO? {
        return userJpaRepository.findById(id = id).firstOrNull()?.toPDO()
    }
}
