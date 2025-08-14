package com.axuanhogan.adapter.out.jpa_repository_impl

import jakarta.enterprise.context.ApplicationScoped
import com.axuanhogan.adapter.mapper.toPDO
import com.axuanhogan.adapter.out.jpa_repository.UserJpaRepository
import com.axuanhogan.core.port.`in`.pdo.UserPDO
import com.axuanhogan.core.port.out.repository.UserRepository
import java.util.*

@ApplicationScoped
class UserJpaRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {

    override fun findAllById(id: UUID): UserPDO? {
        return userJpaRepository.findAllById(id = id).firstOrNull()?.toPDO()
    }
}
