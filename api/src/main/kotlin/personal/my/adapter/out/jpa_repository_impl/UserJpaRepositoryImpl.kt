package personal.my.adapter.out.jpa_repository_impl

import jakarta.enterprise.context.ApplicationScoped
import personal.my.adapter.out.jpa_repository.UserJpaRepository
import personal.my.dao.User
import personal.my.use_case.port.out.repository.UserRepository
import personal.my.use_case.port.out.repository.UserRepository.FindByIdResponse
import java.util.*

@ApplicationScoped
class UserJpaRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {

    override fun findById(id: UUID): FindByIdResponse? {
        val user: User = userJpaRepository.findById(id = id).firstOrNull() ?: return null

        return FindByIdResponse(
            id = user.id,
            name = user.name,
        )
    }
}
