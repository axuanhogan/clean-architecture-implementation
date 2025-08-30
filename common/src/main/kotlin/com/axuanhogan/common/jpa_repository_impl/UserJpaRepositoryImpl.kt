package com.axuanhogan.common.jpa_repository_impl

import jakarta.enterprise.context.ApplicationScoped
import com.axuanhogan.common.mapper.toPDO
import com.axuanhogan.common.mapper.toDAO
import com.axuanhogan.common.jpa_repository.UserJpaRepository
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

    override fun saveData(pdo: UserPDO) {
        userJpaRepository.save(pdo.toDAO())
    }
}
