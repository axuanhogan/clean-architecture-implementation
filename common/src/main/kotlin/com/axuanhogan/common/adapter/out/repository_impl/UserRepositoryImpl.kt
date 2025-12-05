package com.axuanhogan.common.adapter.out.repository_impl

import jakarta.enterprise.context.ApplicationScoped
import com.axuanhogan.common.mapper.toPDO
import com.axuanhogan.common.mapper.toDAO
import com.axuanhogan.common.adapter.out.jpa_repository.UserJpaRepository
import com.axuanhogan.core.port.`in`.pdo.UserPDO
import com.axuanhogan.core.port.out.repository.UserRepository
import java.util.*
import kotlin.jvm.optionals.getOrNull

@ApplicationScoped
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {

    override fun findById(id: UUID): UserPDO? {
        return userJpaRepository.findById(id)?.getOrNull()?.toPDO()
    }

    override fun save(pdo: UserPDO) {
        userJpaRepository.save(pdo.toDAO())
    }

    override fun delete(pdo: UserPDO) {
        userJpaRepository.delete(pdo.toDAO())
    }
}
