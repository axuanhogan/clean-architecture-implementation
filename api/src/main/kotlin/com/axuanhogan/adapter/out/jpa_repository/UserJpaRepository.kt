package com.axuanhogan.adapter.out.jpa_repository

import org.springframework.data.jpa.repository.JpaRepository
import com.axuanhogan.jpa.dao.UserDAO
import java.util.*

interface UserJpaRepository: JpaRepository<UserDAO, UUID> {
    fun findAllById(id: UUID): List<UserDAO>
}
